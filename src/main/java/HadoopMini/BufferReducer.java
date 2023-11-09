
package HadoopMini;

import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class BufferReducer {
    
     private int numReducer; // Número de reducer
    private ArrayList<Tupla> lstTuplas; // Lista de tuplas asociadas al reducer

    /**
     * Constructor de BufferReducer.
     *
     * @param numReducer  El número de reducer.
     * @param lstTuplas   La lista de tuplas asociadas al reducer.
     */
    public BufferReducer(int numReducer, ArrayList<Tupla> lstTuplas) {
        super();
        this.numReducer = numReducer;
        this.lstTuplas = lstTuplas;
    }

    /**
     * Busca una tupla en la lista de tuplas.
     *
     * @param tupla  La tupla que se desea encontrar.
     * @return La posición de la tupla en la lista, o -1 si no se encuentra.
     */
    public int buscarTuplaEnLst(Tupla tupla) {
        for (int i = 0; i < lstTuplas.size(); i++) {
            String claveTpTmp = ((String) (lstTuplas.get(i)).getClave());
            if (claveTpTmp.compareTo((String) tupla.getClave()) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Agrega una tupla a la lista de tuplas.
     *
     * @param tp  La tupla que se va a agregar.
     */
    public void agregarTuplaAlstTupla(Tupla tp) { //aqui se agrega la tupla a la lista de tuplas
        int index = buscarTuplaEnLst(tp);
        if (index != -1) { //si la tupla ya existe en la lista de tuplas, entonces agrega el valor a la lista de valores
            Tupla tptmp = lstTuplas.get(index); // Obtiene la tupla de la lista
            ArrayList lastTmp = (ArrayList) tptmp.getValor(); // Obtiene la lista de valores de la tupla
            lastTmp.add(tp.getValor()); // Agrega el valor a la lista de valores
            lstTuplas.set(index, new Tupla(tp.getClave(), lastTmp));
        } else {
            ArrayList lstTmp = new ArrayList(); //caso contrario agrega una nueva tupla
            lstTmp.add(tp.getValor());
            lstTuplas.add(new Tupla(tp.getClave(), lstTmp));
        }
    }

    /**
     * Obtiene el número de reducer.
     *
     * @return El número de reducer.
     */
    public int getNumReducer() {
        return numReducer;
    }

    /**
     * Obtiene la lista de tuplas asociadas al reducer.
     *
     * @return La lista de tuplas asociadas al reducer.
     */
    public ArrayList<Tupla> getLstTuplas() {
        return lstTuplas; //retornamos la lista de tuplas
    }

}
