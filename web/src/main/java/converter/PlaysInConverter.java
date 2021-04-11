package converter;

import dto.PlaysInDto;
import model.PlaysIn;

public class PlaysInConverter extends BaseConverter<PlaysIn, PlaysInDto> {
    @Override
    public PlaysIn convertDtoToModel(PlaysInDto dto) {
        var model = new PlaysIn();
        model.setId(dto.getId());
        model.setRole(dto.getRole());
        model.setMovieID(dto.getMovieID());
        model.setActorID(dto.getActorID());
        return model;
    }

    @Override
    public PlaysInDto convertModelToDto(PlaysIn playsIn) {
        var dto = new PlaysInDto();
        dto.setId(playsIn.getId());
        dto.setRole(playsIn.getRole());
        dto.setMovieID(playsIn.getMovieID());
        dto.setActorID(playsIn.getActorID());
        return dto;
    }
}
