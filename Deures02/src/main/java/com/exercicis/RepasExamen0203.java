package com.exercicis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
//import java.util.stream.Collectors;

//import org.json.JSONArray;
//import org.json.JSONObject;


public class RepasExamen0203 {

    public static Scanner scanner;
    public static Locale defaultLocale;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        // Probar la validación de email
        String email = "test@example.com";
        System.out.println("Validar email (" + email + "): " + validarEmail(email));

        // Cargar estudiantes desde un archivo JSON
        String filePath = "estudiantes.json"; // Asegúrate de que este archivo exista
        try {
            ArrayList<HashMap<String, Object>> estudiantes = loadEstudiantes(filePath);
            System.out.println("Estudiantes cargados: " + estudiantes);

            // Filtrar estudiantes por edad
            ArrayList<HashMap<String, Object>> estudiantesFiltrados = filtraEstudiantesPorEdad(estudiantes, 20, 22);
            System.out.println("Estudiantes filtrados (edad 20-22): " + estudiantesFiltrados);

            // Ordenar estudiantes por nombre
            ArrayList<HashMap<String, Object>> estudiantesOrdenados = ordenaEstudiantesPorNombre(estudiantes);
            System.out.println("Estudiantes ordenados por nombre: " + estudiantesOrdenados);

            // Mostrar tabla de estudiantes
            mostrarTablaEstudiantes(estudiantes);

            // Generar baraja de cartas francesas
            ArrayList<HashMap<String, Object>> baraja = generaBarajaFrancesa();
            System.out.println("Baraja generada: " + baraja);

            // Guardar la baraja en un archivo (opcional)
            String barajaFilePath = "baraja.json"; // Cambia la ruta si es necesario
            guardaBaraja(barajaFilePath, baraja);
            System.out.println("Baraja guardada en: " + barajaFilePath);

            // Obtener el valor de una carta
            String palo = "oros";
            int numero = 7;
            Object carta = getValorCarta(baraja, palo, numero);
            System.out.println("Valor de la carta (" + palo + " " + numero + "): " + carta);

        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }    

        Locale.setDefault(defaultLocale);
        scanner.close();
    }

    /**
     * Valida una dirección de correo electrónico.
     * 
     * @param email Dirección de correo a validar.
     * @return 'true' si el email es válido, 'false' si no lo es.
     */
    public static boolean validarEmail(String email) {
        
        if (email == null || email.isEmpty() || email.equalsIgnoreCase(" ")) {
            return false;
        }

        if (!email.contains("@") || email.indexOf("@") != email.lastIndexOf("@")) {
            return false;
        }

        String[] parts = email.split("@");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            return false;
        }

        if (parts[0].startsWith(".") || parts[0].endsWith(".") || parts[1].startsWith(".") || parts[1].endsWith(".")) {
            return false;
        }

        String dominio = email.substring(email.indexOf("@") + 1);
        if (!dominio.contains(".") || dominio.indexOf(".") != dominio.lastIndexOf(".") || dominio.contains("..")) {
            return false;
        }

        String[] caracteresEspeciales = {"!", "#", "$", "%", "&"};
        for (String caract : caracteresEspeciales) {
            if (email.contains(caract)) {
                return false;
            }
        }

        return true; // Cambia esto
    }

    /**
     * Carga datos de estudiantes desde un archivo JSON.
     * 
     * @param filePath Ruta del archivo JSON.
     * @return ArrayList con los datos de los estudiantes.
     * @throws IOException Si hay un problema al leer el archivo.
     */
    public static ArrayList<HashMap<String, Object>> loadEstudiantes(String filePath) throws IOException {
        // Implementa la lógica aquí
        return new ArrayList<>(); // Cambia esto
    }

    /**
     * Filtra la lista de estudiantes por edad.
     * 
     * @param estudiantes Lista de estudiantes.
     * @param edadMinima Edad mínima.
     * @param edadMaxima Edad máxima.
     * @return Lista filtrada de estudiantes.
     */
    public static ArrayList<HashMap<String, Object>> filtraEstudiantesPorEdad(ArrayList<HashMap<String, Object>> estudiantes, int edadMinima, int edadMaxima) {
        // Implementa la lógica aquí
        return new ArrayList<>(); // Cambia esto
    }

    /**
     * Ordena la lista de estudiantes por nombre.
     * 
     * @param estudiantes Lista de estudiantes.
     * @return Lista ordenada de estudiantes.
     */
    public static ArrayList<HashMap<String, Object>> ordenaEstudiantesPorNombre(ArrayList<HashMap<String, Object>> estudiantes) {
        // Implementa la lógica aquí
        return estudiantes; // Cambia esto
    }

    /**
     * Muestra una tabla con la información de los estudiantes.
     * 
     * @param estudiantes Lista de estudiantes.
     */
    public static void mostrarTablaEstudiantes(ArrayList<HashMap<String, Object>> estudiantes) {
        // Implementa la lógica aquí
    }

    /**
     * Genera una baraja de cartas francesas.
     * 
     * @return ArrayList con las cartas de la baraja.
     */
    public static ArrayList<HashMap<String, Object>> generaBarajaFrancesa() {
        // Implementa la lógica aquí
        return new ArrayList<>(); // Cambia esto
    }

    /**
     * Guarda la baraja en un archivo JSON.
     * 
     * @param filePath Ruta del archivo.
     * @param baraja Lista de cartas.
     * @throws IOException Si hay un problema al escribir el archivo.
     */
    public static void guardaBaraja(String filePath, ArrayList<HashMap<String, Object>> baraja) throws IOException {
        // Implementa la lógica aquí
    }

    /**
     * Obtiene el valor de una carta dada su palo y número.
     * 
     * @param baraja Lista de cartas.
     * @param palo Palo de la carta.
     * @param numero Número de la carta.
     * @return El valor de la carta o null si no existe.
     */
    public static Object getValorCarta(ArrayList<HashMap<String, Object>> baraja, String palo, int numero) {
        // Implementa la lógica aquí
        return null; // Cambia esto
    }
}