package dto;

import lombok.*;

import java.io.Serializable;

/**
 * Not created by radu.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDto<T> implements Serializable {
    private T id;
}
