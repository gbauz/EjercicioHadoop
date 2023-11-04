package HadoopMini;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tarea {
    private String inputFile;
    private String outputfile;
    private int node;
    private MyMap mapFunction;
    private MyReduce reduceFunction;
    private Object combinerFunction;

    public Tarea() {
	super();
	inputFile = "";
	outputfile = "";
	node = 0;
	mapFunction = null;
	reduceFunction = null;
	combinerFunction = "";
    }

    public void run() {
	Files arhivoM = new Files(this.inputFile, this.outputfile);

	BufferMap bfm = new BufferMap();
	ArrayList<Tupla> resultado = new ArrayList<>();
	ArrayList<Tupla> lstBuffers = arhivoM.makeBufferMap(node);
	if (lstBuffers.isEmpty()) {
	    Logger.getAnonymousLogger().log(Level.SEVERE, "No se ha podido cargar el archivo");
	    return;
	}

	try {
	    System.out.println("Iniciando proceso de Map");
	    for (Tupla tupla : lstBuffers) {
		ArrayList<Tupla> output = new ArrayList<>();
		mapFunction.map(tupla, output);
		bfm.particionarBuffer(output, this.node);
	    }
	    System.out.println("Iniciando proceso de Ordenamiento");
	    bfm.ordenadoBuffer();
	    ArrayList<BufferReducer> lstOrdenada = bfm.getLstOrdenada();

	    System.out.println("Iniciando proceso de Reduce");

	    for (BufferReducer bufferReducer : lstOrdenada) {
		ArrayList<Tupla> lstTuplasReducer = bufferReducer.getLstTuplas();
		for (Tupla tuplaReducer : lstTuplasReducer) {
		    reduceFunction.reduce(tuplaReducer, resultado);
		}
	    }
	    System.out.println("Guardando los datos en " + outputfile);
	    arhivoM.savedata(resultado);
	} catch (Exception e) {

	    e.printStackTrace();// Mensaje de exceptiion en caso de errores
	}
    }

    public String getInputFile() {
	return inputFile;
    }

    public void setInputFile(String inputFile) {
	this.inputFile = inputFile;
    }

    public String getOutputfile() {
	return outputfile;
    }

    public void setOutputfile(String outputfile) {
	this.outputfile = outputfile;
    }

    public int getNode() {
	return node;
    }

    public void setNode(int node) { // los nodos son los que se encargan de hacer el particionamiento
	this.node = node;
    }

    public MyMap getMapFunction() {
	return mapFunction;
    }

    public void setMapFunction(MyMap mapFunction) {
	this.mapFunction = mapFunction;
    }

    public MyReduce getReduceFunction() {
	return reduceFunction;
    }

    public void setReduceFunction(MyReduce reduceFunction) {
	this.reduceFunction = reduceFunction;
    }

    public Object getCombinerFunction() {
	return combinerFunction;
    }

    public void setCombinerFunction(Object combinerFunction) {
	this.combinerFunction = combinerFunction;
    }
}