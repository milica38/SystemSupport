import { Component, OnInit } from '@angular/core';
import { BayesService } from '../services/bayes.service';

@Component({
  selector: 'app-bayes',
  templateUrl: './bayes.component.html',
  styleUrls: ['./bayes.component.css']
})
export class BayesComponent implements OnInit {

  constructor(public service: BayesService) { }

  results: any[] = [];
  selectedSymptome: string = "";
  resultBox: boolean = false;

  ngOnInit(): void {
  }

  onSelected(value: any): void {
    this.selectedSymptome = value;
  }

  onDamage(): void {
    let params = {
      symptome: this.selectedSymptome
    }
    this.service.getDamages(params).subscribe((response: any) => {
      this.results = response;
      console.log(response);
    })
    this.resultBox = true;
  }

}
