package com.example.iz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotherboardDTO {
    private String name;
    private String brand;
    private String usbSlots;
    private String chipset;
    private String formFactor;
}
