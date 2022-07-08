import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FuzzyService {

  baseURL = "http://localhost:8080/fuzzyLogic";

  constructor(private http: HttpClient) { }

  getAllGraphicsCards() {
    return this.http.get(this.baseURL + "/gpu");
  }

  getAllCPUs() {
    return this.http.get(this.baseURL + "/cpu");
  }

  getAllSSDs() {
    return this.http.get(this.baseURL + "/ssd");
  }

  getAllRams() {
    return this.http.get(this.baseURL + "/ram");
  }

  SendResults(obj: any) {
    return this.http.post(this.baseURL, obj);
  }

}
