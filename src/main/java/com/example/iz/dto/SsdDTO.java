package com.example.iz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SsdDTO {
    private String name;
    private String model;
    private String brand;
    private String storageSize;
}
