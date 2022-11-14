package http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApiResponse {

    @JsonProperty("content")
    private String content = null;

    public ApiResponse(String content) {
        this.content = content;
    }



}
