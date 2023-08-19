import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Nodo {
    String dato;
    Nodo siguiente;

    public Nodo(String dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class ListaLigada {
    Nodo cabeza;

    public void agregar(String dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
        }
    }

    public void mostrar() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.print(temp.dato + ", ");
            temp = temp.siguiente;
        }
    }

    public void eliminar(String dato) {
        if (cabeza == null) {
            return;
        }
        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            return;
        }
        Nodo temp = cabeza;
        while (temp.siguiente != null && !temp.siguiente.dato.equals(dato)) {
            temp = temp.siguiente;
        }
        if (temp.siguiente != null) {
            temp.siguiente = temp.siguiente.siguiente;
        }
    }
}

public class read_file {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaLigada lista = new ListaLigada();

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Abrir un archivo");
            System.out.println("2. Mostrar los datos de la lista ligada");
            System.out.println("3. Eliminar un dato de la lista");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del archivo: ");
                    String nombreArchivo = scanner.nextLine();
                    leerArchivo(nombreArchivo, lista);
                    break;
                case 2:
                    lista.mostrar();
                    break;
                case 3:
                    System.out.print("Ingrese el dato a eliminar: ");
                    String datoEliminar = scanner.nextLine();
                    lista.eliminar(datoEliminar);
                    System.out.println("Dato eliminado.");
                    break;
                case 4:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private static void leerArchivo(String nombreArchivo, ListaLigada lista) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                for (String dato : partes) {
                    lista.agregar(dato.trim());
                }
            }
            System.out.println("Archivo leído y datos agregados a la lista.");
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo: " + e.getMessage());
        }
    }
}
