
package ApartadoB;

import HadoopMini.*;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class Problema7 {

    static class TristezaMapper implements MyMap {
	@Override
	public void map(Tupla elemento, ArrayList<Tupla> output) {
	    String[] campos = elemento.getValor().toString().split(","); // Supongamos que los datos están separados por
									 // comas

	    if (campos.length >= 3) {
		String palabra = campos[0];
		double felicidadMedia = Double.parseDouble(campos[1]);
		String twitterRank = campos[2];

		output.add(new Tupla(palabra, felicidadMedia + "," + twitterRank));

	    }
	}
    }

    static class TristezaReducer implements MyReduce {
	@Override
	public void reduce(Tupla elemento, ArrayList<Tupla> output) {
	    String palabra = (String) elemento.getClave();
	    double felicidadTotal = 0.0;
	    int conteo = 0;

	    for (Tupla data : output) {
		double felicidadMedia = (double) data.getValor();
		String twitterRank = (String) data.getClave();

		if (felicidadMedia < 2.0 && !twitterRank.equals("--")) {
		    felicidadTotal += felicidadMedia;
		    conteo++;
		}
	    }

	    if (conteo > 0) {
		double felicidadMediaFinal = felicidadTotal / conteo;
		output.add(new Tupla(palabra, felicidadMediaFinal));
	    }
	}
    }

    public static void main(String[] args) {
	Tarea tarea = new Tarea();

	// Configuración de la tarea
	tarea.setInputFile("happiness.txt"); // Ruta del archivo txt de entrada
	tarea.setOutputfile("palabras_muy_tristes.txt"); // Ruta del archivo de salida
	tarea.setNode(2); // Número de nodos/reducers
	tarea.setMapFunction(new TristezaMapper());
	tarea.setReduceFunction(new TristezaReducer());

	// Ejecutar la tarea
	tarea.run();
    }

}
