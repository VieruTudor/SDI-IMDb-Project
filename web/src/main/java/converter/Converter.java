package converter;

import dto.BaseDto;
import model.BaseEntity;

import java.io.Serializable;

public interface Converter<Model extends BaseEntity<? extends Serializable>, Dto extends BaseDto<?>> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}
