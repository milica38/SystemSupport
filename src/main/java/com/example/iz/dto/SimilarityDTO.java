package com.example.iz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimilarityDTO {

    private int cores;
    private double cpuFrequency;

    private int ramSize;
    private String storageType;
    private int storageSize;

    private String formFactor;
    private int gpuSize;
    private int price;

    private String brand;

    private String name;

}
