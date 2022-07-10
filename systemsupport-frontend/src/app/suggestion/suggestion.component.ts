import { Component, OnInit } from '@angular/core';
import { SuggestionService } from '../services/suggestion.service';

@Component({
  selector: 'app-suggestion',
  templateUrl: './suggestion.component.html',
  styleUrls: ['./suggestion.component.css']
})
export class SuggestionComponent implements OnInit {

  constructor(public service: SuggestionService) { }

  motherboards: any[] = [];
  cpus: any[] = [];
  ssds: any[] = [];
  rams: any[] = [];
  keyboards: any[] = [];
  headphones: any[] = [];
  
  components: any[] = [];
  resultsGpu: any[] = [];
  resultsCpu: any[] = [];
  resultsMouse: any[] = [];
  resultsCooler: any[] = [];

  selectedVal: string = "";
  selectedUsbSlotsForGpu: string = "";
  selectedCpuFrequencyForGpu: string = "";
  selectedRamSizeForCpu: string = "";
  selectedSsdSizeForCpu: string = "";
  selectedKeyboardForMouse: string = "";
  selectedHpSensitivitiesForMouse: string = "";
  selectedCpuFrequencyForCooler: string = "";

  ngOnInit(): void {
    this.getMotherboards();
    this.getCpus();
    this.getRams();
    this.getSsds();
    this.getHeadphones();
    this.getKeyboards();
  }

  //#################GET COMPONENTS FOR BOXES#####################

  getComponents(componentType: string): void {
    this.service.getComponents(componentType).subscribe((response: any) => {
      this.components = response;
      //console.log(this.components);
    })
  }

  getMotherboards(): void {
    this.service.getAllMotherboards().subscribe((response: any) => {
      this.motherboards = response;
    })
  }

  getCpus(): void {
    this.service.getAllCpus().subscribe((response: any) => {
      this.cpus = response;
    })
  }

  getRams(): void {
    this.service.getAllRams().subscribe((response: any) => {
      this.rams = response;
    })
  }

  getSsds(): void {
    this.service.getAllSsds().subscribe((response: any) => {
      this.ssds = response;
    })
  }

  getKeyboards(): void {
    this.service.getAllKeyboards().subscribe((response: any) => {
      this.keyboards = response;
    })
  }

  getHeadphones(): void {
    this.service.getAllHeadphones().subscribe((response: any) => {
      this.headphones = response;
    })
  }

//#################RECOMMENDATIONS-RESULTS-BUTTONS#####################

  onSubmit(): void {
    this.getComponents(this.selectedVal);
  }

  getRecoommendedGpus(): void {
    let params = {
      motherboardUSBslots: this.selectedUsbSlotsForGpu,
      cpuFrequency: this.selectedCpuFrequencyForGpu
    }
    this.service.getRecommendationForGpu(params).subscribe((response: any) => {
      this.resultsGpu = response;
      console.log(response);
    })
  }

  getRecoommendedCpus(): void {
    let params = {
      ssdMemorySize: this.selectedSsdSizeForCpu,
      ramSize: this.selectedRamSizeForCpu
    }
    this.service.getRecommendationForCpu(params).subscribe((response: any) => {
      this.resultsCpu = response;
      console.log(response);
    })
  }

  getRecoommendedMouses(): void {
    let params = {
      hpSensitivities: this.selectedHpSensitivitiesForMouse,
      keyboardType: this.selectedKeyboardForMouse
    }
    this.service.getRecommendationForMouse(params).subscribe((response: any) => {
      this.resultsMouse = response;
      console.log(response);
    })
  }

  getRecoommendedCoolers(): void {
    let params = {
      frequency: this.selectedCpuFrequencyForCooler
    }
    this.service.getRecommendationForCooler(params).subscribe((response: any) => {
      this.resultsCooler = response;
      console.log(response);
    })
  }  

  //################# ON SELECTED FOR BOXES#####################

  onSelectedSlotsForGpu(value: string): void {
    this.selectedUsbSlotsForGpu = value;
    console.log(this.selectedUsbSlotsForGpu);
  }

  onSelectedFrequencyForGpu(value: string): void {
    this.selectedCpuFrequencyForGpu = value;
    console.log(this.selectedCpuFrequencyForGpu);
  }

  onSelectedRamSizeForCpu(value: string): void {
    this.selectedRamSizeForCpu = value;
    console.log(this.selectedRamSizeForCpu);
  }

  onSelectedSsdSizeForCpu(value: string): void {
    this.selectedSsdSizeForCpu = value;
    console.log(this.selectedSsdSizeForCpu);
  }

  onSelectedHpSensForMouse(value: string): void {
    this.selectedHpSensitivitiesForMouse = value;
    console.log(this.selectedHpSensitivitiesForMouse);
  }

  onSelectedKbTypeForMouse(value: string): void {
    this.selectedKeyboardForMouse = value;
    console.log(this.selectedKeyboardForMouse);
  }

  onSelectedCpuForCooler(value: string): void {
    this.selectedCpuFrequencyForCooler = value;
    console.log(this.selectedCpuFrequencyForCooler);
  }

  onSelected(value: string): void {
      this.selectedVal = value;
      console.log(this.selectedVal);
  }

}
