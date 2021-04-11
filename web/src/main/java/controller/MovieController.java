package controller;

import converter.MovieConverter;
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

    @RequestMapping(value="/movie")
    public MoviesDto getAllMovies(){
        return new MoviesDto(converter.convertModelsToDtos(movieService.getAllMovies()));
    }

    @RequestMapping(value= "/movie", method = RequestMethod.POST)
    MovieDto addMovie(@RequestBody MovieDto dto){
        var movie = converter.convertDtoToModel(dto);
        var result = movieService.addMovie(movie);
        return converter.convertModelToDto(result);
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.PUT)
    MovieDto updateMovie(@PathVariable int id, @RequestBody MovieDto dto){
        if(movieService.getById(id) == null){
            return null;
        }
        var movie = converter.convertDtoToModel(dto);
        var result = movieService.updateMovie(movie);
        return converter.convertModelToDto(result);
    }
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
