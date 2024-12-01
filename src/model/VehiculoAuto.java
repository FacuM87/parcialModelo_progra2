
package model;


public class VehiculoAuto extends Vehiculo {
    private static final long serialVersionUID = 1L;

    private final boolean esLujo;

    public VehiculoAuto(boolean esLujo, String modelo, double precioBasePorDia) {
        super(modelo, precioBasePorDia);
        this.esLujo = esLujo;
    }

    

    @Override
    public double calcularCostoAlquiler(int dias) {
        double costoAlquilerPorDia = this.getPrecioBasePorDia();
        if(this.esLujo){
            costoAlquilerPorDia *= 1.25;
        }
        return costoAlquilerPorDia * dias;
    }
    
    
}
