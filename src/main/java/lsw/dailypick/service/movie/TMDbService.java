package lsw.dailypick.service.movie;

import lombok.RequiredArgsConstructor;
import lsw.dailypick.dto.MovieDto;
import lsw.dailypick.dto.TMDBResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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

    public Object getPopularTop10Movies() {
        String url = "https://api.themoviedb.org/3/movie/popular"
                + "?api_key=" + apiKey
                + "&language=ko-KR"
                + "&page=1";
        TMDBResponseDto response =  restTemplate.getForObject(url, TMDBResponseDto.class);

        List<MovieDto> popularMovies = response.getResults().subList(0, 10);
        return popularMovies;

    }
}
