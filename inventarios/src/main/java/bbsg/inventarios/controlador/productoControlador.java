package bbsg.inventarios.controlador;

import bbsg.inventarios.modelo.Producto;
import bbsg.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//http://localhost:8080/inventario-app
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:4200")
public class productoControlador {
    private static  final Logger logger =
            LoggerFactory.getLogger(productoControlador.class);

    @Autowired
    private ProductoServicio productoServicio;

    //htpp://localhost:8080/inventario-app/producto
    @GetMapping("/productos")
    public List<Producto> obtenerProducto(){
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos Obtenido:");
        productos.forEach((producto -> logger.info(producto.toString())));
        return productos;
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@RequestBody Producto producto){
        logger.info("Producto a agregar" + producto);
        return  this.productoServicio.guardarProducto(producto);
    }

}
