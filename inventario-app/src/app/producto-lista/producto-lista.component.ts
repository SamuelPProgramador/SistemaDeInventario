import { Component } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import {  Router } from '@angular/router';

@Component({
  selector: 'app-producto-lista',
  templateUrl: './producto-lista.component.html',
})
export class ProductoListaComponent {
  pruductos: Producto[];

  constructor(private productoServicio: ProductoService,
    private enrutador: Router) {}

  ngOnInit() {
    //Cargamos los productos
    this.obtenerProductos();
  }
  private obtenerProductos() {
    //consumir los datos del observable
    this.productoServicio.obtenerProductoLista().subscribe(
      (datos =>{
        this.pruductos = datos;
      })
    );
  }
  editarProducto(id: number){
    this.enrutador.navigate(["editar-producto", id]);
  }

}
