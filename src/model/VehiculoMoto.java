
package model;


public class VehiculoMoto extends Vehiculo {
    private static final long serialVersionUID = 1L;

    private final int cilindrada;

    public VehiculoMoto(int cilindrada, String modelo, double precioBasePorDia) {
        super(modelo, precioBasePorDia);
        this.cilindrada = cilindrada;
    }


    @Override
    public double calcularCostoAlquiler(int dias) {
        double costoAlquilerPorDia = this.getPrecioBasePorDia();
        if(this.cilindrada>500){
            costoAlquilerPorDia *= 1.15;
        }
        return costoAlquilerPorDia*dias;
    }
    
    
}
