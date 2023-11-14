

package HadoopMini;

/**
 *
 * @author kevin
 */
public class DatosMeteorologicos {
    
     private double precipitaciones;
    private double humedadRelativa;
    private double sensacionTermica;

    public DatosMeteorologicos(double precipitaciones, double humedadRelativa, double sensacionTermica) {
        this.precipitaciones = precipitaciones;
        this.humedadRelativa = humedadRelativa;
        this.sensacionTermica = sensacionTermica;
    }

    public double getPrecipitaciones() {
        return precipitaciones;
    }

    public double getHumedadRelativa() {
        return humedadRelativa;
    }

    public double getSensacionTermica() {
        return sensacionTermica;
    }

}
