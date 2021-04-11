package converter;

import dto.MovieDto;
import model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto> {
    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        var model = new Movie();
        model.setId(dto.getId());
        model.setDirectorId(dto.getDirectorId());
        model.setName(dto.getName());
        model.setRating(dto.getRating());
        model.setYear(dto.getYear());
        return model;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        var dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setDirectorId(movie.getDirectorId());
        dto.setName(movie.getName());
        dto.setRating(movie.getRating());
        dto.setYear(movie.getYear());
        return dto;
    }
}
