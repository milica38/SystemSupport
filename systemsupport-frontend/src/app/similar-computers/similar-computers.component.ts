import { Component, OnInit } from '@angular/core';
import { SimilarityService } from '../services/similarity.service';

@Component({
  selector: 'app-similar-computers',
  templateUrl: './similar-computers.component.html',
  styleUrls: ['./similar-computers.component.css']
})
export class SimilarComputersComponent implements OnInit {

  constructor(public service: SimilarityService) { }

  resultBox: boolean = false;
  computers: any[] = [];

  cores: number = 0;
  freq: number = 0;
  ramSize: number = 0;
  storageType: string = "";
  storageSize: number = 0;
  formFactor: string = "";
  gpuSize: number = 0;
  price: number = 0;

  ngOnInit(): void {

  }

onSelectedStorage(value: string): void {
  this.storageType = value;
  console.log(this.storageType);
}

onSelectedFormFactor(value: string): void {
  this.formFactor = value;
  console.log(this.formFactor);
}

  onFind(): void {
    this.resultBox = true;
    this.getSimilarComputers(this.cores, this.freq, this.ramSize, this.storageType, this.storageSize, this.formFactor, this.gpuSize, this.price);
  }

  getSimilarComputers(cores: number, 
                      freq: number, 
                      ramSize: number, 
                      storageType: string,
                      storageSize: number,
                      formFactor: string,
                      gpuSize: number,
                      price: number): void {

    this.service.getSimilarComputers(cores, freq, ramSize, storageType, storageSize, formFactor, gpuSize, price).subscribe((response: any) => {
      this.computers = response;
      console.log(this.computers);
    })
  }

}
