package test;

import model.Reserva;
import model.VehiculoAuto;
import model.VehiculoMoto;
import negocio.GestorAlquileres;

public class Main {

    public static void main(String[] args) {

        try {
            VehiculoAuto v1 = new VehiculoAuto(true, "BMW X5", 100.0);
            VehiculoAuto v2 = new VehiculoAuto(false, "Toyota Corolla", 50.0);
            VehiculoAuto v3 = new VehiculoAuto(true, "Audi A6", 120.0);

            VehiculoMoto m1 = new VehiculoMoto(600, "Yamaha R1", 80.0);
            VehiculoMoto m2 = new VehiculoMoto(450, "Honda CBR", 60.0);
            VehiculoMoto m3 = new VehiculoMoto(650, "Kawasaki Ninja", 90.0);

            Reserva r1 = new Reserva("Pepe");
            Reserva r2 = new Reserva("Juan");

            GestorAlquileres ga = new GestorAlquileres("vehiculos", "reservas");
            ga.agregarVehiculo(v1);
            ga.agregarVehiculo(v2);
            ga.agregarVehiculo(v3);

            ga.agregarVehiculo(m1);
            ga.agregarVehiculo(m2);
            ga.agregarVehiculo(m3);

            r1.agregarVehiculo(v3);
            r1.agregarVehiculo(v1);
            r1.agregarVehiculo(m2);

            r2.agregarVehiculo(m1);

            ga.realizarReserva(r1, 3);
            ga.realizarReserva(r2, 1);

            System.out.println(r1.mostrarDetalle());
            System.out.println(r2.mostrarDetalle());

            System.out.println(ga.traerVehiculos() + "\n");
            System.out.println(ga.traerReservas() + "\n");

            System.out.println(ga.filtrarVehiculos(v -> v.getId() == 2));

            // descuento 10% sobre Audis
            ga.aplicarDescuento(v -> {
                if (v.getModelo().equals("Audi A6")) {
                    return v.getPrecioBasePorDia() * 0.9; 
                }
                return v.getPrecioBasePorDia();
            });
            
            // descuento 30% sobre motos
            ga.aplicarDescuento(v->{
                if(v instanceof VehiculoMoto a){
                    a.setPrecioBasePorDia(a.getPrecioBasePorDia()*0.7);
                }
                return v.getPrecioBasePorDia();
            });
            
            
            System.out.println(ga.traerVehiculos() + "\n");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
