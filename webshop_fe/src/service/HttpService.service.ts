import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Products } from 'src/model/Products';

@Injectable({
    providedIn: 'root',
  })
export class HttpService {

     URL = "http://localhost:8080/products";

  constructor(private http: HttpClient) { }

getProducts(){
    return this.http.get<Products[]>(this.URL);
}

getProductById(id: string){
    const url = `${this.URL}/${id}`;
    return this.http.get<Products>(url)
}
}