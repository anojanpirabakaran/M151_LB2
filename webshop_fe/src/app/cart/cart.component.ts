import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/service/CartService.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public product : any = [];
  public grandTotal : number = 0;

  constructor(private cartService : CartService) { }

  ngOnInit(): void {
    this.cartService.getProduct()
    .subscribe(res => {
        this.product = res;
        this.grandTotal = this.cartService.getTotalPrice();
      }
    )
  }

}
