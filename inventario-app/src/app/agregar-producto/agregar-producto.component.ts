import { Component } from '@angular/core';
import { Producto } from '../producto';
import { Router } from '@angular/router';
import { ProductoService } from '../producto.service';

@Component({
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
  styleUrls: ['./agregar-producto.component.css']
})
export class AgregarProductoComponent {
    producto: Producto = new Producto();

  constructor(private productoServicio: ProductoService,
    private enrutador: Router) {}

  onSubmit(){
    this.guardarProducto();
  }
  guardarProducto() {
    this.productoServicio.agregarProducto(this.producto).subscribe(
      {
        next: (datos) => {
          this.irListaProduto();
      },
      error: (error: any) => {console.error(error);}
    }
    );
  }

  irListaProduto() {
    this.enrutador.navigate(["/productos"]);

  }
}
