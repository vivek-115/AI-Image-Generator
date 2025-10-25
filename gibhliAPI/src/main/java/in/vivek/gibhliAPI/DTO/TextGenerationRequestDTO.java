package in.vivek.gibhliAPI.DTO;

import lombok.Data;

@Data
public class TextGenerationRequestDTO {
    private String prompt;
    private String style;

}
