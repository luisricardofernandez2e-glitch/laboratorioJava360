package com.axity.parquededinosaurios.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Ticket {
    private Long id;
    private int touristId;
    private String categoria;
    private  Double precio;
    private LocalDate fecha;

}
