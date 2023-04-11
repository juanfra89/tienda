package tienda.servicios;

import java.util.Collection;

import tienda.entidades.Producto;
import tienda.persistencia.ProductoDao;

public class ProductoService {
    private ProductoDao productoDao;

    public ProductoService() {
        productoDao = new ProductoDao();
    }

    public Collection<Producto> listarProductos() throws Exception {
        return productoDao.listaProducto();
    }

    public Collection<Producto> listarProductosPorPrecio(double precioMin, double precioMax) throws Exception {
        return productoDao.listaProductosPorPrecio(precioMin, precioMax);
    }

    public Collection<Producto> buscarPortatiles() throws Exception {
        return productoDao.buscarProductosPorTipo("Portátil");
    }

    public Producto buscarProductoPorCodigo(int codigo) throws Exception {
        return productoDao.buscarProductoPorCodigo(codigo);
    }

    public Producto buscarProductoMasBarato() throws Exception {
        return productoDao.buscarProductoMasBarato();
    }

    public void guardarProducto(Producto producto) throws Exception {
        productoDao.guardarProducto(producto);
    }



    public void editarProducto(Producto producto) throws Exception {
        productoDao.modificarProducto(producto);
    }
}