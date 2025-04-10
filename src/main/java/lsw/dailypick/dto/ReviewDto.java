package lsw.dailypick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ReviewDto {

    private String userName;
    private Double rating;
    private String comment;
}
