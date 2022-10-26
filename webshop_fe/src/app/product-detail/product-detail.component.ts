import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Products } from 'src/model/Products';
import { HttpService } from 'src/service/HttpService.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(private active: ActivatedRoute, private httpService: HttpService) { }

product: Products | undefined;

  ngOnInit(): void {
    const id = this.active.snapshot.paramMap.get('id');
    console.log(id)
    this.httpService.getProductById(id!).subscribe(data => this.product = data)
    
  }

}
