package com.exercicis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Exercici0201 {

    public static Scanner scanner;
    public static Locale defaultLocale;

    public static void main(String[] args) {
        
        scanner = new Scanner(System.in);
        defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);
        /* 
        int[] arrEnters = generaArrayEnters(10);
        mostraArrayEstadistiques(arrEnters);

        ArrayList<Integer> lstEnters = generaLlistaEnters(10);
        mostraLlistaEstadistiques(lstEnters);

        filtraArrayParaulesAmbA();
        filtraLlistaParaulesAmbA();

        double[] arrDecimals = generaArrayDecimals(15);
        filtraArrayDecimalsSuperiors50(arrDecimals);

        ArrayList<Double> lstDecimals = generaLlistaDecimals(15);
        filtraLlistaDecimalsSuperiors50(lstDecimals);

        HashMap<String, Integer> persones = new HashMap<>();
        persones.put("Anna", 25);
        persones.put("Joan", 30);
        persones.put("Marc", 20);
        mostrarLlistaOrdenadesPerEdat(persones);
        
        mostrarFrecuenciaParaules();
        
        invertirMapaClauValor();
                
        fusionarMapesSumantValors();
         
        ordenarMapaPerClaus();
        */
        HashMap<String, Double> notes = new HashMap<>();
        notes.put("Anna", 7.5);
        notes.put("Joan", 6.8);
        notes.put("Marta", 8.2);
        notes.put("Pere", 4.1);
        notes.put("Enric", 2.0);
        notes.put("Amparo", 6.9);
        notes.put("Olga", 9.0);
        notes.put("Manel", 2.2);

        calcularEstadistiquesNotesEstudiants(notes);
        

        Locale.setDefault(defaultLocale);
        scanner.close();
    }

    /**
     * Genera un array d'enters aleatoris.
     *
     * @param mida la mida de l'array a generar
     * @return un array d'enters amb valors entre 0 i 99
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testGeneraArrayEnters
     */
    public static int[] generaArrayEnters(int mida) {
        
        int[] rst = new int[mida];
        Random rand = new Random();

        for (int i = 0; i < mida; i++) {
            rst[i] = rand.nextInt(100);
        }

        return rst;
    }

    /**
     * Calcula i mostra estadístiques d'un array d'enters.
     * 
     * Imprimeix per pantalla l'array, el valor màxim, el mínim i la mitjana.
     * Format d'output:
     * "Array: [valors]"
     * "Màxim: X  Mínim: Y  Mitjana: Z"
     *
     * @param array l'array d'enters sobre el qual calcular les estadístiques
     * @test ./runTest.sh com.exercicis.TestExercici0201#testMostraArrayEstadistiques
     */
    public static void mostraArrayEstadistiques(int[] array) {
        Integer maximo = Integer.MIN_VALUE;
        Integer minimo = Integer.MAX_VALUE;
        double media = 0.0;

        for (int valor : array) {
            media += valor;
            if (valor > maximo) {
                maximo = valor;
            } if (valor < minimo) {
                minimo = valor;
            }
        }
        media = media / array.length;

        System.out.print("Array: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
        System.out.println("Màxim: " + maximo + "  Mínim: " + minimo + "  Mitjana: " + media);
    }

    /**
     * Genera una llista d'enters aleatoris.
     *
     * @param mida la mida de la llista a generar
     * @return una ArrayList d'enters amb valors entre 0 i 99
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testGeneraLlistaEnters
     */
    public static ArrayList<Integer> generaLlistaEnters(int mida) {
        ArrayList<Integer> rst = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < mida; i++) {
            rst.add(rand.nextInt(100));
        }
        return rst;
    }

    /**
     * Calcula i mostra estadístiques d'una llista d'enters.
     * 
     * Imprimeix per pantalla la llista, el valor màxim, el mínim i la mitjana.
     * Format d'output:
     * "Llista: [valors]"
     * "Màxim: X  Mínim: Y  Mitjana: Z"
     * 
     *
     * @param llista la llista d'enters sobre la qual calcular les estadístiques
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testMostraLlistaEstadistiques
     */
    public static void mostraLlistaEstadistiques(ArrayList<Integer> llista) {
            int max = Integer.MIN_VALUE;
            Integer min = Integer.MAX_VALUE;
            double total = 0.0;
    
            for (int value : llista) {
                total += value;
                if (value > max) {
                    max = value;
                } if (value < min) {
                    min = value;
                }
            }
            total = total / llista.size();
    
            System.out.print("Llista: [");
            for (int i = 0; i < llista.size(); i++) {
                System.out.print(llista.get(i));
                if (i < llista.size() - 1)
                    System.out.print(", ");
            }
            System.out.println("]");
            System.out.println("Màxim: " + max + "  Mínim: " + min + "  Mitjana: " + total);
    }
    

    /**
     * Demana a l'usuari que escrigui 5 paraules separades per ',' o ', ' 
     * i mostra aquelles que comencen amb 'a'.
     * 
     * Guarda la llista en un "String[] paraules"
     * 
     * Es mostra per pantalla:
     * "Escriu 5 paraules separades per ',' o ', ':" per sol·licitar les entrades,
     * i després "Paraules que comencen amb 'a':" seguit de les paraules filtrades.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testFiltraArrayParaulesAmbA
     */
    // ...existing code...
    public static void filtraArrayParaulesAmbA() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Escriu 5 paraules separades per ',' o ', ': ");
        String paraula = sc.nextLine().toLowerCase().trim();

        String[] paraules = paraula.split(",\\s*");
        String rst = "Paraules que comencen amb 'a': ";

        boolean first = true;
        for (int i = 0; i < paraules.length; i++) {
            String word = paraules[i].trim();
            if (word.startsWith("a")) {
                if (!first) {
                    rst += ", ";
                }
                rst += word;
                first = false;
            }
        }

        System.out.println(rst);
    }
