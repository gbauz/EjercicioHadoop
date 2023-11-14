
package ApartadoB;

import HadoopMini.*;

import java.util.ArrayList;

public class Problema5 {

    interface MeteorologicalDataFilter {
	boolean filter(double precipitation);
    }

    static class Map5 implements MyMap {
	private final MeteorologicalDataFilter filter;

	public Map5(MeteorologicalDataFilter filter) {
	    this.filter = filter;
	}

	@Override
	public void map(Tupla elemento, ArrayList<Tupla> output) {
	    String[] parts = elemento.getValor().toString().split(",");
	    double precipitation = Double.parseDouble(parts[5]);

	    if (filter.filter(precipitation)) {
		double humidity = Double.parseDouble(parts[7]);
		double windChill = Double.parseDouble(parts[8]);
		output.add(new Tupla("meteorological", precipitation + "," + humidity + "," + windChill));
	    }
	}
    }

    static class Reduce implements MyReduce {
	@Override
	public void reduce(Tupla elemento, ArrayList<Tupla> output) {
	    output.add(elemento); // simplemente agregamos el resultado al archivo de salida
	}
    }

    static class MeteorologicalDataFilterImpl implements MeteorologicalDataFilter {
	@Override
	public boolean filter(double precipitation) {
	    return precipitation > 0;
	}
    }

    public static void main(String[] args) {
	Tarea tarea5 = new Tarea();

	tarea5.setReduceFunction(new Reduce());

	MeteorologicalDataFilter filter = new MeteorologicalDataFilterImpl();

	Map5 map5 = new Map5(filter);

	tarea5.setMapFunction(map5);

	// Utiliza la ruta completa para el archivo de entrada y salida
	tarea5.setInputFile("JCMB_last31days.csv");
	tarea5.setOutputfile("Problema5.txt");
	tarea5.setNode(2);

	tarea5.run();
    }

}
