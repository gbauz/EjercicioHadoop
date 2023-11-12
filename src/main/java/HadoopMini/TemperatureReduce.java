
package HadoopMini;

import java.util.ArrayList;

/**
 *
 * @author kevin
 */

/**
 * Agrega el elemento al resultado final.
 */
public class TemperatureReduce implements MyReduce {

    /**
     * Agrega el elemento al resultado final.
     */

    @Override
    public void reduce(Tupla elemento, ArrayList<Tupla> output) {
	output.add(elemento);
    }

}
