

package HadoopMini;

import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class TemperatureMapper implements MyMap{
    
        public void map(Tupla elemento, ArrayList<Tupla> output) {
            String[] campos = elemento.getValor().toString().split(",");

            if (campos.length >= 9) {
                String index = campos[0];
                String year = campos[1];
                String dayOfYear = campos[2];
                String time = campos[3];
                double surfaceTemperature = Double.parseDouble(campos[8]);

                // Verifica si el valor es válido
                if (surfaceTemperature >= 0) {
                    output.add(new Tupla(index + "," + year + "," + dayOfYear + "," + time, surfaceTemperature));
                }
            }
        }

}
