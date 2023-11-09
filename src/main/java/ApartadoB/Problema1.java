

package ApartadoB;

import HadoopMini.*;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class Problema1 {
    
    // Implementación de la estrategia de mapeo
    static class Map1 implements MyMap {

        @Override
        public void map(Tupla elemento, ArrayList<Tupla> output) {
            String[] words = elemento.getValor().toString().split(" ");
            for (String w : words) {
                String new_w = w.toLowerCase().replaceAll("[^\\w]", ""); // Eliminamos los caracteres no alfanuméricos
                if (new_w.equals("404")) {
                    output.add(new Tupla(new_w, 1));
                }
            }
        }
    }

    // Implementación de la estrategia de reducción
    static class Reduce1 implements MyReduce {
        @Override
        public void reduce(Tupla elemento, ArrayList<Tupla> output) { //El reduce se encarga de sumar los valores de la lista de tuplas
            ArrayList<Integer> list = (ArrayList<Integer>) elemento.getValor();
            int count = list.stream().mapToInt(Integer::intValue).sum();
            output.add(new Tupla(elemento.getClave(), count));
        }
    }

    public static void main(String[] args) {
        Tarea t = new Tarea();
        t.setInputFile("weblog.txt");
        t.setOutputfile("Problema1.txt");
        t.setNode(30);
        t.setMapFunction(new Problema1.Map1()); // Utilización de la estrategia de mapeo (DIP)
        t.setReduceFunction(new Problema1.Reduce1()); // Utilización de la estrategia de reducción (DIP)
        t.run(); // Ejecución de la tarea (SRP)
    }

}
