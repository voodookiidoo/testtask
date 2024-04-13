package ru.task.transport.dto;

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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateRequestDTO {

    private Integer id;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String name;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String brand;

    @NotNull
    @Min(value = 1900)
    private Integer year;

    @NotNull(message = Constants.EMPTY_FIELD_MESSAGE)
    private Boolean hasTrailer;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)

    private String transportType;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)
    private String category;

    @NotEmpty(message = Constants.EMPTY_FIELD_MESSAGE)

    private String govNumber;

}
