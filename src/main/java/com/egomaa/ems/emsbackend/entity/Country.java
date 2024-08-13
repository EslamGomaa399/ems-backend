package com.egomaa.ems.emsbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    @Id
    private String countryId;

    private String countryName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

}
