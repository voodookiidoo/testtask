package ru.task.transport.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndexResponseDTO {

    private Integer id;

    private String name;

    private String brand;

    private Integer year;

    @JsonProperty("trailer")
    private Boolean hasTrailer;

    @JsonProperty("type")
    private String transportType;

    private String category;

    @JsonProperty("gov_number")
    private String govNumber;

}
