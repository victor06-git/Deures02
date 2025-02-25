package com.exercicis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Exercici0203 {

    public static Scanner scanner;
    public static Locale defaultLocale;
    
    // Fes anar el 'main' de l'exercici amb:
    // ./run.sh com.exercicis.Exercici0203
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        String url0 = "http://example.com";
        validarURL(url0); 

        String url1 = "https://google";
        validarURL(url1); 

        
        
        try {
            ArrayList<HashMap<String, Object>> monuments = loadMonuments("./data/monuments.json");
            ArrayList<HashMap<String, Object>> monumentsOrdenats = ordenaMonuments(monuments, "nom");
            ArrayList<HashMap<String, Object>> monumentsFiltrats = filtraMonuments(monuments, "categoria", "cultural");

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            guardaBaralla("./data/baralla.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Locale.setDefault(defaultLocale);
        scanner.close();
    }

    /** 
     * Valida una URL a partir d'una cadena de text. 
     * 
     * Cal que:
     * 
     * - No pot ser null ni una cadena de text buida
     * - No pot contenir espais
     * - Ha de començar amb 'http://' o 'https://'
     * - El domini ha de contenir almenys un punt 
     * - El domini no pot començar ni acabar amb un punt
     * 
     * URLs vàlides: ["http://example.com", "https://www.google.com", "https://sub.domini.cat/pagina", "http://localhost:8080", "http://www.ieti.cat:8080/horaris"]
     * URLs no vàlides: ["", "ftp://example.com", "http:/example", "http:/example.com", "https:// google.com", "https://.example.com", "https://example.", "example.com"]
     * 
     * @param url URL a validar
     * @return 'true' si la URL és vàlida, 'false' si no ho és
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testValidarURL
     */
    public static boolean validarURL(String url) {

        if (!url.isEmpty()) {
            return true;
        } else if (!url.contains(" ")) {
            return true;
        } else if (url.startsWith("http://") || url.startsWith("https://")) {
            return true;
        } else if (url.contains(".")) {
            return true;
        } else if (!url.startsWith(".") && !url.endsWith(".")) {
            return true;
        }

        return false;
    }

    /**
     * Llegeix l'arxiu de 'filePath', retorna un ArrayList amb les dades 
     * dels monuments que són patrimoni de la humanitat.
     * 
     * Ha de generar 'HashMap' amb les dades de cada monument
     * L'atribut que no coincideix, ha d'estar en un 'HashMap' propi anomenat 'altres'
     * que té dues claus:
     * - 'clau': nom de l'atribut
     * - 'valor': valor de l'atribut
     * 
     * @param filePath Ruta de l'arxiu JSON
     * @return ArrayList amb les dades dels monuments
     * 
     * @throws IOException Si hi ha algun problema amb l'escriptura de l'arxiu forçant un 'try/catch'
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testLoadMonuments
     */
    public static ArrayList<HashMap<String, Object>> loadMonuments(String filePath) throws IOException {
        ArrayList<HashMap<String, Object>> rst = new ArrayList<>();
        return rst;
    }

    /**
     * Obté el valor d'un monument segons el camp especificat.
     * Es pot utilitzar tant per a ordenació com per a filtratge.
     * 
     * - Si el camp és "nom", retorna el valor directament de la clau "nom".
     * - Si el camp és "pais", retorna el valor directament de la clau "pais".
     * - Si el camp és "categoria", retorna el valor directament de la clau "categoria".
     * - Si el camp és "any", accedeix al valor de "any_declaracio" dins de "detalls".
     * - Si el camp és "latitud" o "longitud", accedeix al valor corresponent dins de "coordenades",
     *   que es troba dins de "detalls".
     * - Si el valor no existeix o la jerarquia de dades no està present, retorna null.
     * 
     * @param monument HashMap amb les dades del monument.
     * @param key Clau pel qual es vol obtenir el valor (pot ser "nom", "any", "latitud" o "longitud").
     * @return Un 'Object' amb el valor corresponent si existeix, en cas contrari retorna null.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testGetMonumentValue
     */
    private static Object getMonumentValue(HashMap<String, Object> monument, String key) {
        return null;
    }
    

    /**
     * Comprova si un valor es troba dins d'una llista de valors vàlids.
     * 
     * @param value Valor a comprovar.
     * @param validValues Llista de valors permesos.
     * @return True si el valor és dins de la llista, false en cas contrari.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testIsValidValue
     */
    private static boolean isValid(String value, String[] validValues) {
        return false;
    }

    /**
     * Ordena un ArrayList de monuments per un camp concret.
     * Els camps vàlids són: 'nom', 'any', 'latitud', 'longitud'
     *      * 
     * @param monuments llista dels monuments
     * @param sortKey camp per ordenar
     * @return ArrayList amb les dades dels monuments
     * 
     * @throws IllegalArgumentException si el paràmetre de columna és invàlid forçant un 'try/catch'
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testOrdenaMonuments
     */
    public static ArrayList<HashMap<String, Object>> ordenaMonuments(ArrayList<HashMap<String, Object>> monuments, String sortKey) throws IllegalArgumentException {

        ArrayList<HashMap<String, Object>> rst = new ArrayList<>(monuments);
        return rst;
    }

    /**
     * Filtra un ArrayList de monuments per un camp i un valor
     * Els camps vàlids són: 'nom', 'pais', 'categoria'
     *      * 
     * @param monuments llista dels monuments
     * @param filterKey camp per filtrar
     * @param filterValue valor a filtrar
     * @return ArrayList amb les dades dels monuments
     * 
     * @throws IllegalArgumentException si el paràmetre de columna és invàlid (no força un 'try/catch')
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testOrdenaMonuments
     */
    public static ArrayList<HashMap<String, Object>> filtraMonuments(ArrayList<HashMap<String, Object>> monuments, String filterKey, String filterValue) throws IllegalArgumentException {

        return new ArrayList<>();
    }

    /**
     * Genera una cadena de text vàlida per formar el marc d'una taula:
     * 
     * Exemple: {2, 5, 3} i { '┌', '┬', '┐' } genera "┌──┬─────┬───┐"
     * Exemple: {4, 3, 6} i { '├', '┼', '┤' } genera "├────┼───┼──────┤"
     * Exemple: {2, 4} i { '└', '┴', '┘' } genera "└──┴────┘"
     * 
     * @param columnWidths array amb els caràcters que ocupa cada columna
     * @param separators array amb els separadors inicial,central i final
     * @return String amb la cadena de text
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testGeneraMarcTaula
     */
    public static String generaMarcTaula(int[] columnWidths, char[] separators) {
        return "";
    }

    /**
     * Formata una fila de la taula amb els valors de cada columna, ajustant l'amplada segons 
     * els valors especificats i afegint marges i separadors.
     *
     * Cada cel·la s'alinea a l'esquerra i es complementa amb espais en blanc si cal 
     * per ajustar-se a l'amplada de la columna.
     *
     * Exemples:
     * formatRow(new String[]{"Nom", "País", "Any"}, new int[]{10, 6, 4});
     * Retorna: "│ Nom       │ País  │ Any  │"
     *
     * formatRow(new String[]{"Machu Picchu", "Perú", "1983"}, new int[]{10, 6, 4});
     * Retorna: "│ Machu Picchu │ Perú  │ 1983 │"
     *
     * @param values Array amb els valors de cada columna.
     * @param columnWidths Array amb l'amplada de cada columna.
     * @return Una cadena de text formatejada representant una fila de la taula.
     */
    private static String formatRow(String[] values, int[] columnWidths) {
        return "";
    }

    /**
     * Obté una representació en format text de les coordenades d'un monument.
     *
     * La funció busca dins de la clau "detalls" del monument i accedeix a 
     * "coordenades", d'on obté la "latitud" i "longitud". 
     * Si algun d'aquests valors no és present, retorna una cadena buida.
     *
     * Exemple de monument:
     * {
     *   "detalls": {
     *     "coordenades": {
     *       "latitud": "40.4",
     *       "longitud": "116.5"
     *     }
     *   }
     * }
     * 
     * Crida a getCoordsString(monument) → Retorna "40.4,116.5"
     *
     * @param monument HashMap que representa un monument.
     * @return Una cadena de text amb les coordenades en format "latitud,longitud",
     *         o una cadena buida si no es troben les dades.
     */
    private static String getCoordsString(HashMap<String, Object> monument) {

        return "";
    }

    /**
     * Mostra una taula amb la informació d'una llista de monuments.
     * 
     * El format de la taula ha de fer servir els caràcters: "┌", "┬", "┐", "├", "┼", "┤", "└", "┴" i "┘".
     * 
     * Ex.:
     * ┌────────────────┬────────────┬──────┬────────────────┐
     * │ Nom            │ Pais       │ Any  │ Coords         │
     * ├────────────────┼────────────┼──────┼────────────────┤
     * │ Gran Muralla C.│ Xina       │ 1987 │ 40.4,116.5     │
     * │ Machu Picchu   │ Perú       │ 1983 │ -13.1,-72.5    │
     * └────────────────┴────────────┴──────┴────────────────┘
     * 
     * @param monuments llista dels monuments     
     * @param columnaOrdenacio La columna per la qual es vol ordenar ("nom", "radi", "massa", "distància").
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testTaulaMonuments
     */
    public static void taulaMonuments(ArrayList<HashMap<String, Object>> monuments) {
        
    }

    /**
     * Genera una baralla de cartes espanyola i la retorna en un ArrayList ordenat aleatòriament.
     * 
     * La baralla consta de 40 cartes, amb quatre pals: "oros", "copes", "espases" i "bastos".
     * Cada pal té cartes numerades de l'1 al 12.
     * 
     * Cada carta es representa com un HashMap amb dues claus:
     * - "pal" (String): indica el pal de la carta (ex: "oros").
     * - "numero" (Integer): el número de la carta (ex: 7).
     * 
     * Exemple de sortida d'una carta:
     * { "pal": "copes", "número": 10 }
     * 
     * @return Un ArrayList que conté les 40 cartes de la baralla en ordre aleatori.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testGeneraBaralla
     */
    public static ArrayList<HashMap<String, Object>> generaBaralla() {
        ArrayList<HashMap<String, Object>> baralla = new ArrayList<>();

        return baralla;
    }

    /**
     * Guarda la informació d'una baralla espanyola a 'filePath'
     * 
     * @param filePath
     * @throws IOException si hi ha algun error amb l'escriptura de l'arxiu forçant un 'try/catch'
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0203#testGuardaBaralla
     */
    public static void guardaBaralla(String filePath) throws IOException {

    }
}