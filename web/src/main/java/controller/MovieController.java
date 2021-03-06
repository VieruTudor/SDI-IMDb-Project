package controller;

import converter.MovieConverter;
import dto.DirectorsDto;
import dto.MovieDto;
import dto.MoviesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IMovieService;

@RestController
public class MovieController {
    @Autowired
    private IMovieService movieService;
    @Autowired
    private MovieConverter converter;

    @RequestMapping(value="/movies")
    public MoviesDto getAllMovies(){
        return new MoviesDto(converter.convertModelsToDtos(movieService.getAllMovies()));
    }

    @RequestMapping(value= "/movies", method = RequestMethod.POST)
    MovieDto addMovie(@RequestBody MovieDto dto){
        var movie = converter.convertDtoToModel(dto);
        var result = movieService.addMovie(movie);
        return converter.convertModelToDto(result);
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    MovieDto updateMovie(@PathVariable int id, @RequestBody MovieDto dto){
        if(movieService.getById(id) == null){
            return null;
        }
        var movie = converter.convertDtoToModel(dto);
        var result = movieService.updateMovie(movie);
        return converter.convertModelToDto(result);
    }
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "movies/filterByRating/{rating}", method = RequestMethod.GET)
    MoviesDto filterMovie(@PathVariable int rating){
        return new MoviesDto(converter.convertModelsToDtos(movieService.getMoviesWithRatingGreater(rating)));
    }

    @RequestMapping(value = "movies/reportMovies/{year}", method = RequestMethod.GET)
    Double reportMovies(@PathVariable int year){
        return movieService.getPercentageOfMoviesThisDecade(year);
    }

}
