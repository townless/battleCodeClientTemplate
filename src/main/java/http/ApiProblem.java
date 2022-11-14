package http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiProblem {

    @JsonProperty("status")
    private Integer status = null;


    @JsonProperty("content")
    private String content = null;


    public ApiProblem(Integer status, String description) {
        this.status = status;
        this.content = description;
    }

    @Override
    public String toString() {
        return "status: " + status + "\ncontent: " +  content;
    }
}
