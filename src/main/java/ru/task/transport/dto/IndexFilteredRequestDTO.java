package ru.task.transport.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IndexFilteredRequestDTO {

    private String name;

    private String brand;

    private Integer year;

    @JsonProperty("type")
    private String transportType;

    private String category;

    @JsonProperty("gov_number")
    private String govNumber;

}
