
package negocio;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import model.Reserva;
import model.Vehiculo;
import persistencia.Repository;
import persistencia.ReservaRepository;
import persistencia.VehiculoRepository;


public class GestorAlquileres {
    private final Repository<Vehiculo> vehiculoRepository;
    private final Repository<Reserva> reservaRepository;

    public GestorAlquileres(String archivoVehiculos, String archivoReservas) {
        this.vehiculoRepository = new VehiculoRepository(archivoVehiculos);
        this.reservaRepository = new ReservaRepository(archivoReservas);
    }
    
    
    
    public void agregarVehiculo(Vehiculo vehiculo){
        if(vehiculo != null){
            this.vehiculoRepository.add(vehiculo);
        } else {
            throw new IllegalArgumentException("No se puede agregar un elemento nulo");
        }
    }
    
    public Optional<Vehiculo> buscarVehiculoPorId(int id){
        return vehiculoRepository.findById(id);
    }
    
    public void realizarReserva(Reserva reserva, int dias){ 
    // ¿como se chequea la disponibilidad de un vehiculo en este metodo?
    // ¿hay que agregar algun estado booleano tipo isReservado a los vehiculos y chequearlo en este metodo? 
    // de ser así, ¿no sería mejor chequear esto en Reserva, de manera que no puedan agregarse vehiculos ya reservados?
        reserva.calcularTotal(dias);
        reservaRepository.add(reserva);
        System.out.println("Reserva agregada exitosamente");
    }
    
    public double calcularIngresos(){
        List<Reserva> listaReservas = reservaRepository.findAll();
        return listaReservas.stream().mapToDouble(Reserva::getTotal).sum();
        
    }
    
    public List<Vehiculo> filtrarVehiculos(Predicate<Vehiculo> criterio){
        List<Vehiculo> listaVehiculos =vehiculoRepository.findAll(); 
        return listaVehiculos.stream().filter(criterio).toList();
    }
    
    public void aplicarDescuento(Function<Vehiculo, Double> descuento) {
        List<Vehiculo> listaVehiculos = vehiculoRepository.findAll();
        listaVehiculos.forEach(v -> {
            double nuevoPrecioBase = descuento.apply(v);
            if (nuevoPrecioBase>0){
                v.setPrecioBasePorDia(nuevoPrecioBase);
            } else {
                v.setPrecioBasePorDia(0); // si el descuento dejara el precio por debajo de 0, lo establezco en 0 para evitar negativos que rompan.¿es correcto esto?
                System.out.println("El precio se establecio en 0");
            }
        });
    }
    
    public List<Reserva> traerReservas(){
        return reservaRepository.findAll();
    }
    
    public List<Vehiculo> traerVehiculos(){
        return vehiculoRepository.findAll();
    }
    
}
