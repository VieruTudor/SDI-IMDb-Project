package converter;

import dto.BaseDto;
import model.BaseEntity;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class BaseConverter<Model extends BaseEntity<? extends Serializable>, Dto extends BaseDto<?>> implements Converter<Model, Dto> {
    // different 'cuz Playsin implements BaseEntity<Pair<whatever>>
    public Set<? extends Serializable> convertModelsToIDs(Set<Model> models) {
        return models.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Object> convertDTOsToIDs(Set<Dto> dtos) {
        return dtos.stream()
                .map(BaseDto::getId)
                .collect(Collectors.toSet());
    }

    public Set<Dto> convertModelsToDtos(Iterable<Model> models) {
        return StreamSupport.stream(models.spliterator(), false)
                .map(this::convertModelToDto)
                .collect(Collectors.toSet());
    }
}
