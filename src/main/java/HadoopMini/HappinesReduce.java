
package HadoopMini;

import java.util.ArrayList;

/**
 *
 * @author kevin
 */

/**
 * Analiza cada elemento y agrega las palabras extremadamente tristes al resultado.
 */
public class HappinesReduce implements MyReduce {

    /**
     * Suma los valores
     * asociados a cada clave y agrega el resultado al resultado final.
     */
    @Override
    public void reduce(Tupla elemento, ArrayList<Tupla> output) {
	ArrayList<Integer> list = (ArrayList<Integer>) elemento.getValor();
	int count = list.stream().mapToInt(Integer::intValue).sum();
	output.add(new Tupla(elemento.getClave(), count));
    }
}
