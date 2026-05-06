package es.iesquevedo.ui;

import es.iesquevedo.dao.AlquilerRepository;
import es.iesquevedo.dao.AlquilerRepositoryimpl;
import es.iesquevedo.service.*;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static  final AlquilerRepositoryimpl alquilerRepo = new AlquilerRepositoryimpl();
    private static final ConsoleController controller = new ConsoleController(sc);
    private static final AlquilerService alquilerService = new AlquilerServiceimpl();
    private static final CocheService cocheService = new CocheServiceimpl();
    private static final UsuarioService usuarioService = new UsuarioServiceimpl();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("--- Agencia Alquiler Coches ---");
            System.out.println("1) Gestionar coches");
            System.out.println("2) Gestionar usuarios");
            System.out.println("3) Gestionar alquileres");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> controller.menuCoches();
                case "2" -> controller.menuUsuarios();
                case "3" -> controller.menuAlquileres();
                case "0" -> running = false;
                default -> System.out.println("Opción no válida");
            }
        }
        System.out.println("Adiós");
    }
}
