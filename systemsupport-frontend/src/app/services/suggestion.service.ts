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

  getAllMotherboards() {
    return this.http.get(this.baseURL + "/recommendation/motherboards");
  }

  getAllCpus() {
    return this.http.get(this.baseURL + "/fuzzyLogic/cpu");
  }

  getAllSsds() {
    return this.http.get(this.baseURL + "/fuzzyLogic/ssd");
  }

  getAllRams() {
    return this.http.get(this.baseURL + "/fuzzyLogic/ram");
  }

  getAllHeadphones() {
    return this.http.get(this.baseURL + "/recommendation/headphones");
  }

  getAllKeyboards() {
    return this.http.get(this.baseURL + "/recommendation/keyboards");
  }

  getRecommendationForGpu(obj: any) {
    return this.http.post(this.baseURL + "/recommendation/gpu/search", obj);
  }

  getRecommendationForCpu(obj: any) {
    return this.http.post(this.baseURL + "/recommendation/cpu/search", obj);
  }

  getRecommendationForMouse(obj: any) {
    return this.http.post(this.baseURL + "/recommendation/mouse/search", obj);
  }

  getRecommendationForCooler(obj: any) {
    return this.http.post(this.baseURL + "/recommendation/cooler/search", obj);
  }

}
