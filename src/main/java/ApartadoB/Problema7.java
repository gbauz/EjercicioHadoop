
package ApartadoB;

import HadoopMini.*;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class Problema7 {

    /**
     * Interfaz que define un método para determinar si una línea de datos es
     * extremadamente triste.
     */
    interface HappinessAnalyzer {
	boolean isExtremelySad(String[] lineData);
    }

    /**
     * Analiza cada elemento y agrega las palabras extremadamente tristes al
     * resultado.
     */
    static class MapImpl implements MyMap {

	private final HappinessAnalyzer analyzer;

	public MapImpl(HappinessAnalyzer analyzer) {
	    this.analyzer = analyzer;
	}

	/**
	 * Analiza cada elemento y agrega las palabras extremadamente tristes al
	 * resultado.
	 */
	@Override
	public void map(Tupla elemento, ArrayList<Tupla> output) {
	    String[] line = elemento.getValor().toString().split(" ");

	    for (String item : line) {
		String[] lineData = item.split("\\t");

		if (analyzer.isExtremelySad(lineData))
		    output.add(new Tupla("palabras_extremadamente_triste:", 1));
	    }
	}
    }

    /**
     * Determina si una línea de datos es extremadamente triste en base a ciertos
     * criterios.
     */
    static class HappinessAnalyzerImpl implements HappinessAnalyzer {

	/**
	 * Determina si una línea de datos es extremadamente triste en base a ciertos
	 * criterios.
	 */
	@Override
	public boolean isExtremelySad(String[] lineData) {
	    double happinessAverage = Double.parseDouble(lineData[2]);
	    return happinessAverage < 2 && !lineData[4].equals("--");
	}
    }

    public static void main(String[] args) {

	Tarea tarea = new Tarea();

	tarea.setInputFile("happiness.txt"); // Ruta del archivo txt de entrada
	tarea.setOutputfile("palabras_muy_tristes.txt"); // Ruta del archivo de salida
	tarea.setNode(30); // Número de nodos
	HappinessAnalyzer analyzer = new HappinessAnalyzerImpl();
	MapImpl mapImpl = new MapImpl(analyzer);

	tarea.setMapFunction(mapImpl);
	tarea.setReduceFunction(new HappinesReduce());

	// Ejecutar la tarea
	tarea.run();
    }

}
