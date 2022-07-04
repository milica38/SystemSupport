package com.example.iz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CpuDTO {
    private String name;
    private String brand;
    private String model;
    private String clockSpeed;
    private String coreNumber;
}
