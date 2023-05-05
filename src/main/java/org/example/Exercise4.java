package org.example;

import org.example.dao.movie.InMemoryMovieService;
import org.example.dao.movie.MovieService;
import org.example.domain.movie.Movie;

import java.util.stream.Collectors;

public class Exercise4 {

    private static final MovieService  service = InMemoryMovieService.getInstance();

    // Group the movies by the year and list them
    public static void main(String[] args) {
        var moviesByYear = service.findAllMovies()
                .stream()
                .collect(Collectors.groupingBy(Movie::getYear));
        moviesByYear.forEach((year, movies) -> System.out.printf("%d: %s\n", year, movies));
    }

}
