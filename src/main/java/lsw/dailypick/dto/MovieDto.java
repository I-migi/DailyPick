package lsw.dailypick.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class MovieDto {
    private int id;
    private String original_language;
    private String original_title;
    private String poster_path;
    private String release_date;
    private String title;
    private List<Integer> genre_ids;

}
