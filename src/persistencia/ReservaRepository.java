
package persistencia;

import excepciones.PersistenciaException;
import java.util.List;
import java.util.Optional;
import model.Reserva;


public class ReservaRepository implements Repository<Reserva>{
    private final List<Reserva> reservas;
    private final GestorPersistencia persistencia;

    public ReservaRepository(String nombreArchivoReservas) {
        this.persistencia = new GestorPersistencia<Reserva>(nombreArchivoReservas);
        this.reservas = this.persistencia.cargar();
    }
    
    @Override
    public void add(Reserva reserva) {
        if(reserva!=null){
            boolean existEnPersistencia = reservas.stream().anyMatch(r -> r.getIdReserva() == reserva.getIdReserva());
            if (existEnPersistencia){
                throw new PersistenciaException("El archivo se encontraba cargado anteriormente");
            } else {    
                this.reservas.add(reserva);
                this.persistencia.guardar(reservas);
            }
            
        } else {
            throw new IllegalArgumentException("No se pueden incorporar elementos nulos a reservas");
        }
    }

    @Override
    public Optional<Reserva> findById(int id) {
        return reservas.stream().filter(r -> r.getIdReserva()==id).findFirst();
    }

    @Override
    public List<Reserva> findAll() {
        return this.reservas;
    }
    
}
