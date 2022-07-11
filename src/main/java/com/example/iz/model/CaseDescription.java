package com.example.iz.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaseDescription implements CaseComponent {

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

    @Override
    public String toString() {
        return "cores=" + cores +
                ",cpuFrequency=" + cpuFrequency +
                ",ramSize=" + ramSize +
                ",storageType=" + storageType +
                ",storageSize=" + storageSize +
                ",formFactor=" + formFactor +
                ",gpuSize=" + gpuSize +
                ",price=" + price +
                ",brand=" + brand +
                ",name=" + name ;
    }

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("id",this.getClass());
    }
}
