package bbsg.inventarios.servicio;

import bbsg.inventarios.modelo.Producto;

import java.util.List;

public class ProductoServicio implements IProductoServicio{
    @Override
    public List<Producto> listarProductos() {
        return List.of();
    }

    @Override
    public Producto buscarProductoPorId(Integer idProducto) {
        return null;
    }

    @Override
    public void guardarProducto(Producto producto) {

    }

    @Override
    public void eliminarProductoPorId(Integer idProducto) {

    }
}
