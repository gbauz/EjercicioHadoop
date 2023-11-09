
package HadoopMini;

import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public interface MyReduce {
    
    /**
     * Metodo Reduce
     *
     * @param elemento Tupla
     * @param output   ArrayList que permite agregar las tuplas que queremo, en la cual será el resultado.
     */
    void reduce(Tupla elemento, ArrayList<Tupla> output); //se define el elemento que sera procesado y el output que sera el resultado

}
