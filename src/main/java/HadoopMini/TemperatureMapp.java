
package HadoopMini;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 Analiza cada elemento y encuentra la temperatura mínima y máxima.
 */

public class TemperatureMapp implements MyMap {

    /**
     * Analiza cada elemento y encuentra la temperatura mínima y máxima.
     */
    @Override
    public void map(Tupla elemento, ArrayList<Tupla> output) {
	String[] line = elemento.getValor().toString().split(" "); //Se crea un arreglo de strings con los datos de la
								       // linea de texto que se esta leyendo en el momento
								      // de la ejecucion

	List<Double> listNumeros = convertirLineaEnListaNumeros(line); // Se crea una lista de numeros a partir de la
								       //linea de texto

	double min = obtenerMinimo(listNumeros); //Se obtiene el valor minimo de la lista de numeros
	double max = obtenerMaximo(listNumeros); //Se obtiene el valor maximo de la lista de numeros

	agregarTuplasMinimoYMaximo(output, min, max); // Se agregan las tuplas de temperatura minima y maxima al resultado
    }

    
    /**
     * Convierte una línea de datos en una lista de números.
     * 
     */
    private List<Double> convertirLineaEnListaNumeros(String[] line) {
	List<Double> listNumeros = new ArrayList<>();

	for (String item : line) { /// recorremos la linea de texto
	    String[] lineData = item.split(","); /// creamos un arreglo de strings con los datos de la linea de texto
	    double temp = Double.parseDouble(lineData[8]); /// convertimos el string de la temperatura en un double
	    listNumeros.add(temp);
	}

	return listNumeros;
    }

    /**
     * Obtiene el valor mínimo de una lista de números.
     */
    private double obtenerMinimo(List<Double> listNumeros) {
	return Collections.min(listNumeros); //Retorna el valor minimo de la lista de numeros.
    }

    /**
     * Obtiene el valor máximo de una lista de números.
     */
    private double obtenerMaximo(List<Double> listNumeros) {
	return Collections.max(listNumeros); /// retornamos el valor maximo de la lista de numeros
    }

    /**
     * Agrega las tuplas de temperatura mínima y máxima al resultado.
     */
    private void agregarTuplasMinimoYMaximo(ArrayList<Tupla> output, double min, double max) { //Se agregan las tuplas de 
											       //temperatura minima y maxima al resultado.
	output.add(new Tupla("Temperatura mínima", min));
	output.add(new Tupla("Temperatura máxima", max));
    }

}
