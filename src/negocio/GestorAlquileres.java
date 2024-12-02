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

    public void agregarVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            this.vehiculoRepository.add(vehiculo);
        } else {
            throw new IllegalArgumentException("No se puede agregar un elemento nulo al repositorio de vehiculos");
        }
    }

    public Optional<Vehiculo> buscarVehiculoPorId(int id) {
        return vehiculoRepository.findById(id);
    }

    public void realizarReserva(Reserva reserva, int dias) {
        List<Vehiculo> listaVehiculosReserva = reserva.getVehiculos();
        for (Vehiculo v : listaVehiculosReserva) {
            Optional<Vehiculo> vehiculoEnRepo = this.buscarVehiculoPorId(v.getId());

            if (vehiculoEnRepo.isEmpty()) {
                throw new IllegalArgumentException(
                        "El vehiculo con ID " + v.getId() + " no se encuentra en el repositorio de vehiculos. No se puede realizar la reserva."
                );
            }
        }

        reserva.calcularTotal(dias);
        reservaRepository.add(reserva);
        System.out.println("Reserva agregada exitosamente");
    }

    public double calcularIngresos() {
        List<Reserva> listaReservas = reservaRepository.findAll();
        return listaReservas.stream().mapToDouble(Reserva::getTotal).sum();

    }

    public List<Vehiculo> filtrarVehiculos(Predicate<Vehiculo> criterio) {
        List<Vehiculo> listaVehiculos = vehiculoRepository.findAll();
        return listaVehiculos.stream().filter(criterio).toList();
    }

    public void aplicarDescuento(Function<Vehiculo, Double> descuento) {
        List<Vehiculo> listaVehiculos = vehiculoRepository.findAll();
        listaVehiculos.forEach(v -> {
            double nuevoPrecioBase = descuento.apply(v);
            if (nuevoPrecioBase > 0) {
                v.setPrecioBasePorDia(nuevoPrecioBase);
            } else {
                v.setPrecioBasePorDia(0); // si el descuento dejara el precio por debajo de 0, lo establezco en 0 para evitar negativos que rompan.¿es correcto esto?
                System.out.println("El precio se establecio en 0");
            }
        });
    }

    public List<Reserva> traerReservas() {
        return reservaRepository.findAll();
    }

    public List<Vehiculo> traerVehiculos() {
        return vehiculoRepository.findAll();
    }

}
