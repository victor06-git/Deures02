package com.exercicis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Exercici0202 {

    public static Scanner scanner;
    public static Locale defaultLocale;
    
    // ./run.sh com.exercicis.Exercici0202
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);

        //showJSONAstronautes("./data/astronautes.json");
        //JSONAstronautesToArrayList("./data/astronautes.json");

        showEsportistesOrdenatsPerMedalla("./data/esportistes.json", "or");
        showEsportistesOrdenatsPerMedalla("./data/esportistes.json", "plata");

        //mostrarPlanetesOrdenats("./data/planetes.json", "nom");
        //mostrarPlanetesOrdenats("./data/planetes.json", "radi");
        //mostrarPlanetesOrdenats("./data/planetes.json", "massa");
        //mostrarPlanetesOrdenats("./data/planetes.json", "distància");


        ArrayList<HashMap<String, Object>> dades = new ArrayList<>();

        ArrayList<String> caracteristiquesPacific = new ArrayList<>();
        caracteristiquesPacific.add("És l'oceà més gran del món");
        caracteristiquesPacific.add("Conté la fossa de les Marianes, la més profunda del món");
        caracteristiquesPacific.add("Conté una illa de plàstics contaminants.");

        ArrayList<String> caracteristiquesAtlantic = new ArrayList<>();
        caracteristiquesAtlantic.add("Separa Amèrica d'Europa i Àfrica");
        caracteristiquesAtlantic.add("Conté el famós Triangle de les Bermudes");

        ArrayList<String> caracteristiquesMediterrani = new ArrayList<>();
        caracteristiquesMediterrani.add("És un mar gairebé tancat");
        caracteristiquesMediterrani.add("Connecta amb l'oceà Atlàntic a través de l'estret de Gibraltar");

        dades.add(crearMassaAigua("Oceà Pacífic", "oceà", 168723000, 10924, caracteristiquesPacific));
        dades.add(crearMassaAigua("Oceà Atlàntic", "oceà", 85133000, 8486, caracteristiquesAtlantic));
        dades.add(crearMassaAigua("Oceà Índic", "oceà", 70560000, 7450, new ArrayList<>()));
        dades.add(crearMassaAigua("Oceà Àrtic", "oceà", 15558000, 5450, new ArrayList<>()));
        dades.add(crearMassaAigua("Mar Mediterrani", "mar", 2500000, 5121, caracteristiquesMediterrani));
        dades.add(crearMassaAigua("Mar Carib", "mar", 2754000, 7686, new ArrayList<>()));
        dades.add(crearMassaAigua("Mar de la Xina Meridional", "mar", 3500000, 5560, new ArrayList<>()));

        try {
            generarJSON(dades, "./data/aigua.json");
        } catch (IOException e) {
            e.printStackTrace();
        }


        Locale.setDefault(defaultLocale);
        scanner.close();
    }

    /**
     * Llegeix l'arxiu de 'filePath' i mostra per consola les dades dels astronautes.
     * 
     * El format és:
     * > Astronauta 0:
     *   Nom: Yuri Gagarin
     *   Naixement: 1934
     * > Astronauta 1:
     *   Nom: Neil Armstrong
     *   Naixement: 1930
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0202#testShowJSONAstronautes
     */
    public static void showJSONAstronautes(String filePath) {
       
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jSONObject = new JSONObject(content); //Passar a objecte
            JSONArray jSONArray = jSONObject.getJSONArray("astronautes"); //Passar a array
            
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject astronauta = jSONArray.getJSONObject(i);
                System.out.println("> Astronauta " + i + ":");
                System.out.println("  Nom: " + astronauta.getString("nom"));
                System.out.println("  Naixement: " + astronauta.getInt("any_naixement"));
            }       
        } catch (IOException e) {
            System.out.print("No es llegeix l'arxiu: " + e.getMessage());
        }
    }

    /**
     * Llegeix l'arxiu de 'filePath', retorna un ArrayList amb les dades dels astronautes
     * Les dades han d'estar en un HashMap amb les claus "nom" i "any_naixement"
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0202#testJSONAstronautesToArrayList
     */
    public static ArrayList<HashMap<String, Object>> JSONAstronautesToArrayList(String filePath) {
        ArrayList<HashMap<String, Object>> rst = new ArrayList<>();

        try {            
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jSONObject = new JSONObject(content); //Passar a objecte
            JSONArray jSONArray = jSONObject.getJSONArray("astronautes"); //Passar a array
            
            for (int ast = 0; ast < jSONArray.length(); ast++) {
                JSONObject astronauta = jSONArray.getJSONObject(ast); //Passar a objecte
                HashMap<String, Object> astronautaMap = new HashMap<>();
                astronautaMap.put("nom", astronauta.getString("nom"));
                astronautaMap.put("any_naixement", astronauta.getInt("any_naixement"));
                rst.add(astronautaMap); //Afegeixo a la llista
            }

        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        return rst;
    }

    /**
     * Llegeix l'arxiu de 'filePath', retorna un ArrayList amb les dades dels esportistes
     * Les dades han d'estar en un HashMap amb: nom, any_naixement, pais i medalles
     * Les medalles de la clau 'medalles' han d'estar en un HashMap amb les claus "or", "plata" i "bronze"
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0202#testJSONEsportistesToArrayList
     */
    public static ArrayList<HashMap<String, Object>> JSONEsportistesToArrayList(String filePath) {
        ArrayList<HashMap<String, Object>> rst = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HashMap<String, Object> map = new HashMap<>();
                map.put("nom", jsonObject.getString("nom"));
                map.put("any_naixement", jsonObject.getInt("any_naixement"));
                map.put("pais", jsonObject.getString("pais"));

                JSONObject medalles = jsonObject.getJSONObject("medalles_olimpiques");
                HashMap<String, Object> mapMedalles = new HashMap<>();
                mapMedalles.put("or", medalles.getInt("or"));
                mapMedalles.put("plata", medalles.getInt("plata"));
                mapMedalles.put("bronze", medalles.getInt("bronze"));

                map.put("medalles", mapMedalles);
                rst.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rst;
    }

    /**
     * Llegeix l'arxiu JSON i retorna una llista d'esportistes ordenada per una medalla específica (or, plata o bronze).
     *
     * Si el tipus no és "or", "plata" o "bronze" llança una excepció IllegalArgumentException. Amb el text:
     * "Tipus de medalla invàlid: {tipusMedalla}. Tipus vàlids: 'or', 'plata' o 'bronze'."
     * 
     * @param filePath Ruta de l'arxiu JSON amb les dades dels esportistes.
     * @param tipusMedalla Tipus de medalla per ordenar: "or", "plata" o "bronze".
     * @return Llista ordenada d'esportistes segons el nombre de medalles especificat.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0202#testOrdenarEsportistesPerMedalla
     */
    public static ArrayList<HashMap<String, Object>> ordenarEsportistesPerMedalla(String filePath, String tipusMedalla) {
        // Obtenir la llista d'esportistes des del fitxer JSON
        ArrayList<HashMap<String, Object>> esportistes = JSONEsportistesToArrayList(filePath);

        if (!tipusMedalla.equals("or") && !tipusMedalla.equals("plata") && !tipusMedalla.equals("bronze")) {
            throw new IllegalArgumentException("Tipus de medalla invàlid: " + tipusMedalla + ". Tipus vàlids: 'or', 'plata' o 'bronze'.");
        }

        // Ordenem la llista en ordre descendent segons el tipus de medalla
        esportistes.sort((esportista0, esportista1) -> {
            // Fer HashMap<?, ?> enlloc de HashMap<String, Integer> evita warnings de tipus
            HashMap<?, ?> medalles0 = (HashMap<?, ?>) esportista0.get("medalles");
            HashMap<?, ?> medalles1 = (HashMap<?, ?>) esportista1.get("medalles");

            // Com que hem fet servir HashMap<?, ?>, cal definir el tipus (Integer)
            Integer a = (Integer) medalles0.get(tipusMedalla);
            Integer b = (Integer) medalles1.get(tipusMedalla);

            // Ordenar en ordre descendent
            return b.compareTo(a);
        });

        return esportistes;
    }

    /**
     * Mostra una taula amb els esportistes ordenats per una medalla específica (or, plata o bronze).
     *
     * Les paraules "or", "plata" i "bronze" han de ser mostrades amb la 
     * primera lletra en majúscules i la resta en minúscules.
     * 
     * El format de la taula ha de fer servir els caràcters: "┌", "┬", "┐", "├", "┼", "┤", "└", "┴" i "┘".
     * 
     * Un exemple simplificat de la taula seria:
     * ┌──────────────────────┬─────────────────┬────────────┬────────┐
     * │ Nom                  │ País            │ Naixement  │ Plata  │
     * ├──────────────────────┼─────────────────┼────────────┼────────┤
     * │ Larisa Latynina      │ Unió Soviètica  │ 1934       │ 5      │
     * │ Michael Phelps       │ Estats Units    │ 1985       │ 3      │
     * └──────────────────────┴─────────────────┴────────────┴────────┘
     * 
     * @param filePath Ruta de l'arxiu JSON amb les dades dels esportistes.
     * @param tipusMedalla Tipus de medalla per ordenar: "or", "plata" o "bronze".
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0202#testShowEsportistesOrdenatsPerOr
     * @test ./runTest.sh com.exercicis.TestExercici0202#testShowEsportistesOrdenatsPerPlata
     * @test ./runTest.sh com.exercicis.TestExercici0202#testShowEsportistesOrdenatsPerBronze
     */
    // filepath: /home/victor/Documentos/GitHub/Deures02/Deures02/src/main/java/com/exercicis/Exercici0202.java
    // public static void showEsportistesOrdenatsPerMedalla(String filePath, String tipusMedalla) {
        
    //     String tipusMedallaFormatted = tipusMedalla.substring(0, 1).toUpperCase() + tipusMedalla.substring(1).toLowerCase();

    //     System.out.println("┌" + "──────────────────────" + "┬" + "─────────────────" + "┬" + "────────────" + "┬" + "────────" + "┐");
    //     System.out.println("│ Nom                  │ País            │ Naixement  │ " + tipusMedallaFormatted + " ".repeat(7 - tipusMedallaFormatted.length()) + "│");
    //     System.out.println("├" + "──────────────────────" + "┼" + "─────────────────" + "┼" + "────────────" + "┼" + "────────" + "┤");

    //     ArrayList<HashMap<String, Object>> esportistes = ordenarEsportistesPerMedalla(filePath, tipusMedalla);
    //     for (HashMap<String, Object> esportista : esportistes) {
    //         System.out.printf("│ %-20s │ %-15s │ %-10d │ %-6d │%n", esportista.get("nom"), esportista.get("pais"), esportista.get("any_naixement"), ((HashMap<?, ?>) esportista.get("medalles")).get(tipusMedalla));
    //     }

    //     System.out.println("└" + "──────────────────────" + "┴" + "─────────────────" +"┴" + "────────────" + "┴" + "────────" + "┘");

    // }

    public static void showEsportistesOrdenatsPerMedalla(String filePath, String tipusMedalla) {
        ArrayList<HashMap<String, Object>> esportistes = ordenarEsportistesPerMedalla(filePath, tipusMedalla);

        String tipusMedallaCapitalized = tipusMedalla.substring(0, 1).toUpperCase() + tipusMedalla.substring(1).toLowerCase();

        String[] headers = {"Nom", "País", "Naixement", tipusMedallaCapitalized};
        int[] columnWidths = {20, 15, 10, 6};

        StringBuilder rst = new StringBuilder();

        rst.append("┌");
        for (int i = 0; i < headers.length; i++) {
            rst.append("─".repeat(columnWidths[i]+2));
            if (i < headers.length - 1) {
                rst.append("┬");
            }
        }
        rst.append("┐\n");
        
        
        //Segona linea
        rst.append("│");
        for (int i = 0; i < headers.length; i++) {
            rst.append(String.format(" %-"+columnWidths[i]+"s │", headers[i]));
        }
        rst.append("\n");

        //Tercera linea
        rst.append("├");
        for (int i = 0; i < headers.length; i++) {
            rst.append("─".repeat(columnWidths[i]+2));
            if (i < headers.length - 1) {
                rst.append("┼");
            }
        }
        rst.append("┤\n");

        //Quarta linea i las altres lineas fins acabar el HashMap
        for (HashMap<String, Object> esportista : esportistes) {
            HashMap<String, Integer> medalles = (HashMap<String, Integer>) esportista.get("medalles");
            rst.append("│");
            rst.append(String.format(" %-"+columnWidths[0]+"s │", esportista.get("nom")));
            rst.append(String.format(" %-"+columnWidths[1]+"s │", esportista.get("pais")));
            rst.append(String.format(" %-"+columnWidths[2]+"d │", esportista.get("any_naixement")));
            rst.append(String.format(" %-"+columnWidths[3]+"d │", medalles.get(tipusMedalla)));
            rst.append("\n");
        }

        rst.append("└");
        for (int i = 0; i < headers.length; i++) {
            rst.append("─".repeat(columnWidths[i] + 2));
            if (i < headers.length - 1) {
                rst.append("┴");
            } 
            if (i == headers.length - 1) {  
                rst.append("┘");
            }
        }
        rst.append("\n");

        System.out.print(rst);
    }

    /**
     * Llegeix l'arxiu JSON i converteix la informació dels planetes en una llista d'objectes HashMap.
     * 
     * Cada planeta es representa com un HashMap amb les claus:
     * - "nom" -> String amb el nom del planeta.
     * - "dades_fisiques" -> HashMap amb:
     *     - "radi_km" -> Double amb el radi en quilòmetres.
     *     - "massa_kg" -> Double amb la massa en kilograms.
     * - "orbita" -> HashMap amb:
     *     - "distancia_mitjana_km" -> Double amb la distància mitjana al Sol en quilòmetres.
     *     - "periode_orbital_dies" -> Double amb el període orbital en dies.
     * 
     * @param filePath Ruta de l'arxiu JSON amb les dades dels planetes.
     * @return Una ArrayList de HashMap amb la informació dels planetes.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0202#testJSONPlanetesToArrayList
     */
    public static ArrayList<HashMap<String, Object>> JSONPlanetesToArrayList(String filePath) {
        ArrayList<HashMap<String, Object>> planetesList = new ArrayList<>();
        try{
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject jsonObject = new JSONObject(content); 
            JSONArray jSONArray = jsonObject.getJSONArray("planetes");

            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject planeta = jSONArray.getJSONObject(i);
                HashMap<String, Object> planetes = new HashMap<>();
                planetes.put("nom", planeta.getString("nom"));
                
                JSONObject dadesFisiques = planeta.getJSONObject("dades_fisiques");
                HashMap<String, Object> mapDades = new HashMap<>();
                mapDades.put("radi_km", dadesFisiques.getDouble("radi_km"));
                mapDades.put("massa_kg", dadesFisiques.getDouble("massa_kg"));

                JSONObject orbita = planeta.getJSONObject("orbita");
                HashMap<String, Object> mapOrbita = new HashMap<>();
                mapOrbita.put("distancia_mitjana_km", orbita.getInt("distancia_mitjana_km"));
                mapOrbita.put("periode_orbital_dies", orbita.getDouble("periode_orbital_dies"));

                planetes.put("dades_fisiques", mapDades);
                planetes.put("orbita", mapOrbita);

                planetesList.add(planetes);
            }
        
        
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        return planetesList;
    }

    /**
     * Mostra una taula amb els planetes ordenats segons una columna especificada.
     * 
     * Els valors vàlids per a la columna d'ordenació són:
     * - "nom" -> Ordena alfabèticament pel nom del planeta.
     * - "radi" -> Ordena numèricament pel radi del planeta.
     * - "massa" -> Ordena numèricament per la massa del planeta.
     * - "distància" -> Ordena numèricament per la distància mitjana al Sol.
     * 
     * El format de la taula ha de fer servir els caràcters: "┌", "┬", "┐", "├", "┼", "┤", "└", "┴" i "┘".
     * 
     * Ex.:
     * ┌──────────────┬────────────┬──────────────┬────────────────┐
     * │ Nom          │ Radi (km)  │ Massa (kg)   │ Distància (km) │
     * ├──────────────┼────────────┼──────────────┼────────────────┤
     * │ Mercuri      │ 2439.7     │ 3.3011e23    │ 57910000       │
     * │ Venus        │ 6051.8     │ 4.8675e24    │ 108200000      │
     * └──────────────┴────────────┴──────────────┴────────────────┘
     * 
     * @param filePath Ruta de l'arxiu JSON amb les dades dels planetes.
     * @param columnaOrdenacio La columna per la qual es vol ordenar ("nom", "radi", "massa", "distància").
     * 
     * @throws IllegalArgumentException si el paràmetre de columna és invàlid.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0202#testMostrarPlanetesOrdenatsNom
     * @test ./runTest.sh com.exercicis.TestExercici0202#testMostrarPlanetesOrdenatsRadi
     * @test ./runTest.sh com.exercicis.TestExercici0202#testMostrarPlanetesOrdenatsMassa
     * @test ./runTest.sh com.exercicis.TestExercici0202#testMostrarPlanetesOrdenatsDistancia
     */
    public static void mostrarPlanetesOrdenats(String filePath, String columnaOrdenacio) {
    }

    /**
     * Crea un HashMap que representa una massa d'aigua amb característiques addicionals.
     * 
     * @param nom Nom del mar o oceà.
     * @param tipus Tipus (mar o oceà).
     * @param superficie_km2 Superfície en km².
     * @param profunditat_max_m Profunditat màxima en metres.
     * @param caracteristiques Llista d'informació addicional sobre el mar o oceà.
     * @return Un HashMap amb les dades del mar o oceà.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0202#testCrearMassaAigua
     */
    public static HashMap<String, Object> crearMassaAigua(String nom, String tipus, double superficie_km2, double profunditat_max_m, ArrayList<String> caracteristiques) {
        HashMap<String, Object> massaAigua = new HashMap<>();
        return massaAigua;
    }

    /**
     * Genera un arxiu JSON amb la informació de mars i oceans. Identat amb "4 espais":
     * [
     *     {
     *         "nom": "Oceà Pacífic",
     *          "tipus": "oceà",
     *          "profunditat_max_m": 10924,
     *          "superficie_km2": 1.68723E8,
     *          "caracteristiques": [
     *              "És l'oceà més gran del món",
     *              "Conté la fossa de les Marianes, la més profunda del món",
     *              "Conté una illa de plàstics contaminants."
     *         ]
     *      },
     *      {
     *          "nom": "Oceà Atlàntic",
     *          "tipus": "oceà", ...
     * 
     * @param filePath Ruta de l'arxiu JSON a crear.
     * @throws IOException Si hi ha algun problema amb l'escriptura de l'arxiu.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0202#testValidarFormatJSON
     */
    public static void generarJSON(ArrayList<HashMap<String, Object>> dades, String filePath) throws IOException {

    }
}