import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SimilarityService {

  baseURL = "http://localhost:8080/similarity";

  constructor(private http: HttpClient) { }

  getSimilarComputers(cores: number, 
                      freq: number, 
                      ramSize: number, 
                      storageType: string,
                      storageSize: number,
                      formFactor: string,
                      gpuSize: number,
                      price: number) {
    return this.http.get(this.baseURL + "/" + cores + "/" + freq + "/" + ramSize + "/" + storageType + "/" + storageSize + "/" + formFactor + "/" + gpuSize + "/" + price)
  }
}
