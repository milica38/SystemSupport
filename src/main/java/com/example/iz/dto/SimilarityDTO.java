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

    private String cores;
    private String cpuFrequency;
    private String cpuBrand;
    private String cpuName;
    private String cpuModel;
    private String ramSize;
    private String hddMemorySize;
    private String usbSlots;
    private String formFactor;
    private String gpuSize;
}
