package HadoopMini;

import java.util.ArrayList;

public class BufferMap {
    private final ArrayList<Tupla> lstParticionada = new ArrayList<>(); // Lista de tuplas particionadas
    private final ArrayList<BufferReducer> lstOrdenada = new ArrayList<>(); // Lista de tuplas ordenadas

    /**
     * Constructor vacío de BufferMap.
     */
    public BufferMap() {
    }

    /**
     * Obtiene la lista ordenada de BufferReducer.
     *
     * @return La lista ordenada de BufferReducer.
     */
    public ArrayList<BufferReducer> getLstOrdenada() {
        return lstOrdenada;
    }

    /**
     * Realiza el particionamiento de los datos.
     *
     * @param lstTuplas La lista de tuplas a particionar.
     * @param nodos     El número de nodos (reducers).
     */
    public void particionarBuffer(ArrayList<Tupla> lstTuplas, int nodos) {
        for (Tupla tupla : lstTuplas) {
            int nodoReducer = tupla.getClave().hashCode() % nodos; // Calcula el nodo reducer para la tupla
            lstParticionada.add(new Tupla(nodoReducer, tupla)); // Agrega la tupla particionada a la lista
        }
    }

    /**
     * Ordena las tuplas según su clave y valor.
     */
    public void ordenadoBuffer() {
        for (Tupla tupla : lstParticionada) {
            int clave1 = (int) tupla.getClave(); // Obtiene la clave de la tupla

            int posicion = buscarbufferreduer(clave1); // Busca la posición de la tupla en la lista ordenada
            Tupla tuplaDelaTupla = (Tupla) tupla.getValor(); // Obtiene el valor de la tupla

            if (posicion != -1) { // Si la tupla ya existe en la lista ordenada, agrega el valor a la lista de valores
                BufferReducer bfr = lstOrdenada.get(posicion);
                bfr.agregarTuplaAlstTupla(tuplaDelaTupla);
                lstOrdenada.set(posicion, bfr);
            } else { // Si no existe, agrega una nueva tupla
                ArrayList tmpLstValores = new ArrayList();
                tmpLstValores.add(tuplaDelaTupla.getValor());
                ArrayList<Tupla> tmp = new ArrayList<>();
                tmp.add(new Tupla(tuplaDelaTupla.getClave(), tmpLstValores));
                lstOrdenada.add(new BufferReducer(clave1, tmp));
            }
        }
    }

    /**
     * Busca un objeto BufferReducer en la lista ordenada.
     *
     * @param reducer El reducer que se quiere buscar.
     * @return La posición del objeto BufferReducer en la lista ordenada, o -1 si no se encuentra.
     */
    private int buscarbufferreduer(int reducer) {
        for (int i = 0; i < lstOrdenada.size(); i++) {
            if (lstOrdenada.get(i).getNumReducer() == reducer) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Imprime el contenido del BufferMap.
     */
    public void imprimirBufferMap() {
        System.out.println("Buffer Map");
        // Itera sobre cada tupla particionada y las imprime
        for (Tupla tupla : lstParticionada) {
            System.out.println(tupla.getClave() + " " + tupla.getValor());
        }
    }
}