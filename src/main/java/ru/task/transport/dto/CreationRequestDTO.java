package ru.task.transport.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.task.transport.config.Constants;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class CreationRequestDTO {

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String name;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String brand;

    @NotNull(message = Constants.EMPTY_FIELD_MESSAGE)
    @Min(value = 1900)
    private Integer year;

    @JsonProperty("trailer")

    @NotNull(message = Constants.EMPTY_FIELD_MESSAGE)
    private Boolean hasTrailer;

    @JsonProperty("type")
    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String transportType;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String category;

    @JsonProperty("gov_number")
    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String govNumber;

}
