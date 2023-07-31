/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.model.Role;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SqlRepository implements Repository {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String DIRECTOR = "DirectorID";
    private static final String DURATION = "Duration";
    private static final String YEAR = "Year";

    private static final String ID_PERSON = "IDPerson";
    private static final String NAME = "Name";
    private static final String ROLE = "Role";

    private static final String CREATE_PERSON = "{ CALL createPerson (?,?,?) }";
    private static final String UPDATE_PERSON = "{ CALL updatePerson (?,?,?) }";
    private static final String DELETE_PERSON = "{ CALL deletePerson (?) }";
    private static final String SELECT_PERSON = "{ CALL selectPerson (?) }";
    private static final String SELECT_ACTORS = "{ CALL selectActors }";
    private static final String SELECT_DIRECTORS = "{ CALL selectDirectors }";

    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";

    @Override
    public int createPerson(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PERSON);) {
            stmt.setString(NAME, person.getName());
            stmt.setString(ROLE, person.getRole().name());

            stmt.registerOutParameter(ID_PERSON, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(ID_PERSON);
        }
    }

    @Override
    public void createPeople(List<Person> people) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PERSON);) {

            for (Person person : people) {
                stmt.setString(NAME, person.getName());
                stmt.setString(ROLE, person.getRole().name());
                stmt.registerOutParameter(ID_PERSON, Types.INTEGER);
                stmt.executeUpdate();
            }

        }
    }

    @Override
    public void updatePerson(int id, Person data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_PERSON);) {
            stmt.setString(NAME, data.getName());
            stmt.setString(ROLE, data.getRole().name());

            stmt.setInt(ID_PERSON, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletePerson(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_PERSON);) {

            stmt.setInt(ID_PERSON, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Person> selectPerson(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_PERSON);) {

            stmt.setInt(ID_PERSON, id);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(NAME),
                            Role.fromString(rs.getString(ROLE))
                    ));
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public List<Person> selectActors() throws Exception {
        List<Person> actors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                actors.add(new Person(
                        rs.getInt(ID_PERSON),
                        rs.getString(NAME),
                        Role.fromString(rs.getString(ROLE))
                ));
            }
        }

        return actors;
    }

    @Override
    public List<Person> selectDirectors() throws Exception {
        List<Person> directors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                directors.add(new Person(
                        rs.getInt(ID_PERSON),
                        rs.getString(NAME),
                        Role.fromString(rs.getString(ROLE))
                ));
            }
        }

        return directors;
    }

    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(PUBLISHED_DATE, movie.getPublishedDate()
                    .format(Movie.DATE_FORMATTER));
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setString(PICTURE_PATH, movie.getPicturePath());
            stmt.setString(DIRECTOR, movie.getDirector().getName());
            //TODO: pogledat kaj sa listom glumaca kak da ubacim to u bazu i tu
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setInt(YEAR, movie.getYear());

            stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(ID_MOVIE);
        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateMovie(int id, Movie data) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Movie> selectMovies() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    /*@Override
    public int createArticle(Article article) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ARTICLE);) {
            stmt.setString(TITLE, article.getTitle());
            stmt.setString(LINK, article.getLink());
            stmt.setString(DESCRIPTION, article.getDescription());
            stmt.setString(PICTURE_PATH, article.getPicturePath());
            stmt.setString(PUBLISHED_DATE, article.getPublishedDate()
                    .format(Article.DATE_FORMATTER));

            stmt.registerOutParameter(ID_ARTICLE, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(ID_ARTICLE);
        }
    }

    @Override
    public void createArticles(List<Article> articles) throws Exception {

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ARTICLE);) {

            for (Article article : articles) {
                stmt.setString(TITLE, article.getTitle());
                stmt.setString(LINK, article.getLink());
                stmt.setString(DESCRIPTION, article.getDescription());
                stmt.setString(PICTURE_PATH, article.getPicturePath());
                stmt.setString(PUBLISHED_DATE, article.getPublishedDate()
                        .format(Article.DATE_FORMATTER));
                stmt.registerOutParameter(ID_ARTICLE, Types.INTEGER);
                stmt.executeUpdate();
            }

        }
    }

    @Override
    public void updateArticle(int id, Article data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_ARTICLE);) {
            stmt.setString(TITLE, data.getTitle());
            stmt.setString(LINK, data.getLink());
            stmt.setString(DESCRIPTION, data.getDescription());
            stmt.setString(PICTURE_PATH, data.getPicturePath());
            stmt.setString(PUBLISHED_DATE, data.getPublishedDate()
                    .format(Article.DATE_FORMATTER));

            stmt.setInt(ID_ARTICLE, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public void deleteArticle(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ARTICLE);) {

            stmt.setInt(ID_ARTICLE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Article> selectArticle(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ARTICLE);) {

            stmt.setInt(ID_ARTICLE, id);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new Article(
                            rs.getInt(ID_ARTICLE),
                            rs.getString(TITLE),
                            rs.getString(LINK),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            LocalDateTime.parse(
                                    rs.getString(PUBLISHED_DATE),
                                    Article.DATE_FORMATTER)
                    ));
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public List<Article> selectArticles() throws Exception {
        List<Article> articles = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ARTICLES); 
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                articles.add(new Article(
                        rs.getInt(ID_ARTICLE),
                        rs.getString(TITLE),
                        rs.getString(LINK),
                        rs.getString(DESCRIPTION),
                        rs.getString(PICTURE_PATH),
                        LocalDateTime.parse(
                                rs.getString(PUBLISHED_DATE),
                                Article.DATE_FORMATTER)
                ));
            }
        }

        return articles;
    }*/
}
