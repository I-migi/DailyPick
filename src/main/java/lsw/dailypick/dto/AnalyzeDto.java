package lsw.dailypick.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class AnalyzeDto {
    private List<String> genres;
    private Boolean prefersDomestic;
}
