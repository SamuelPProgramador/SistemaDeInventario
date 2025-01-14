package bbsg.inventarios.controlador;

import bbsg.inventarios.excepcion.RecursoNoEncontradoExepcion;
import bbsg.inventarios.modelo.Producto;
import bbsg.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/productos/{id}")
    public ResponseEntity <Producto> obtenerProductoPorId(@PathVariable int id){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto != null){
            return ResponseEntity.ok(producto);
        }
        else{
            throw  new RecursoNoEncontradoExepcion("No se ha encontrado ese id:" + id);
        }

    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable int id,
            @RequestBody Producto productoRecibido){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto == null){
            throw  new RecursoNoEncontradoExepcion("Producto no encontrado: " + id);
        }
        producto.setDescripcion(productoRecibido.getDescripcion());
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExistencia(productoRecibido.getExistencia());
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>>
    eliminarProducto(@PathVariable int id){
        Producto producto = productoServicio.buscarProductoPorId(id);
        if(producto == null){
            throw  new RecursoNoEncontradoExepcion("No se ha encontrado el id: " + id);
        }
        this.productoServicio.eliminarProductoPorId(producto.getIdProducto());
        Map<String, Boolean> repuesta = new HashMap<>();
        repuesta.put("Eliminado", Boolean.TRUE);
        return ResponseEntity.ok(repuesta);
    }


}
