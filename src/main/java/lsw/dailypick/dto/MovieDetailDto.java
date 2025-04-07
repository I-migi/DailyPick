package lsw.dailypick.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MovieDetailDto {
    private boolean adult;
    private String backdrop_path;
    private List<GenreDto> genreDtos;
    private Long id;
    private String overview;
    private String release_date;
    private String poster_path;
    private String title;

    private String original_title;
    private String original_language;
    private Double vote_average;
    private Integer runtime;
    private String homepage;
    private String status;

}
