package dto;

import lombok.*;
import model.Pair;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlaysInDto extends BaseDto<Pair<Integer, Integer>>{
    private int movieID;
    private int actorID;
    private String role;
}