package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ActorDto extends BaseDto<Integer>{
    private Integer id;
    private String name;
    private int age;
    private int fame;
}