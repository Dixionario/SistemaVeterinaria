package veterinaria.ui;

import veterinaria.controller.ClinicaController;
import veterinaria.entities.Animal;
import veterinaria.entities.Perro;
import veterinaria.entities.Gato;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final ClinicaController controller;
    private final Scanner sc = new Scanner(System.in);

    public ConsoleUI(ClinicaController controller) {
        this.controller = controller;
    }

    public void start() {
        int opcion;
        do {
            menu();
            opcion = readInt("Elige una opción: ");
            try {
                switch (opcion) {
                    case 1 -> agregarPerro();
                    case 2 -> agregarGato();
                    case 3 -> listar();
                    case 4 -> buscarPorId();
                    case 5 -> modificar();
                    case 6 -> eliminar();
                    case 7 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace(System.out);
            }
        } while (opcion != 7);
    }

    private void menu() {
        System.out.println("\n=== Menú Clínica Veterinaria (BD) ===");
        System.out.println("1. Agregar Perro");
        System.out.println("2. Agregar Gato");
        System.out.println("3. Listar animales");
        System.out.println("4. Buscar por ID");
        System.out.println("5. Modificar animal");
        System.out.println("6. Eliminar animal");
        System.out.println("7. Salir");
    }

    private void agregarPerro() throws Exception {
        System.out.println("== Agregar Perro ==");
        String nombre = readString("Nombre: ");
        int edad = readInt("Edad: ");
        Integer nivel = readIntNullable("Nivel de entrenamiento (entero) o ENTER para null: ");
        String tamano;
        while (true) {
            tamano = readStringNullable("Tamaño (pequeño/mediano/grande) o ENTER para null: ");
            if (tamano == null) break;
            if (tamano.equalsIgnoreCase("pequeño") || tamano.equalsIgnoreCase("mediano") || tamano.equalsIgnoreCase("grande")) break;
            System.out.println("Valor inválido.");
        }
        Perro p = new Perro(nombre, edad, nivel, tamano);
        controller.crear(p);
        System.out.println("Perro guardado con ID: " + p.getId());
    }

    private void agregarGato() throws Exception {
        System.out.println("== Agregar Gato ==");
        String nombre = readString("Nombre: ");
        int edad = readInt("Edad: ");
        Boolean viveInterior = readBooleanNullable("Vive en interior? (s/n) o ENTER para null: ");
        Integer vidas = readIntNullable("Nro de vidas (entero) o ENTER para null: ");
        Gato g = new Gato(nombre, edad, viveInterior, vidas);
        controller.crear(g);
        System.out.println("Gato guardado con ID: " + g.getId());
    }

    private void listar() throws Exception {
        List<Animal> lista = controller.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay animales registrados.");
            return;
        }
        for (Animal a : lista) {
            System.out.println(a.toString());
            a.emitirSonido();
        }
    }

    private void buscarPorId() throws Exception {
        int id = readInt("ID: ");
        Animal a = controller.buscar(id);
        if (a == null) {
            System.out.println("No encontrado.");
        } else {
            System.out.println(a.toString());
            a.emitirSonido();
        }
    }

    private void modificar() throws Exception {
        int id = readInt("ID a modificar: ");
        Animal a = controller.buscar(id);
        if (a == null) {
            System.out.println("No encontrado.");
            return;
        }
        System.out.println("Actual: " + a.toString());
        String nombre = readStringNullable("Nuevo nombre o ENTER para mantener: ");
        Integer edad = readIntNullable("Nueva edad o ENTER para mantener: ");

        if (nombre != null) a.setNombre(nombre);
        if (edad != null) a.setEdad(edad);

        if (a instanceof Perro) {
            Perro p = (Perro) a;
            Integer nivel = readIntNullable("Nuevo nivel de entrenamiento o ENTER para mantener: ");
            String tamano = readStringNullable("Nuevo tamaño (pequeño/mediano/grande) o ENTER para mantener: ");
            if (nivel != null) p.setNivelEntrenamiento(nivel);
            if (tamano != null) p.setTamano(tamano);
        } else if (a instanceof Gato) {
            Gato g = (Gato) a;
            Boolean vive = readBooleanNullable("Vive en interior? (s/n) o ENTER para mantener: ");
            Integer vidas = readIntNullable("Nuevo nro de vidas o ENTER para mantener: ");
            if (vive != null) g.setViveEnInterior(vive);
            if (vidas != null) g.setNroVidas(vidas);
        }

        boolean ok = controller.actualizar(a);
        System.out.println(ok ? "Actualizado." : "No se pudo actualizar.");
    }

    private void eliminar() throws Exception {
        int id = readInt("ID a eliminar: ");
        boolean ok = controller.eliminar(id);
        System.out.println(ok ? "Eliminado." : "No existe.");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try { return Integer.parseInt(line.trim()); }
            catch (Exception e) { System.out.println("Ingrese un número válido."); }
        }
    }

    private Integer readIntNullable(String prompt) {
        System.out.print(prompt);
        String line = sc.nextLine().trim();
        if (line.isEmpty()) return null;
        try { return Integer.parseInt(line); }
        catch (Exception e) { System.out.println("Formato inválido. Se toma null."); return null; }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        String line = sc.nextLine().trim();
        while (line.isEmpty()) {
            System.out.print(prompt);
            line = sc.nextLine().trim();
        }
        return line;
    }

    private String readStringNullable(String prompt) {
        System.out.print(prompt);
        String line = sc.nextLine().trim();
        return line.isEmpty() ? null : line;
    }

    private Boolean readBooleanNullable(String prompt) {
        System.out.print(prompt);
        String line = sc.nextLine().trim().toLowerCase();
        if (line.isEmpty()) return null;
        if (line.equals("s") || line.equals("si") || line.equals("y") || line.equals("yes")) return true;
        if (line.equals("n") || line.equals("no")) return false;
        System.out.println("No reconocido, se toma null.");
        return null;
    }
}
