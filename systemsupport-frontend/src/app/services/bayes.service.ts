import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BayesService {

  baseURL = "http://localhost:8080/bayes";

  constructor(private http: HttpClient) { }

  getDamages(obj: any) {
    return this.http.post(this.baseURL, obj);
  }
}
