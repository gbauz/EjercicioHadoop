

package HadoopMini;

import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class TemperatureReducer implements MyReduce {
    
     @Override
    public void reduce(Tupla elemento, ArrayList<Tupla> output) {
        String fecha = (String) elemento.getClave();

        ArrayList<Double> temperaturas = (ArrayList<Double>) elemento.getValor();

        double minTemp = Double.MAX_VALUE;
        double maxTemp = Double.MIN_VALUE;

        for (Double temperature : temperaturas) {
            minTemp = Math.min(minTemp, temperature);
            maxTemp = Math.max(maxTemp, temperature);
        }

        output.add(new Tupla(fecha, "Mínima: " + minTemp + ", Máxima: " + maxTemp));
    }

}
