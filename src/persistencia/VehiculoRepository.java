
package persistencia;

import excepciones.PersistenciaException;
import java.util.List;
import java.util.Optional;
import model.Vehiculo;

public class VehiculoRepository implements Repository<Vehiculo>{
    
    private final List<Vehiculo> vehiculos;
    private final GestorPersistencia persistencia;

    public VehiculoRepository(String nombreArchivoVehiculos) {
        this.persistencia = new GestorPersistencia<Vehiculo>(nombreArchivoVehiculos);
        this.vehiculos = this.persistencia.cargar();
    }
    
    @Override
    public void add(Vehiculo vehiculo) {
        boolean existeEnPersistencia = vehiculos.stream().anyMatch(v -> v.getId() == vehiculo.getId());
        if(existeEnPersistencia){
            throw new PersistenciaException("Error de persistencia: El archivo se encontraba cargado anteriormente");
        } else {
            this.vehiculos.add(vehiculo);
            this.persistencia.guardar(vehiculos);
        }
      
    }

    @Override
    public Optional<Vehiculo> findById(int id) {
        return vehiculos.stream().filter(v -> v.getId() == id).findFirst();
    }

    @Override
    public List<Vehiculo> findAll() {
        return this.vehiculos;
    }
    
}
