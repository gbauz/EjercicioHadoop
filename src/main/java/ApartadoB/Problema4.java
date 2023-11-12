

package ApartadoB;

import HadoopMini.MyMap;
import HadoopMini.MyReduce;
import HadoopMini.Tarea;
import HadoopMini.Tupla;
import java.util.ArrayList;


public class Problema4 {
    
    // Clase interna estática que implementa la interfaz MyMap
    static class MapImpl implements MyMap {
        @Override
        public void map(Tupla elemento, ArrayList<Tupla> output) {
            // Dividir la cadena de valor en palabras
            String[] linea = elemento.getValor().toString().split(" ");

            // Iterar sobre cada palabra
            for (String item : linea) {
                // Dividir la palabra en elementos separados por comas
                String[] lineData = item.split(",");

                // Verificar si los elementos en las posiciones 8 y 12 son diferentes
                if (!lineData[8].equals(lineData[12]))
                    // Agregar una nueva Tupla al resultado
                    output.add(new Tupla(item, 1));
            }
        }
    }

    // Clase interna estática que implementa la interfaz MyReduce
    static class ReduceImpl implements MyReduce {
        @Override
        public void reduce(Tupla elemento, ArrayList<Tupla> output) {
            // Agregar una nueva Tupla al resultado con la clave del elemento de entrada y un valor vacío
            output.add(new Tupla(elemento.getClave(), ""));
        }
    }

    public static void main(String[] args) {
        // Crear una nueva instancia de Tarea
        Tarea nuevaTarea = new Tarea();

        // Configurar las propiedades de la tarea
        nuevaTarea.setInputFile("JCMB_last31days.csv"); // Establecer el archivo de entrada
        nuevaTarea.setOutputfile("Problema4.txt"); // Establecer el archivo de salida
        nuevaTarea.setNode(30); // Establecer el número de nodos para la ejecución de la tarea
        nuevaTarea.setMapFunction(new MapImpl()); // Establecer la implementación de la función de mapeo
        nuevaTarea.setReduceFunction(new ReduceImpl()); // Establecer la implementación de la función de reducción

        // Ejecutar la tarea
        nuevaTarea.run();
    }

}
