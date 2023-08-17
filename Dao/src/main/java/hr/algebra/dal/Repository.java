/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.AppUser;
import hr.algebra.model.Person;
import hr.algebra.model.Movie;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author matej.galic
 */
public interface Repository {
    
    int createPerson(Person person) throws Exception;
    void createPeople(List<Person> people) throws Exception;
    void updatePerson(int id, Person person) throws Exception;
    void deletePerson(int id) throws Exception;
    Optional<Person> selectPerson(int id) throws Exception;
    List<Person> selectPeople() throws Exception;
    List<Person> selectActors() throws Exception;
    List<Person> selectDirectors() throws Exception;
    
    int createMovie(Movie movie) throws Exception;
    void createMovies(List<Movie> movies) throws Exception;
    void updateMovie(int id, Movie movie) throws Exception;
    void deleteMovie(int id) throws Exception;
    Optional<Movie> selectMovie(int id) throws Exception;
    List<Movie> selectMovies() throws Exception;
    
    int createPersonInMovie(int movieId, int personId, Person person) throws Exception;
    void createPeopleInMovie(int createdMovieId, Set<Person> people) throws Exception;
    Set<Person> selectPeopleInMovie(int movieId) throws Exception;
    public void deletePeopleInMovie(int id) throws Exception;
    
    int createUser(AppUser appUser) throws Exception;
    Optional<AppUser> selectUser(String username, String password) throws Exception;
    
    void deleteAllData() throws Exception;

    Optional<Integer> findPerson(Person person) throws Exception;
    Optional<Integer> findMovie(String title) throws Exception;

    void deleteDuplicateMovies() throws Exception;   
    
    Optional<Integer> maxMovieId() throws Exception;
    Optional<Integer> maxPersonId() throws Exception;
    
}