// ...existing code...
       
    /**
     * Demana a l'usuari que escrigui 5 paraules separades per ',' o ', ' 
     * i mostra aquelles que comencen amb 'a' en una sola línia separades per ", ".
     * 
     * Guarda la llista en un "ArrayList<String> paraules".
     * 
     * Es mostra per pantalla:
     * "Escriu 5 paraules separades per ',' o ', ':" per sol·licitar les entrades,
     * i després "Paraules que comencen amb 'a':" seguit de les paraules filtrades.
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testFiltraLlistaParaulesAmbA
     */
    public static void filtraLlistaParaulesAmbA() {
        //Scanner scanner = new Scanner(System.in);
        System.out.print("Escriu 5 paraules separades per ',' o ', ': ");
        String paraula = scanner.nextLine().toLowerCase().trim();
        String[] paraulasStrings = paraula.split(",");
        ArrayList<String> paraules = new ArrayList<String>(Arrays.asList(paraulasStrings));

        boolean coma = false;
            
        String rst = "Paraules que comencen amb 'a': ";
        for (String word : paraules) {
            if (word.trim().startsWith("a")) {
                if (!coma) {
                    rst += word;
                    coma = true;
                } else {
                    rst += "," + word;
                }
            }
        }

        System.out.println(rst);
    }

    /**
     * Genera un array de decimals aleatoris.
     *
     * @param mida la mida de l'array a generar
     * @return un array de decimals amb valors entre 0.0 i 100.0
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testGeneraArrayDecimals
     */
    public static double[] generaArrayDecimals(int mida) {
        double[] rst = new double[mida];

        Random rand = new Random();
        for (int i = 0; i < mida; i++) {
            rst[i] = rand.nextDouble(0.0, 100.0);
        }

        return rst;
    }

    /**
     * Genera una llista de decimals aleatoris.
     *
     * @param mida la mida de la llista a generar
     * @return una ArrayList de decimals amb valors entre 0.0 i 100.0
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testFiltraArrayDecimalsSuperiors50
     */
    public static ArrayList<Double> generaLlistaDecimals(int mida) {
        ArrayList<Double> rst = new ArrayList<>();
        for (int cnt = 0; cnt < mida; cnt++) {
            Random rd = new Random();
            rst.add(rd.nextDouble(100));
        }
        return rst;
    }

    /**
     * Filtra i mostra els decimals superiors a 50 d'un array.
     * 
     * Imprimeix per pantalla l'array original de decimals i, a continuació,
     * la llista dels decimals que són majors que 50.
     * Format d'output:
     * "Array original: [valors]"
     * "Valors majors que 50: [valors filtrats]"
     * 
     *
     * @param decimals l'array de decimals a filtrar
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testGeneraLlistaDecimals
     */
    public static void filtraArrayDecimalsSuperiors50(double[] decimals) {

        String array = "";
        String valors = "";

        for (int i = 0; i < decimals.length; i++) {
            array += String.format("%.2f, ", decimals[i]);
        }
        array = array.substring(0, array.length() - 2) + "]";

        for (int j = 0; j < decimals.length; j++) {
            if (decimals[j] > 50) {
                valors += String.format("%.2f, ", decimals[j]);
            }
        }

        valors = valors.substring(0, valors.length() - 2) + "]";

        System.out.println("Array original: [" + array);
        System.out.println("Valors majors que 50: [" + valors);
    }

    /**
     * Filtra i mostra els decimals superiors a 50 d'una llista.
     * 
     * Imprimeix per pantalla la llista original de decimals i, a continuació,
     * la llista dels decimals que són majors que 50.
     * Format d'output:
     * "Llista original: [valors]"
     * "Valors majors que 50: [valors filtrats]"
     *
     * @param decimals la llista de decimals a filtrar
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testFiltraLlistaDecimalsSuperiors50
     */
    public static void filtraLlistaDecimalsSuperiors50(ArrayList<Double> decimals) {

        String original = "";
        String filtrats = "";

        for (int i = 0; i < decimals.size(); i++) {
            original += String.format("%.2f, ", decimals.get(i));
        }
        original = original.substring(0, original.length() - 2) + "]";

        for (int j = 0; j < decimals.size(); j++) {
            if (decimals.get(j) > 50) {
                filtrats += String.format("%.2f, ", decimals.get(j));
            }
        }

        filtrats = filtrats.substring(0, filtrats.length() - 2) + "]";

        System.out.println("Llista original: [" + original);
        System.out.println("Valors majors que 50: [" + filtrats);

    }
    
    /**
     * Mostra per pantalla les persones ordenades per edat.
     * 
     * Recorre un HashMap de persones (nom i edat) i imprimeix cada persona en format "Nom (Edat)"
     * ordenat per edat de manera ascendent.
     *
     * @param persones un HashMap on la clau és el nom de la persona i el valor és la seva edat
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testMostrarLlistaOrdenadesPerEdat
     */
    public static void mostrarLlistaOrdenadesPerEdat(HashMap<String, Integer> persones) {

        Collection<Integer> edats = persones.values();
        ArrayList<Integer> edatsOrdenades = new ArrayList<>(edats);
        String edatSort = "";
        edatsOrdenades.sort((a, b) -> a.compareTo(b));
        for (int edat : edatsOrdenades) {
            for (String nom : persones.keySet()) {
                if (persones.get(nom) == edat) {
                    edatSort += nom + " (" + edat + ")\n";
                }
            }
        }

        System.out.println(edatSort.substring(0, edatSort.length() - 1));

    }

    /**
     * Demana a l'usuari que introdueixi una frase i mostra la freqüència de cada paraula.
     * 
     * L'usuari escriu una frase per la consola i el mètode separa les paraules usant els espais.
     * A continuació, es calcula la freqüència de cada paraula i es mostra per pantalla en format de HashMap.
     * 
     * 
     * Es mostra per pantalla:
     * "Introdueix una frase:" i després "Freqüència de paraules: {paraula=frequencia, ...}".
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testMostrarFrecuenciaParaules
     */
    public static void mostrarFrecuenciaParaules() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix una frase: ");
        String frase = sc.nextLine().toLowerCase().trim();
        String[] paraules = frase.split(" ");
        HashMap<String, Integer> frequencia = new HashMap<>();
        for (String word : paraules) {
            if (frequencia.containsKey(word)) {
                frequencia.put(word, frequencia.get(word) + 1);
            } else {
                frequencia.put(word, 1);
            }
        }

        String rst = "Freqüència de paraules: " + frequencia;
        System.out.println(rst);
    }

    /**
     * Inverteix un HashMap intercanviant claus i valors.
     * 
     * Es crea un HashMap amb elements (A=1, B=2, C=3) i es construeix un nou HashMap on cada valor 
     * es converteix en clau i cada clau es converteix en valor.
     * 
     * 
     * Es mostra per pantalla:
     * "Mapa original: {A=1, B=2, C=3}" i "Mapa invertit: {1=A, 2=B, 3=C}".
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testInvertirMapaClauValor
     */
    public static void invertirMapaClauValor() {

        HashMap<String, Integer> newMap = new HashMap<>();
        newMap.put("A", 1);
        newMap.put("B", 2);
        newMap.put("C", 3);

        HashMap<Integer, String> invertedMap = new HashMap<>();
        for (String key : newMap.keySet()) {
            invertedMap.put(newMap.get(key), key);
        }

        System.out.println("Mapa original: " + newMap);
        System.out.println("Mapa invertit: " + invertedMap);

    }

    /**
     * Fusiona dos HashMap sumant els valors de les claus comuns.
     * 
     * Es defineixen dos mapes:
     * <ul>
     *   <li>Mapa 1: {X=10, Y=20}</li>
     *   <li>Mapa 2: {Y=5, Z=15}</li>
     * </ul>
     * El mètode crea un nou HashMap on, per cada clau existent en ambdós mapes, es suma el valor.
     * 
     * 
     * Es mostra per pantalla:
     * "Mapa fusionat: {X=10, Y=25, Z=15}".
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testFusionarMapesSumantValors
     */
    public static void fusionarMapesSumantValors() {

        HashMap<String, Integer> mapa1 = new HashMap<>();
        mapa1.put("X", 10);
        mapa1.put("Y", 20);
        HashMap<String, Integer> mapa2 = new HashMap<>();
        mapa2.put("Y", 5);
        mapa2.put("Z", 15);

        HashMap<String, Integer> mapaFusionat = new HashMap<>();
        for (String key : mapa1.keySet()) {
            for (String key2 : mapa2.keySet()) {
                if (key.equals(key2)) {
                    mapaFusionat.put(key, mapa1.get(key) + mapa2.get(key2));
                } else {
                    mapaFusionat.put(key, mapa1.get(key));
                    mapaFusionat.put(key2, mapa2.get(key2));
                }
            }
        }
        System.out.println("Mapa fusionat: "  + mapaFusionat);
    }

    /**
     * Ordena un HashMap per les claus mitjançant un TreeMap i mostra el resultat.
     * 
     * Es crea un HashMap amb elements (Banana=3, Poma=5, Taronja=2)
     * per obtenir un ordre natural de les claus (alfabètic).
     * 
     * Mostrar les claus amb el seu valor ordenar per nom de clau.
     * 
     * Es mostra per pantalla:
     * "Mapa ordenat per claus: {Banana=3, Poma=5, Taronja=2}".
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testOrdenarMapaPerClaus
     */
    public static void ordenarMapaPerClaus() {

        HashMap<String, Integer> mapa = new HashMap<>();
        mapa.put("Banana", 3);
        mapa.put("Poma", 5);
        mapa.put("Taronja", 2);

        Collection<String> fruites = mapa.keySet();
        ArrayList<String> fruitesOrdenades = new ArrayList<>(fruites);
        String mapaOrdenat = "";

        fruitesOrdenades.sort((fruita1, fruita2) -> fruita2.compareTo(fruita1));

        HashMap<String, Integer> newMap = new HashMap<>();
        for (String fruit : fruitesOrdenades) {
            newMap.put(fruit, mapa.get(fruit));
        }
        /*
        for (String fruit : fruitesOrdenades) {
            for (int num : mapa.values()) {
                if (mapa.get(fruit) == num) {
                    
                }
            }
        }
        */
        System.out.println("Mapa ordenat per claus: " + newMap);

    }

    /**
     * Calcula i mostra les estadístiques (mitjana, màxim i mínim) de les notes dels estudiants.
     * 
     * Es defineix un HashMap on la clau és el nom de l'estudiant i el valor la seva nota.
     * El mètode calcula la mitjana, la nota màxima i la nota mínima i les mostra per pantalla.
     * 
     *
     * Es mostra per pantalla:
     * "Mitjana: [valor], Màxim: [valor], Mínim: [valor]".
     * 
     * @test ./runTest.sh com.exercicis.TestExercici0201#testCalcularEstadistiquesNotesEstudiants
     */
    public static void calcularEstadistiquesNotesEstudiants(HashMap<String, Double> notes) {

        

        Double mitjana = 0.0;
        Double maxim = Double.MIN_VALUE;
        Double minim = Double.MAX_VALUE;
        Integer total = 0;


        //Recorrer la lista de Keys del HashMap y dentro las notas
        for (double nota : notes.values()) {
            total ++;
            mitjana += nota;
            if (nota > maxim) {
                maxim = nota;
            } if (nota < minim) {
                minim = nota;
            }
        }

        mitjana = mitjana / notes.size();

        System.out.printf("Mitjana: %.2f, Màxim: %.2f, Mínim: %.2f%n", mitjana, maxim, minim);

    }
}
