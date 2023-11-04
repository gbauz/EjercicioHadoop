package HadoopMini;

/*
 * Hecho por: Guillermo Baquerizo Mejia
 * */

import java.io.*;
import java.util.ArrayList;

public class Files {
    private String inputFile; // Ruta del archivo de entrada
    private String outputFile; // Ruta del archivo de salida
    private ArrayList<Tupla> lstTuplas; // Lista de tuplas

    /**
     * Constructor de Files.
     *
     * @param inputFile   Nombre del archivo de entrada.
     * @param outputFile  Nombre del archivo de salida.
     */
    public Files(String inputFile, String outputFile) {
        this.inputFile = "C:\\Users\\kevin\\OneDrive\\Escritorio\\FRPB-AD-Unidad2-ProyectoCodigo2-Ficheros\\" + inputFile;
        this.outputFile = "C:\\Users\\kevin\\OneDrive\\Escritorio\\FRPB-AD-Unidad2-ProyectoCodigo2-Ficheros\\data\\resultados\\" + outputFile;
        lstTuplas = new ArrayList<>();
    }

    /**
     * Obtiene la lista de tuplas.
     *
     * @return La lista de tuplas.
     */
    public ArrayList<Tupla> getLstTuplas() {
        return lstTuplas;
    }

    /**
     * Establece la lista de tuplas.
     *
     * @param lstTuplas La lista de tuplas a establecer.
     */
    public void setLstTuplas(ArrayList<Tupla> lstTuplas) {
        this.lstTuplas = lstTuplas;
    }

    /**
     * Lee un archivo y almacena los datos en una lista de tuplas.
     *
     * @param node El número de nodo/reducer.
     * @return La lista de tuplas leídas del archivo.
     */
    public ArrayList<Tupla> makeBufferMap(int node) {
        System.out.println("Leyendo el archivo " + inputFile);
        try {
            FileReader fr = new FileReader(inputFile);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea.trim()).append(" ");
            }
            br.close();
            fr.close();
            int hash = sb.hashCode() % node;
            lstTuplas.add(new Tupla(hash, sb));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
        return lstTuplas;
    }

    /**
     * Guarda los datos de la lista de tuplas en un archivo.
     *
     * @param lstTuplas La lista de tuplas a guardar.
     */
    public void savedata(ArrayList<Tupla> lstTuplas) {
        try {
            FileWriter fw = new FileWriter(outputFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Tupla tupla : lstTuplas) {
                bw.write(tupla.getClave() + "," + tupla.getValor() + "\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
