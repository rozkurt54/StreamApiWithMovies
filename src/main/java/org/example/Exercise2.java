package org.example;

import org.example.dao.movie.InMemoryMovieService;
import org.example.dao.movie.MovieService;
import org.example.domain.movie.Director;
import org.example.domain.movie.Genre;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise2 {

    private static final MovieService MOVIE_SERVICE = InMemoryMovieService.getInstance();

    // Find the number of genres of each director's movies.

    public static void main(String[] args) {

        var directorGenreNumbers = MOVIE_SERVICE
                .findAllMovies()
                .stream()
                .map(movie-> movie.getDirectors().stream().map(director -> new DirectorGenresPair(director, movie.getGenres())).toList())
                .flatMap(Collection::stream)
                .map(directorGenres -> directorGenres.genres().stream().map(genre -> new DirectorGenrePair(directorGenres.director(), genre)).toList())
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(DirectorGenrePair::director, Collectors.groupingBy(DirectorGenrePair::genre, Collectors.counting())));

        directorGenreNumbers.forEach((director, genreCounts) -> {
            System.out.printf("%s\n", director.getName());
            genreCounts.forEach((genre, count) -> System.out.printf("\t%s: %s\n", genre.getName(), count));
        });
    }



}

record DirectorGenrePair(Director director, Genre genre){};
record DirectorGenresPair(Director director, List<Genre> genres) {};
