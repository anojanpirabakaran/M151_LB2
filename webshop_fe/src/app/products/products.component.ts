import { Component, OnInit } from '@angular/core';
import { Products } from 'src/model/Products';
import { HttpService } from 'src/service/HttpService.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Products[] | undefined;

  constructor(private httpService: HttpService, private route: Router) {}

  ngOnInit(): void {
   this.httpService.getProducts().subscribe((data) => {
    this.products = data;
   })
  }

  navigateRoute(productLink: Products){
    this.route.navigate(['/product-detail', productLink.id])
  }

}
