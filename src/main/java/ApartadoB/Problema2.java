

package ApartadoB;

import HadoopMini.*;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class Problema2 {
    
     interface StringAnalyzer { //Interfaz para analizar las palabras
        boolean containsGif(String word); //Método para comprobar si la palabra contiene .gif
    }

    static class Map1 implements MyMap {
        private final StringAnalyzer analyzer;

        // Aplicación del principio de inversión de dependencias (DIP)
        public Map1(StringAnalyzer analyzer) {
            this.analyzer = analyzer;
        }

        @Override
        public void map(Tupla elemento, ArrayList<Tupla> output) { //El map se encarga de añadir a la lista de tuplas las palabras que contengan .gif
            String[] words = elemento.getValor().toString().split(" ");
            for (String word : words) { // Recorremos las palabras
                String newWord = word.toLowerCase();

                if (analyzer.containsGif(newWord))
                    output.add(new Tupla("gif", 1));
            }
        }
    }

    static class Reduce implements MyReduce { //El reduce se encarga de sumar los valores de la lista de tuplas
        @Override
        public void reduce(Tupla elemento, ArrayList<Tupla> output) {
            ArrayList<Integer> list = (ArrayList<Integer>) elemento.getValor();//Obtenemos la lista de valores
            int count = list.stream().mapToInt(Integer::intValue).sum(); //Sumamos los valores de la lista
            output.add(new Tupla(elemento.getClave(), count)); //Añadimos a la lista de tuplas el resultado
        }
    }

    static class StringAnalyzerImpl implements StringAnalyzer { //Implementación de la interfaz StringAnalyzer
        @Override
        public boolean containsGif(String word) {
            return word.contains(".gif");
        } //Comprobamos si la palabra contiene .gif
    }

    public static void main(String[] args) {
        Tarea tarea1 = new Tarea();

        tarea1.setReduceFunction(new Reduce());

        // Creación de una instancia de StringAnalyzerImpl
        StringAnalyzer analyzer = new StringAnalyzerImpl();

        Map1 map1 = new Map1(analyzer);

        tarea1.setMapFunction(map1);
        tarea1.setInputFile("weblog.txt");
        tarea1.setOutputfile("Problema2.txt");
        tarea1.setNode(2);

        tarea1.run();
    }

}
