/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.parsers.rss;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.enums.Role;
import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.utilities.FileUtils;
import hr.algebra.view.AdminPanel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.jsoup.Jsoup;

/**
 *
 * @author matej.galic
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar-bh.ba/rss.aspx?id=2682";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";
    private static final String DIV = "div";
    private static final String COMMA = ",";

    private static Repository repository;

    private MovieParser() {
    }

    public static void parse(int maxMovieId, int maxPersonId) throws IOException, XMLStreamException, Exception {
        List<Movie> movies = new ArrayList<>();
        List<Person> people = new ArrayList<>();

        int movieID = maxMovieId == 0 ? 1 : maxMovieId;
        int personId = maxPersonId == 0 ? 1 : maxPersonId;

        repository = RepositoryFactory.getRepository();

        HttpURLConnection con = UrlConnectionFactory
                .getHttpUrlConnection(RSS_URL);

        try (InputStream is = con.getInputStream();) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Movie movie = null;
            Person person = null;
            StartElement startElement = null;
            Optional<TagType> tagType = Optional.empty();

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);

                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            movie = new Movie();
                            movies.add(movie);
                            movie.setId(movieID);
                            movieID++;
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        if (tagType.isPresent() && movie != null) {
                            String data = event.asCharacters().getData().trim();

                            switch (tagType.get()) {
                                case TITLE:
                                    if (!data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    break;
                                case PUB_DATE:
                                    if (!data.isEmpty()) {
                                        movie.setPublishedDate(
                                                LocalDateTime.parse(
                                                        data,
                                                        DateTimeFormatter.RFC_1123_DATE_TIME
                                                )
                                        );
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (!data.isEmpty()) {
                                        org.jsoup.nodes.Document doc = Jsoup.parse(data);
                                        org.jsoup.nodes.Element descriptionElement = doc.selectFirst(DIV);
                                        if (descriptionElement != null) {
                                            String description = descriptionElement.ownText();
                                            movie.setDescription(description);
                                        } else {
                                            movie.setDescription(data);
                                        }
                                    }
                                    break;
                                case DIRECTOR:
                                    if (!data.isEmpty()) {
                                        try {
                                            String names = data
                                                    .replaceAll("<!\\[CDATA\\[", "")
                                                    .replaceAll("\\]\\]>", "")
                                                    .trim();
                                            String[] peopleNames = names.split(COMMA);

                                            for (String personName : peopleNames) {
                                                person = new Person(personName, Role.DIRECTOR, movie.getId());
                                                if (!repository.findPerson(person).isPresent()) {
                                                    repository.createPerson(person);
                                                    person.setId(personId);
                                                    personId++;
                                                    people.add(person);
                                                }
                                            }
                                        } catch (Exception ex) {
                                            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                                            break;
                                        }
                                    }
                                    break;
                                case ACTORS:
                                    if (!data.isEmpty()) {
                                        try {
                                            String names = data
                                                    .replaceAll("<!\\[CDATA\\[", "")
                                                    .replaceAll("\\]\\]>", "")
                                                    .trim();
                                            String[] peopleNames = names.split(COMMA);

                                            for (String personName : peopleNames) {
                                                person = new Person(personName, Role.ACTOR, movie.getId());
                                                if (!repository.findPerson(person).isPresent()) {
                                                    repository.createPerson(person);
                                                    person.setId(personId);
                                                    personId++;
                                                    people.add(person);
                                                }
                                            }
                                        } catch (Exception ex) {
                                            Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
                                            break;
                                        }
                                    }
                                    break;
                                case DURATION:
                                    if (!data.isEmpty()) {
                                        movie.setDuration(
                                                Integer.parseInt(data));
                                    }
                                    break;
                                case YEAR:
                                    if (!data.isEmpty()) {
                                        movie.setYear(
                                                Integer.parseInt(data));
                                    }
                                    break;
                                case PICTURE:
                                    if (!data.isEmpty()) {
                                        handlePicture(movie, data);
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        }

        repository.createMovies(movies);
        repository.deleteDuplicateMovies();

        for (Person person : people) {
            repository.createPersonInMovie(person.getMovieId(), person.getId(), person);
        }
    }

    private static void handlePicture(Movie movie, String url) throws IOException {
        String ext = url.substring(url.lastIndexOf("."));
        if (ext.length() > 4) {
            ext = EXT;
        }

        String imageName = UUID.randomUUID() + ext;
        String localPath = DIR + File.separator + imageName;

        FileUtils.copyFromUrl(url, localPath);
        movie.setPicturePath(localPath);
    }

    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        PUB_DATE("pubDate"),
        DESCRIPTION("description"),
        DIRECTOR("redatelj"),
        ACTORS("glumci"),
        DURATION("trajanje"),
        YEAR("godina"),
        PICTURE("plakat");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }
}
