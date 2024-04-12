package ru.task.transport.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Table(name = "transport_table")
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class TransportEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "year_out")
    private Integer year;

    @Column(name = "trailer")
    private Boolean hasTrailer;

    @Column(name = "type")
    private String transportType;

    @Column(name = "category")
    private String category;

    @Column(name = "gov_number")
    private String govNumber;
}
