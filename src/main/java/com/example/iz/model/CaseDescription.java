package com.example.iz.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CaseDescription implements CaseComponent {

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

    public CaseDescription(String cores, String cpuFrequency, String cpuBrand, String cpuName, String cpuModel, String ramSize, String hddMemorySize, String usbSlots, String formFactor, String gpuSize) {
        this.cores = cores;
        this.cpuFrequency = cpuFrequency;
        this.cpuBrand = cpuBrand;
        this.cpuName = cpuName;
        this.cpuModel = cpuModel;
        this.ramSize = ramSize;
        this.hddMemorySize = hddMemorySize;
        this.usbSlots = usbSlots;
        this.formFactor = formFactor;
        this.gpuSize = gpuSize;
    }

    public CaseDescription() {}

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("id",this.getClass());
    }
}
