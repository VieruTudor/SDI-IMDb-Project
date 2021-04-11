package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MovieDto extends BaseDto<Integer>{
    private Integer id;
    private String name;
    private int rating;
    private int year;
    private int directorId;
}