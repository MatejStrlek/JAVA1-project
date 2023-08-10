/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.enums.Role;
import hr.algebra.enums.User;
import hr.algebra.model.AppUser;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.sql.DataSource;

public class SqlRepository implements Repository {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String DURATION = "Duration";
    private static final String YEAR = "Year";

    private static final String ID_PERSON = "IDPerson";
    private static final String NAME = "Name";
    private static final String ROLE = "Role";

    private static final String ID_APP_USER = "IDAppUser";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String APP_ROLE = "Role";

    private static final String FK_PERSON_ID = "PersonID";
    private static final String FK_MOVIE_ID = "MovieID";
    private static final String ROLE_IN_MOVIE = "RoleInMovie";
    private static final String ID_PERSON_MOVIE = "IDPersonMovie";

    private static final String CREATE_PERSON = "{ CALL createPerson (?,?,?) }";
    private static final String UPDATE_PERSON = "{ CALL updatePerson (?,?,?) }";
    private static final String DELETE_PERSON = "{ CALL deletePerson (?) }";
    private static final String SELECT_PERSON = "{ CALL selectPerson (?) }";
    private static final String SELECT_PEOPLE = "{ CALL selectPeople }";
    private static final String SELECT_ACTORS = "{ CALL selectActors }";
    private static final String SELECT_DIRECTORS = "{ CALL selectDirectors }";

    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";

    private static final String CREATE_PERSON_IN_MOVIE = "{ CALL createPersonInMovie (?,?,?,?) }";
    private static final String SELECT_PEOPLE_IN_MOVIE = "{ CALL selectPeopleInMovie (?) }";

    private static final String CREATE_USER = "{ CALL createUser (?,?,?,?) }";
    private static final String SELECT_USER = "{ CALL selectUser (?,?) }";

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
    public List<Person> selectPeople() throws Exception {
        List<Person> people = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_PEOPLE); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                people.add(new Person(
                        rs.getInt(ID_PERSON),
                        rs.getString(NAME),
                        Role.fromString(rs.getString(ROLE))
                ));
            }
        }

        return people;
    }

    @Override
    public List<Person> selectActors() throws Exception {
        List<Person> actors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ACTORS); ResultSet rs = stmt.executeQuery();) {
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
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS); ResultSet rs = stmt.executeQuery();) {
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
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIE);) {

            stmt.setInt(ID_MOVIE, id);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            LocalDateTime.parse(
                                    rs.getString(PUBLISHED_DATE),
                                    Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            rs.getInt(DURATION),
                            rs.getInt(YEAR)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMovies() throws Exception { //SELECT_MOVIES
        List<Movie> movies = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIES); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        LocalDateTime.parse(
                                rs.getString(PUBLISHED_DATE),
                                Movie.DATE_FORMATTER),
                        rs.getString(DESCRIPTION),
                        rs.getString(PICTURE_PATH),
                        rs.getInt(DURATION),
                        rs.getInt(YEAR)
                ));
            }
        }

        return movies;
    }

    @Override
    public void createPeopleInMovie(int createdMovieId, Set<Person> people) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PERSON_IN_MOVIE);) {

            for (Person person : people) {
                stmt.setInt(FK_PERSON_ID, person.getId());
                stmt.setInt(FK_MOVIE_ID, createdMovieId);
                stmt.setString(ROLE_IN_MOVIE, person.getRole().name());

                stmt.registerOutParameter(ID_PERSON_MOVIE, Types.INTEGER);
                stmt.executeUpdate();
            }

        }
    }

    @Override
    public Set<Person> selectPeopleInMovie(int movieId) throws Exception {
        Set<Person> peopleInMovie = new HashSet<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_PEOPLE_IN_MOVIE);) {

            stmt.setInt(FK_MOVIE_ID, movieId);

            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    peopleInMovie.add(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(NAME),
                            Role.fromString(rs.getString(ROLE))
                    ));
                }
            }
        }
        return peopleInMovie;
    }

    @Override
    public int createUser(AppUser appUser) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER);) {
            stmt.setString(USERNAME, appUser.getUsername());
            stmt.setString(PASSWORD, appUser.getPassword());
            stmt.setString(APP_ROLE, appUser.getAppUserRole().name());

            stmt.registerOutParameter(ID_APP_USER, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(ID_APP_USER);
        }
    }

    @Override
    public Optional<AppUser> selectUser(String username, String password) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER);) {

            stmt.setString(USERNAME, username);
            stmt.setString(PASSWORD, password);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new AppUser(
                            rs.getInt(ID_APP_USER),
                            User.fromString(rs.getString(APP_ROLE))
                    ));
                }
            }

        }
        return Optional.empty();
    }

}
