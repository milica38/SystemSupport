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
  selectedRamSize: string = "";
  selectedCoreNumber: string = "";
  selectedStorageSize: string = "";
  selectedGpuSize: string = "";
  selectedClockSpeed: string = "";

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
      cpuClockSpeed: 1
    }
    this.service.SendResults(params).subscribe((response: any) => {
      console.log(response);
    })
  }

  onSelectedRAM(value: any): void {
    this.selectedRamSize = value;
    console.log(this.selectedRamSize);
}

onSelectedSSD(value: any): void {
  this.selectedStorageSize = value;
  console.log(this.selectedStorageSize);
}

onSelectedGPU(value: any): void {
  this.selectedGpuSize = value;
  console.log(this.selectedGpuSize);
}

onSelectedCPU(value: any): void {
  this.selectedCoreNumber = value;
  // this.selectedClockSpeed = value2;
  console.log(value);
}

  getAllGraphicsCards(): void {
    this.service.getAllGraphicsCards().subscribe((response: any) => {
      this.gpus = response;
      console.log(this.gpus);
    })
  }

  getAllCPUs(): void {
    this.service.getAllCPUs().subscribe((response: any) => {
      this.cpus = response;
      console.log(this.cpus);
    })
  }

  getAllSSDs(): void {
    this.service.getAllSSDs().subscribe((response: any) => {
      this.ssds = response;
      console.log(this.ssds);
    })
  }

  getAllRams(): void {
    this.service.getAllRams().subscribe((response: any) => {
      this.rams = response;
      console.log(this.rams);
    })
  }

}
