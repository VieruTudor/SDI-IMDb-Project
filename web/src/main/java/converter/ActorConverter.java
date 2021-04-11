package converter;

import dto.ActorDto;
import model.Actor;

public class ActorConverter extends BaseConverter<Actor, ActorDto> {
    @Override
    public Actor convertDtoToModel(ActorDto dto) {
        var model = new Actor();
        model.setId(dto.getId());
        model.setAge(dto.getAge());
        model.setFame(dto.getFame());
        model.setName(dto.getName());
        return model;
    }

    @Override
    public ActorDto convertModelToDto(Actor actor) {
        var dto = new ActorDto();
        dto.setId(actor.getId());
        dto.setAge(actor.getAge());
        dto.setFame(actor.getFame());
        dto.setName(actor.getName());
        return dto;
    }
}
