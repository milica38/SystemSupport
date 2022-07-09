import { Component, OnInit } from '@angular/core';
import { FuzzyService } from '../services/fuzzy.service';

@Component({
  selector: 'app-fuzzy',
  templateUrl: './fuzzy.component.html',
  styleUrls: ['./fuzzy.component.css']
})
export class FuzzyComponent implements OnInit {

  constructor(public service: FuzzyService) { }

  gpus: any[] = [];
  cpus: any[] = [];
  ssds: any[] = [];
  rams: any[] = [];
  results: any[] = [];
  brandsubs: string = "";
  
  selectedRamSize: string = "";
  selectedCoreNumber: string = "";
  selectedStorageSize: string = "";
  selectedGpuSize: string = "";
  selectedClockSpeed: string = "";

  resultBox: boolean = false;

  ngOnInit(): void {
    this.getAllGraphicsCards();
    this.getAllSSDs();
    this.getAllCPUs();
    this.getAllRams();
  }

  onSuggest(): void {
    let params = {
      coreNumber: this.selectedCoreNumber,
      ramSize: this.selectedRamSize,
      storageSize: this.selectedStorageSize,
      gpuSize: this.selectedGpuSize,
      cpuClockSpeed: this.selectedClockSpeed
    }
    this.service.SendResults(params).subscribe((response: any) => {
      this.results = response;
      console.log(response);
    })
    this.resultBox = true;
  }

  onSelectedRAM(value: any): void {
    this.selectedRamSize = value;
}

onSelectedSSD(value: any): void {
  this.selectedStorageSize = value;
}

onSelectedGPU(value: any): void {
  this.selectedGpuSize = value;
}

onSelectedCPU(selectedValue: any): void {
  this.selectedCoreNumber = selectedValue.core;
  this.selectedClockSpeed = selectedValue.clockSpeed;
  console.log( this.selectedClockSpeed,  this.selectedCoreNumber);
}

  getAllGraphicsCards(): void {
    this.service.getAllGraphicsCards().subscribe((response: any) => {
      this.gpus = response;
    })
  }

  getAllCPUs(): void {
    this.service.getAllCPUs().subscribe((response: any) => {
      this.cpus = response;
    })
    for(let i of this.cpus){
      this.brandsubs = i.brand.substring(6);
    }
  }

  getAllSSDs(): void {
    this.service.getAllSSDs().subscribe((response: any) => {
      this.ssds = response;
    })
  }

  getAllRams(): void {
    this.service.getAllRams().subscribe((response: any) => {
      this.rams = response;
    })
  }

}
