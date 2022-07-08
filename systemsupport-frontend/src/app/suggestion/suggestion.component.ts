import { Component, OnInit } from '@angular/core';
import { SuggestionService } from '../services/suggestion.service';

@Component({
  selector: 'app-suggestion',
  templateUrl: './suggestion.component.html',
  styleUrls: ['./suggestion.component.css']
})
export class SuggestionComponent implements OnInit {

  constructor(public service: SuggestionService) { }

  components: any[] = []
  selectedVal: string = "";

  ngOnInit(): void {
    //this.getComponents(this.selectedVal);
  }

  getComponents(componentType: string): void {
    this.service.getComponents(componentType).subscribe((response: any) => {
      this.components = response;
      console.log(this.components);
    })
  }

  onSelected(value: string): void {
      this.selectedVal = value;
      console.log(this.selectedVal);
  }

  onSubmit(): void {
    this.getComponents(this.selectedVal);
  }

}
