package com.exercicis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

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
        String filePath = "./data/estuadiants.json"; // Asegúrate de que este archivo exista
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
            //ArrayList<HashMap<String, Object>> baraja = generaBarajaFrancesa();
            //System.out.println("Baraja generada: " + baraja);

            // Guardar la baraja en un archivo (opcional)
            //String barajaFilePath = "baraja.json"; // Cambia la ruta si es necesario
            //guardaBaraja(barajaFilePath, baraja);
            //System.out.println("Baraja guardada en: " + barajaFilePath);

            // Obtener el valor de una carta
            //String palo = "oros";
            //int numero = 7;
            //Object carta = getValorCarta(baraja, palo, numero);
            //System.out.println("Valor de la carta (" + palo + " " + numero + "): " + carta);

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
        ArrayList<HashMap<String, Object>> estudiantes = new ArrayList<>();
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONArray estudiantesArray = new JSONObject(content).getJSONArray("estudiantes");
    
        for (int i = 0; i < estudiantesArray.length(); i++) {
            HashMap<String, Object> estudiante = new HashMap<>();
            JSONObject estudianteObject = estudiantesArray.getJSONObject(i);
    
            for (String key : estudianteObject.keySet()) {
                if (key.equals("nombre") || key.equals("edad")) {
                    estudiante.put(key, estudianteObject.get(key));
                } else if (key.equals("asignaturas")) {
                    JSONArray asignaturasArray = estudianteObject.getJSONArray("asignaturas");
                    String[] asignaturas = new String[asignaturasArray.length()];
                    for (int j = 0; j < asignaturasArray.length(); j++) {
                        asignaturas[j] = asignaturasArray.getString(j);
                    }
                    estudiante.put("asignaturas", asignaturas);
                }
            }
            estudiantes.add(estudiante);
        }
    
        return estudiantes;
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

        ArrayList<HashMap<String, Object>> filtraEstudiants = new ArrayList<>();
        for (int i = 0; i < estudiantes.size(); i++){
            HashMap<String, Object> estudiant = estudiantes.get(i);
            for (String key : estudiant.keySet()) {
                if (key.equals("edad")) {
                    int edat = (int) estudiant.get(key);
                    if (edat >= edadMinima && edat <= edadMaxima){
                        filtraEstudiants.add(estudiant);
                    }
                }
            }
        }

        return filtraEstudiants; // Cambia esto
    }
     /**

     * Ordena la lista de estudiantes por nombre utilizando el método de burbuja.
     * 
     * @param estudiantes Lista de estudiantes.
     * @return Lista ordenada de estudiantes.
     */

     public static ArrayList<HashMap<String, Object>> ordenaEstudiantesPorNombre(ArrayList<HashMap<String, Object>> estudiantes) {

        // Itera sobre la lista de estudiantes, menos el último elemento
        for (int i = 0; i < estudiantes.size() - 1; i++) {
            // Itera sobre la lista de estudiantes, menos los elementos ya ordenados
            for (int j = 0; j < estudiantes.size() - 1 - i; j++) {
                // Obtiene el nombre del estudiante en la posición j
                String nombre1 = (String) estudiantes.get(j).get("nombre");
                // Obtiene el nombre del estudiante en la posición j + 1
                String nombre2 = (String) estudiantes.get(j + 1).get("nombre");                
                // Compara los nombres y los intercambia si están en el orden incorrecto
                if (nombre1.compareTo(nombre2) > 0) {
                    // Crea una variable temporal para almacenar el estudiante en la posición j
                    HashMap<String, Object> temp = estudiantes.get(j);
                    // Intercambia el estudiante en la posición j con el estudiante en la posición j + 1
                    estudiantes.set(j, estudiantes.get(j + 1));
                    // Coloca el estudiante temporal en la posición j + 1
                    estudiantes.set(j + 1, temp);
                }
            }
        }
        // Retorna la lista de estudiantes ya ordenada por nombre
        return estudiantes; 
    }

    /**
     * Muestra una tabla con la información de los estudiantes.
     * 
     * ┌────────────────┬────────────┬───────────────────────┐
     * │Nombre          │Edad        │Assignaturas           │
     * ├────────────────┼────────────┼───────────────────────┤
     * │ Gran Muralla C.│ Xina       │                       │
     * │ Machu Picchu   │ Perú       │                       │
     * └────────────────┴────────────┴───────────────────────┘
     * 
     * @param estudiantes Lista de estudiantes.
     */
    public static void mostrarTablaEstudiantes(ArrayList<HashMap<String, Object>> estudiantes) {
        int[] colWidth = {20, 4, 33};
        String rst = "┌";
        // First line
        for (int i = 0; i < colWidth.length; i++) {
            rst += "─".repeat(colWidth[i]);
            if (i == colWidth.length - 1) {
                rst += "┐";
            } else {
                rst += "┬";
            }
        }
    
        System.out.println(rst);
    
        // Titles line
        String[] titles = {"Nombre", "Edad", "Assignaturas"};
        String rst1 = "│";
        for (int i = 0; i < colWidth.length; i++) {
            String title = titles[i];
            if (title.length() > colWidth[i]) {
                title = title.substring(0, colWidth[i]);
            }
            rst1 += title + " ".repeat(colWidth[i] - title.length());
            if (i == colWidth.length - 1) {
                rst1 += "│";
            } else {
                rst1 += "│";
            }
        }
    
        System.out.println(rst1);
    
        // Second Line
        String rst2 = "├";
        for (int i = 0; i < colWidth.length; i++) {
            rst2 += "─".repeat(colWidth[i]);
            if (i == colWidth.length - 1) {
                rst2 += "┤";
            } else {
                rst2 += "┼";
            }
        }
    
        System.out.println(rst2);
    
        // Data rows
        for (HashMap<String, Object> estudiante : estudiantes) {
            String estudiants = "│";
            String nom = (String) estudiante.get("nombre");
            String edat = String.valueOf(estudiante.get("edad"));
            String rst3 = "";
    
            // Verificar si el array de asignaturas no es nulo
            if (estudiante.get("asignaturas") != null) {
                String[] asignaturas = (String[]) estudiante.get("asignaturas");
                for (int j = 0; j < asignaturas.length; j++) {
                    rst3 += asignaturas[j];
                    if (j < asignaturas.length - 1) {
                        rst3 += ", ";
                    }
                }
            }
    
            estudiants += nom + " ".repeat(Math.max(0, colWidth[0] - nom.length()));
            estudiants += "│";
            estudiants += edat + " ".repeat(Math.max(0, colWidth[1] - edat.length()));
            estudiants += "│";
            estudiants += rst3 + " ".repeat(Math.max(0, colWidth[2] - rst3.length()));
            estudiants += "│";
    
            System.out.println(estudiants);
        }
    
        // Last line
        String rst4 = "└";
        for (int i = 0; i < colWidth.length; i++) {
            rst4 += "─".repeat(colWidth[i]);
            if (i == colWidth.length - 1) {
                rst4 += "┘";
            } else {
                rst4 += "┴";
            }
        }
    
        System.out.println(rst4);
    }

    /**
     * Genera una baraja de cartas francesas.
     * 
     * 
     * La baraja francesa consta de 52 cartas divididas en cuatro palos: 
     * corazones, diamantes, tréboles y picas. 
     * 
     * Cada palo tiene 13 cartas: As, del 2 al 10, J (Jota), Q (Reina) y K (Rey). 
     * 
     * Además, a menudo se incluyen 2 comodines.
     * 
     * @return ArrayList con las cartas de la baraja.
     */
    public static ArrayList<HashMap<String, Object>> generaBarajaFrancesa() {
        // Implementa la lógica aquí

        String[] palos = {"Corazones", "Diamantes", "Tréboles", "Picas"};
        String[] types = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        

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

   

    /**
     * Valida una data en el format yyyy-MM-ddTHH:mm:ss.
     *
     * @param fecha Cadena de text que representa la data a validar.
     * @return 'true' si la data és vàlida, 'false' si no ho és.
     */
    public static boolean validarFecha(String fecha) {
        if (fecha == null || fecha.isEmpty()) {
            return false;
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        format.setLenient(false); // No permet dates no vàlides

        try {
            Date date = format.parse(fecha);
            return true; // Si no es llença una excepció, la data és vàlida
        } catch (ParseException e) {
            return false; // La data no és vàlida
        }
    }

    /**
     * 
        public static boolean isValidDate(String dateTime) {

    // Comprobar que la cadena no es nula ni vacía

    if (dateTime == null || dateTime.isEmpty()) {

        return false;

    }


    // Definir el formato de la fecha

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


    // Intentar analizar la fecha

    try {

        // Analizar la fecha

        format.parse(dateTime);

    } catch (ParseException e) {

        return false; // La fecha no es válida

    }


    // Separar la fecha y la hora

    String[] dateTimeParts = dateTime.split("T");

    String[] dateParts = dateTimeParts[0].split("-");

    String[] timeParts = dateTimeParts[1].split(":");


    // Extraer año, mes, día, hora, minuto y segundo

    int year = Integer.parseInt(dateParts[0]);

    int month = Integer.parseInt(dateParts[1]);

    int day = Integer.parseInt(dateParts[2]);

    int hour = Integer.parseInt(timeParts[0]);

    int minute = Integer.parseInt(timeParts[1]);

    int second = Integer.parseInt(timeParts[2]);


    // Validar el año

    if (year < 0 || year > 9999) {

        return false; // Años fuera de rango

    }


    // Validar el mes

    if (month < 1 || month > 12) {

        return false; // Mes fuera de rango

    }


    // Validar el día según el mes

    int maxDays;

    switch (month) {

        case 1: case 3: case 5: case 7: case 8: case 10: case 12:

            maxDays = 31;

            break;

        case 4: case 6: case 9: case 11:

            maxDays = 30;

            break;

        case 2:

            // Comprobar si es año bisiesto

            maxDays = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;

            break;

        default:

            return false; // Esto no debería ocurrir debido a la validación anterior

    }
     */
}