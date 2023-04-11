package tienda.persistencia;

import tienda.entidades.Producto;

import java.util.ArrayList;
import java.util.Collection;

public final class ProductoDao extends DAO{

    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar un producto");
            }
            String sql = "INSERT INTO producto (codigo, nombre, precio, codigo_fabricante) " +
                    "VALUES (" + producto.getCodigo() + ", '" + producto.getNombre() + "', " +
                    producto.getPrecio() + ", " + producto.getCodigoFabricante() + ");";
            instarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar un producto");
            }
            String sql = "UPDATE producto SET nombre = '" + producto.getNombre() + "', precio = " + producto.getPrecio() + ", codigo_fabricante = " + producto.getCodigoFabricante() + " WHERE codigo = " + producto.getCodigo();
            instarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarProducto(int codigo) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE codigo = "+ codigo;
            instarModificarEliminar(sql);
        }catch (Exception e){
            throw e;
        }
    }


    public Producto buscarProductoPorNombre(int codigo)throws Exception{
        Producto producto = null;
        try {
            String sql = "SELECT * FROM producto WHERE codigo = " + codigo + ";";
            consultarBase(sql);
            while (resultado.next()){
                producto = new Producto(resultado.getInt(1), resultado.getString("nombre"), resultado.getDouble("precio"), resultado.getInt("codigo_fabricante"));
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getNString("nombre"));
                producto.setPrecio(resultado.getInt("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
            }
            desconectarBase();
            return producto;
        }catch (Exception e){
            desconectarBase();
            throw e;
        }

    }
    public Collection<Producto> listaProducto() throws Exception {
        try {
            String sql = "SELECT * FROM producto;";
            consultarBase(sql);
            Collection<Producto> productos = new ArrayList<>();
            while (resultado.next()) {
                Producto producto = new Producto(resultado.getInt(1), resultado.getString("nombre"), resultado.getDouble("precio"), resultado.getInt("codigo_fabricante"));
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    public Collection<Producto> listaProductosPorPrecio(double precioMin, double precioMax) throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE precio >= " + precioMin + " AND precio <= " + precioMax;
            consultarBase(sql);
            Collection<Producto> productos = new ArrayList<>();
            while (resultado.next()) {
                Producto producto = new Producto(resultado.getInt(1), resultado.getString("nombre"), resultado.getDouble("precio"), resultado.getInt("codigo_fabricante"));
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    public Producto buscarProductoPorCodigo(int codigo) throws Exception {
        Producto producto = null;
        try {
            String sql = "SELECT * FROM producto WHERE codigo = " + codigo + ";";
            consultarBase(sql);
            if (resultado.next()) {
                producto = new Producto(resultado.getInt(1), resultado.getString("nombre"), resultado.getDouble("precio"), resultado.getInt("codigo_fabricante"));
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getNString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    public Producto buscarProductoMasBarato() throws Exception {
        Producto producto = null;
        try {
            String sql = "SELECT * FROM producto ORDER BY precio ASC LIMIT 1;";
            consultarBase(sql);
            if (resultado.next()) {
                producto = new Producto(resultado.getInt(1), resultado.getString("nombre"), resultado.getDouble("precio"), resultado.getInt("codigo_fabricante"));
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getNString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
            }
            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
    public Collection<Producto> buscarProductosPorTipo(String tipo) throws Exception {
        try {
            String sql = "SELECT * FROM producto WHERE tipo = '" + tipo + "';";
            consultarBase(sql);
            Collection<Producto> productos = new ArrayList<>();
            while (resultado.next()) {
                Producto producto = new Producto(resultado.getInt(1), resultado.getString("nombre"), resultado.getDouble("precio"), resultado.getInt("codigo_fabricante"));
                producto.setCodigo(resultado.getInt("codigo"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getDouble("precio"));
                producto.setCodigoFabricante(resultado.getInt("codigo_fabricante"));
                productos.add(producto);
            }
            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
}
