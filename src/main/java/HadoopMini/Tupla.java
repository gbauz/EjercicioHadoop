package HadoopMini;

public class Tupla {
    private Object clave; //son tipo Object para que pueda ser cualquier tipo de dato
    private Object valor; //son tipo Object para que pueda ser cualquier tipo de dato

    public Tupla(Object clave, Object valor) { // Constructor
        this.clave = clave;
        this.valor = valor;
    }
    public Object getClave() {
        return clave;
    }
    public Object getValor() {
        return valor;
    }
}
