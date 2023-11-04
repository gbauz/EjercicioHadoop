
package ApartadoB;

import HadoopMini.*;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class Problema6 {

    static class TemperatureMapper implements MyMap {
	@Override
	public void map(Tupla elemento, ArrayList<Tupla> output) {
	    // Dividir la línea del archivo CSV en campos (fecha y temperatura)
	    String[] campos = elemento.getValor().toString().split(",");

	    if (campos.length == 2) {
		String fecha = campos[0];
		int temperatura = Integer.parseInt(campos[1]);
		// Emitir la temperatura con la fecha como clave
		output.add(new Tupla(fecha, temperatura));
	    }
	}
    }

    static class TemperatureReducer implements MyReduce {
	@Override
    public void reduce(Tupla elemento, ArrayList<Tupla> output) {
        String[] data = ((String) elemento.getValor()).split(","); // Divide los datos por comas
        int minTemp = Integer.MAX_VALUE; // Inicializa la temperatura mínima con el valor máximo
        int maxTemp = Integer.MIN_VALUE; // Inicializa la temperatura máxima con el valor mínimo

        // Itera sobre los datos de temperatura y encuentra la mínima y máxima
        for (int i = 6; i < data.length - 1; i++) {
            int temperature = (int) Double.parseDouble(data[i]);
            minTemp = Math.min(minTemp, temperature);
            maxTemp = Math.max(maxTemp, temperature);
        }

        String fecha = data[0] + "," + data[1] + "," + data[2] + "," + data[3];
        output.add(new Tupla(fecha, "Mínima: " + minTemp + ", Máxima: " + maxTemp));
    }
    }

  

    public static void main(String[] args) {
	Tarea tarea = new Tarea();

	// Configuración de la tarea
	tarea.setInputFile("JCMB_last31days.csv"); // Ruta del archivo CSV de entrada
	tarea.setOutputfile("problema6.txt"); // Ruta del archivo de salida
	tarea.setNode(2); // Número de nodos/reducers
	tarea.setMapFunction(new TemperatureMapper());
	tarea.setReduceFunction(new TemperatureReducer());

	// Ejecutar la tarea
	tarea.run();
    }
}
