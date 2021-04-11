package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DirectorDto extends BaseDto<Integer>{
    private String name;
    private int age;
}