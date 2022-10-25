import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Products } from 'src/model/Products';

@Injectable({
    providedIn: 'root',
  })
export class HttpService {

     url = "http://localhost:8080/products";

  constructor(private http: HttpClient) { }

getProducts(){
    return this.http.get<Products[]>(this.url);
}
}