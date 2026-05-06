package es.iesquevedo.ui;

import es.iesquevedo.modelo.Alquiler;
import es.iesquevedo.modelo.Coche;
import es.iesquevedo.modelo.Usuario;
import es.iesquevedo.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleController {
    private final Scanner sc;
    private final CocheService cocheService;
    private final UsuarioService usuarioService;
    private final AlquilerService alquilerService;
    private final CocheService cocheService1;
    private final UsuarioService usuarioService1;
    private final AlquilerService alquilerService1;

    public ConsoleController(Scanner sc, CocheService cocheService1, UsuarioService usuarioService1, AlquilerService alquilerService1) {
        this.sc = sc;
        this.cocheService = cocheService1;
        this.cocheService1 = cocheService1;
        this.usuarioService = usuarioService1;
        this.usuarioService1 = usuarioService1;
        this.alquilerService = alquilerService1;
        this.alquilerService1 = alquilerService1;
    }

    public ConsoleController(Scanner sc, Scanner sc1, CocheService cocheService, UsuarioService usuarioService, AlquilerService alquilerService, CocheService cocheService1, UsuarioService usuarioService1, AlquilerService alquilerService1) {

        this.sc = sc1;
        this.cocheService = cocheService;
        this.usuarioService = usuarioService;
        this.alquilerService = alquilerService;
        this.cocheService1 = cocheService1;
        this.usuarioService1 = usuarioService1;
        this.alquilerService1 = alquilerService1;
    }

    // Menús delegados desde Main
    public void menuCoches() {
        System.out.println("-- Coches --");
        System.out.println("1) Alta coche");
        System.out.println("2) Baja coche");
        System.out.println("3) Listar coches");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1" -> altaCoche();
            case "2" -> bajaCoche();
            case "3" -> listarCoches();
            case "0" -> {
            }
            default -> System.out.println("Opción no válida");
        }
    }

    public void altaCoche() {
        System.out.print("Marca: ");
        String marca = sc.nextLine().trim();
        System.out.print("Modelo: ");
        String modelo = sc.nextLine().trim();
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine().trim();
        String id = UUID.randomUUID().toString();
        Coche c = new Coche(id, marca, modelo, matricula);
        if (cocheService.altaCoche(c)) System.out.println("Coche dado de alta con id=" + id);
        else System.out.println("No se pudo crear el coche (id duplicado o datos inválidos)");
    }

    public void bajaCoche() {
        System.out.print("Id coche a eliminar: ");
        String id = sc.nextLine().trim();
        if (cocheService.bajaCoche(id)) System.out.println("Coche eliminado");
        else System.out.println("No existe coche con ese id");
    }

    public void listarCoches() {
        List<Coche> ls = cocheService.listar();
        if (ls.isEmpty()) System.out.println("No hay coches");
        else ls.forEach(System.out::println);
    }

    public void menuUsuarios() {
        System.out.println("-- Usuarios --");
        System.out.println("1) Alta usuario");
        System.out.println("2) Baja usuario");
        System.out.println("3) Listar usuarios");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1" -> altaUsuario();
            case "2" -> bajaUsuario();
            case "3" -> listarUsuarios();
            case "0" -> {
            }
            default -> System.out.println("Opción no válida");
        }
    }

    public void altaUsuario() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Documento (DNI/email): ");
        String doc = sc.nextLine().trim();
        String id = UUID.randomUUID().toString();
        Usuario u = new Usuario(id, nombre, doc);
        if (usuarioService.altaUsuario(u)) System.out.println("Usuario creado con id=" + id);
        else System.out.println("No se pudo crear el usuario (id duplicado o datos inválidos)");
    }

    public void bajaUsuario() {
        System.out.print("Id usuario a eliminar: ");
        String id = sc.nextLine().trim();
        if (usuarioService.bajaUsuario(id)) System.out.println("Usuario eliminado");
        else System.out.println("No existe usuario con ese id");
    }

    public void listarUsuarios() {
        List<Usuario> ls = usuarioService.listar();
        if (ls.isEmpty()) System.out.println("No hay usuarios");
        else ls.forEach(System.out::println);
    }

    public void menuAlquileres() {
        System.out.println("-- Alquileres --");
        System.out.println("1) Crear alquiler");
        System.out.println("2) Finalizar alquiler");
        System.out.println("3) Listar alquileres");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1" -> crearAlquiler();
            case "2" -> finalizarAlquiler();
            case "3" -> listarAlquileres();
            case "0" -> {
            }
            default -> System.out.println("Opción no válida");
        }
    }

    public void crearAlquiler() {
        System.out.print("Id coche: ");
        String cocheId = sc.nextLine().trim();
        System.out.print("Id usuario: ");
        String usuarioId = sc.nextLine().trim();
        System.out.print("Fecha inicio (yyyy-MM-dd): ");
        String fi = sc.nextLine().trim();
        System.out.print("Fecha fin (yyyy-MM-dd): ");
        String ff = sc.nextLine().trim();
        try {
            LocalDate inicio = LocalDate.parse(fi);
            LocalDate fin = LocalDate.parse(ff);
            if (fin.isBefore(inicio)) { System.out.println("Fecha fin anterior a inicio"); return; }
            String id = UUID.randomUUID().toString();
            Alquiler a = new Alquiler(id, cocheId, usuarioId, inicio, fin);
            if (alquilerService.crearAlquiler(a)) System.out.println("Alquiler creado con id=" + id);
            else System.out.println("No se pudo crear el alquiler (coche no existe/disponible o usuario no existe)");
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido, usa yyyy-MM-dd");
        }
    }

    public void finalizarAlquiler() {
        System.out.print("Id alquiler a finalizar: ");
        String id = sc.nextLine().trim();
        if (alquilerService.finalizarAlquiler(id)) System.out.println("Alquiler finalizado");
        else System.out.println("No existe alquiler con ese id");
    }

    public void listarAlquileres() {
        List<Alquiler> ls = alquilerService.listar();
        if (ls.isEmpty()) System.out.println("No hay alquileres");
        else ls.forEach(System.out::println);
    }
}

