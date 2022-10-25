import { Component, OnInit } from '@angular/core';
import { Products } from 'src/model/Products';
import { HttpService } from 'src/service/HttpService.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Products[] = [];

  constructor(private httpService: HttpService) {}

  ngOnInit(): void {
   this.httpService.getProducts().subscribe((data) => {
    this.products = data;
    console.log(data)
   })
  }

}
