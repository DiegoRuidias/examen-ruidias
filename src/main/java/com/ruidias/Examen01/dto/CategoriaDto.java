package com.ruidias.Examen01.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDto {
    private int id;
    private String nombre;
    private String descripcion;
}
