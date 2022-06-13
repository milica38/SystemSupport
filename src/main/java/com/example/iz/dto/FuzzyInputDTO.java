package com.example.iz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuzzyInputDTO {

    private int coreNumber;
    private int ramSize;
    private int storageSize;
    private int gpuSize;
    private int cpuClockSpeed;
}
