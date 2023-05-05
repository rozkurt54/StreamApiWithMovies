package org.example;

import org.example.dao.movie.InMemoryMovieService;
import org.example.dao.movie.MovieService;

import java.util.List;

public class Exercise3 {

    private static final MovieService MOVIE_SERVICE = InMemoryMovieService.getInstance();

    // Find the list of movies having the genres "Drama" and "Comedy" only
    public static void main(String[] args) {

        var listOfGenreNames = List.of("Drama", "Comedy");
        var listOfGenres = listOfGenreNames.stream().map(MOVIE_SERVICE::findGenreByName).toList();
        var moviesInDramaAndComedyOnly = MOVIE_SERVICE
                .findAllMovies()
                .stream()
                .filter(movie -> movie.getGenres().size() == 2)
                .filter(movie -> movie.getGenres().containsAll(listOfGenres))
                .toList();

        moviesInDramaAndComedyOnly.forEach(movie -> System.out.printf("%s %s\n", movie, movie.getGenres()));

    }

}
