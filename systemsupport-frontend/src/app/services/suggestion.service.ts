import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SuggestionService {

  baseURL = "http://localhost:8080";

  constructor(private http: HttpClient) { }

  getComponents(type: string) {
    return this.http.get(this.baseURL + "/recommendation/" + type);
  }
}
