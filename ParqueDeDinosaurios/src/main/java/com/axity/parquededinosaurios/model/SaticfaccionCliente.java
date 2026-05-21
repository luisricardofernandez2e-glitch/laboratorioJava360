package com.axity.parquededinosaurios.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class SaticfaccionCliente {
    private int turistaId;
    private String nombreRecinto;
    private int puntuacion; //1-5
}
