package com.egomaa.ems.emsbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
