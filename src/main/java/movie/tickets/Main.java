package movie.tickets;

import movie.tickets.lib.Injector;
import movie.tickets.model.Movie;
import movie.tickets.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("movie.tickets");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
