
package HadoopMini;

import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public interface MyMap { // Interfaz que permite definir el proceso de Map
    /**
     * Proceso de Map de los datos.
     *
     * @param elemento Tupla
     * @param output   ArrayList que permite agregar las tuplas que queremos.
     */
    void map(Tupla elemento, ArrayList<Tupla> output);// Método que permite definir el proceso de Map
    
    

}
