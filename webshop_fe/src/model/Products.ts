
import { Types } from "src/enum/enum"
import { Brand } from "./Brand"

export interface Products {
    id: String,
    price: number,
    description: String,
    imageLink: String
    type: Types,
    brand: Brand
}