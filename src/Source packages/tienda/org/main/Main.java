package org.main;

import java.util.Scanner;
import java.util.Collection;
import tienda.entidades.Producto;
import tienda.servicios.ProductoService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductoService productoService = new ProductoService();
        boolean salir = false;

        while (!salir) {
            System.out.println("Menu:");
            System.out.println("a) Lista de nombres de todos los productos.");
            System.out.println("b) Lista de nombres y precios de todos los productos.");
            System.out.println("c) Lista de productos cuyo precio está entre 120 y 202.");
            System.out.println("d) Lista de todos los portátiles.");
            System.out.println("e) Nombre y precio del producto más barato.");
            System.out.println("f) Ingresar un producto.");
            System.out.println("g) Ingresar un fabricante.");
            System.out.println("h) Editar un producto.");
            System.out.println("i) Salir.");

            String opcion = scanner.nextLine();

            try {
                switch (opcion) {
                    case "a":
                        Collection<Producto> listaProductos = productoService.listarProductos();
                        for (Producto producto : listaProductos) {
                            System.out.println(producto.getNombre());
                        }
                        break;
                    case "b":
                        Collection<Producto> listaProductosConPrecio = productoService.listarProductos();
                        for (Producto producto : listaProductosConPrecio) {
                            System.out.println(producto.getNombre() + " - Precio: " + producto.getPrecio());
                        }
                        break;
                    case "c":
                        Collection<Producto> listaProductosPorPrecio = productoService.listarProductosPorPrecio(120, 202);
                        for (Producto producto : listaProductosPorPrecio) {
                            System.out.println(producto.getNombre() + " - Precio: " + producto.getPrecio());
                        }
                        break;
                    case "d":
                        Collection<Producto> listaPortatiles = productoService.buscarPortatiles();
                        for (Producto producto : listaPortatiles) {
                            System.out.println(producto.getNombre());
                        }
                        break;
                    case "e":
                        Producto productoMasBarato = productoService.buscarProductoMasBarato();
                        System.out.println("Producto más barato: " + productoMasBarato.getNombre() + " - Precio: " + productoMasBarato.getPrecio());
                        break;
                    case "f":
                        System.out.println("Ingrese los datos del producto:");
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Tipo: ");
                        String tipo = scanner.nextLine();
                        System.out.print("Precio: ");
                        double precio = scanner.nextDouble();
                        scanner.nextLine(); // Limpiar el buffer
                        productoService.guardarProducto(new Producto(nombre, tipo, precio));
                        System.out.println("Producto guardado exitosamente.");
                        break;
                    case "g":
                        System.out.print("Ingrese el nombre del fabricante: ");
                        String nombreFabricante = scanner.nextLine();

                        System.out.println("Fabricante guardado exitosamente.");
                        break;
                    case "h":
                        System.out.print("Ingrese el código del producto a editar: ");
                        int codigoEditar = Integer.parseInt(scanner.nextLine());
                        try {
                            Producto productoEditar = productoService.buscarProductoPorCodigo(codigoEditar);
                            if (productoEditar != null) {
                                System.out.print("Ingrese el nuevo nombre del producto (o deje en blanco para no cambiar): ");
                                String nuevoNombre = scanner.nextLine();
                                if (!nuevoNombre.isBlank()) {
                                    productoEditar.setNombre(nuevoNombre);
                                }
                                System.out.print("Ingrese el nuevo precio del producto (o deje en blanco para no cambiar): ");
                                String nuevoPrecioStr = scanner.nextLine();
                                if (!nuevoPrecioStr.isBlank()) {
                                    double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
                                    productoEditar.setPrecio(nuevoPrecio);
                                }
                                productoService.editarProducto(productoEditar);
                                System.out.println("Producto editado exitosamente.");
                            } else {
                                System.out.println("Producto no encontrado.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error al editar producto: " + e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
