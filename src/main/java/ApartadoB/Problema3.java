
package ApartadoB;

import HadoopMini.MyMap;
import HadoopMini.MyReduce;
import HadoopMini.Tarea;
import HadoopMini.Tupla;
import java.util.ArrayList;


public class Problema3 {

    static class map1 implements MyMap {
	/**
	 * Guarda en la lista output las horas.
	 * 
	 * @param elemento Tupla
	 * @param output   ArrayList que permite agregar las tuplas que queremos.
	 */
	@Override
	public void map(Tupla elemento, ArrayList<Tupla> output) {
	    String[] words = ((elemento.getValor())).toString().split(" ");
	    for (String wordss : words) {
		if (wordss.startsWith("[") && wordss.endsWith("]")) {
		    String hora = wordss.split(":")[1];
		    output.add(new Tupla(hora, 1));
		}
	    }
	}
    }

    static class reduce1 implements MyReduce {

	@Override
	public void reduce(Tupla elemento, ArrayList<Tupla> output) {
	    ArrayList<Integer> list = (ArrayList<Integer>) elemento.getValor();
	    int count = 0;
	    for (Integer item : list) {
		count += item;
	    }

	    output.add(new Tupla(elemento.getClave(), count));
	}
    }

    public static void main(String[] args) {
	Tarea t = new Tarea();
	t.setInputFile("weblog.txt");
	t.setOutputfile("Problema3.txt");
	t.setNode(52);
	t.setMapFunction(new map1());
	t.setReduceFunction(new reduce1());
	t.run();
    }

}
