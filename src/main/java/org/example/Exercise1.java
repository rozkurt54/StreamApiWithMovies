package org.example;

import org.example.dao.movie.InMemoryMovieService;
import org.example.dao.movie.MovieService;
import org.example.domain.movie.Director;
import org.example.domain.movie.Movie;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercise1 {

    private static final MovieService MOVIE_SERVICE = InMemoryMovieService.getInstance();

    // Count movies by each Director

    public static void main(String[] args) {
        final Collection<Movie> movies = MOVIE_SERVICE.findAllMovies();
        Map<String, Long> directorMovieCount = movies
                .stream()
                .map(Movie::getDirectors)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Director::getName, Collectors.counting()));

        directorMovieCount.forEach((name, count) -> System.out.printf("%25s: %3d\n", name, count));
    }

}
