package ru.task.transport.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateRequestDTO {

    private Integer id;

    private String name;

    private String brand;

    private Integer year;

    private Boolean hasTrailer;

    private String transportType;

    private String category;

    private String govNumber;

}
