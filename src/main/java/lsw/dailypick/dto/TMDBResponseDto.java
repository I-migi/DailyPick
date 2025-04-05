package lsw.dailypick.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TMDBResponseDto {

    private int page;
    private List<MovieDto> results;
    private int total_pages;
    private int total_results;
}
