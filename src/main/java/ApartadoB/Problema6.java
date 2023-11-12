
package ApartadoB;

import HadoopMini.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kevin
 */
public class Problema6 {

    public static void main(String[] args) {
	Tarea tarea = new Tarea();

	tarea.setInputFile("JCMB_last31days.csv"); // Ruta del archivo CSV de entrada
	tarea.setOutputfile("problema6.txt"); // Ruta del archivo de salida
	tarea.setNode(2); // Número de nodos
	tarea.setMapFunction(new TemperatureMapp());
	tarea.setReduceFunction(new TemperatureReduce());

	
	tarea.run();//Ejecucion de la tarea
    }

}
