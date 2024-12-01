
package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Reserva implements Serializable{
    private static final long serialVersionUID = 1L;


    private static int contadorIdReserva = 1;
    private final int idReserva;
    private final String cliente;
    private final List<Vehiculo> vehiculos;
    private double total;

    public Reserva(String cliente) {
        this.idReserva = contadorIdReserva++;
        this.cliente = cliente;
        this.vehiculos = new ArrayList<>();
        
    }
    
    public void agregarVehiculo(Vehiculo vehiculo){
        if(vehiculo != null){
            this.vehiculos.add(vehiculo);
        } else {
            throw new IllegalArgumentException("No se puede agregar un elemento nulo a la lista de vehiculos");
        }        
    }
    
    public void calcularTotal(int dias){
        double montoTotal = 0;
        for (Vehiculo v : vehiculos){
            montoTotal += v.calcularCostoAlquiler(dias); 
        }
        this.setTotal(montoTotal);
    }
    
    public String mostrarDetalle(){
        return "ID reserva: " + this.idReserva +"\n"+"Cliente: "+this.cliente+"\n"+"Total: $"+this.total+"\n";
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public int getIdReserva() {
        return idReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" + "idReserva=" + idReserva + ", cliente=" + cliente + ", vehiculos=" + vehiculos + ", total=" + total + '}';
    }
    
    
     
}
