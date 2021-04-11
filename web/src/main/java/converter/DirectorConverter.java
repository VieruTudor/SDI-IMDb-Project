package converter;

import dto.DirectorDto;
import model.Director;

public class DirectorConverter extends BaseConverter<Director, DirectorDto> {
    @Override
    public Director convertDtoToModel(DirectorDto dto) {
        var model = new Director();
        model.setAge(dto.getAge());
        model.setName(dto.getName());
        model.setId(dto.getId());
        return model;
    }

    @Override
    public DirectorDto convertModelToDto(Director director) {
        var dto = new DirectorDto();
        dto.setAge(director.getAge());
        dto.setId(director.getId());
        dto.setName(director.getName());
        return dto;
    }
}
