package lsw.dailypick.service.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lsw.dailypick.dto.MovieDetailDto;
import lsw.dailypick.dto.MovieDto;
import lsw.dailypick.dto.TMDBResponseDto;
import lsw.dailypick.entity.Genre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TMDbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();


    public Object getMoviesByGenre(String genreId) {
        String url = "https://api.themoviedb.org/3/discover/movie"
                + "?api_key=" + apiKey
                + "&with_genres=" + genreId
                + "&sort_by=popularity.desc"
                + "&language=ko-KR"
                + "&page=1"
                + "&region=ko-KR";

        return restTemplate.getForObject(url, Object.class);
    }

    public List<MovieDto> getPopularTop10Movies() {
        String url = "https://api.themoviedb.org/3/movie/popular"
                + "?api_key=" + apiKey
                + "&language=ko-KR"
                + "&page=1";
        TMDBResponseDto response =  restTemplate.getForObject(url, TMDBResponseDto.class);
        return response.getResults().subList(0, 10);
    }

    public List<MovieDto> getPopularMoviesByGenreAndCountry(Long genreId, Boolean preferDomestic) {

        String originalLanguage = preferDomestic ? "ko" : "en";
        log.info("genreId:{}", genreId);
        String url = "https://api.themoviedb.org/3/discover/movie"
                + "?api_key=" + apiKey
                + "&language=ko-KR"
                + "&with_genres=" + genreId
                + "&sort_by=popularity.desc"
                + "&with_original_language=" + originalLanguage;
        TMDBResponseDto response =  restTemplate.getForObject(url, TMDBResponseDto.class);
        return response.getResults().subList(0, 10);
    }

    public int convertGenreId(String genre) {
        return switch (genre) {
            case "액션" -> 28;
            case "모험" -> 12;
            case "애니메이션" -> 16;
            case "코미디" -> 35;
            case "범죄" -> 80;
            case "다큐멘터리" -> 99;
            case "드라마" -> 18;
            case "가족" -> 10751;
            case "판타지" -> 14;
            case "역사" -> 36;
            case "공포" -> 27;
            case "음악" -> 10402;
            case "미스터리" -> 9648;
            case "로맨스" -> 10749;
            case "SF" -> 878;
            case "TV 영화" -> 10770;
            case "스릴러" -> 53;
            case "전쟁" -> 10752;
            case "서부" -> 37;
            default -> 0;
        };
    }

    public MovieDetailDto getMovieDetail(String movieId) {
        String url = "https://api.themoviedb.org/3/movie/"
                + movieId
                + "?api_key=" + apiKey
                + "&language=ko-KR";
        return restTemplate.getForObject(url, MovieDetailDto.class);

    }
}
