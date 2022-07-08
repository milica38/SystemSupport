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

  ngOnInit(): void {
    this.getAllGraphicsCards();
    this.getAllSSDs();
    this.getAllCPUs();
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

}
