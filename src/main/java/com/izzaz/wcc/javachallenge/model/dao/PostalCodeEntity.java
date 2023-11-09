package com.izzaz.wcc.javachallenge.model.dao;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "postcodelatlng")
public class PostalCodeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String postcode;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
