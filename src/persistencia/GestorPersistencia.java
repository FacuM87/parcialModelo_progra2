package persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GestorPersistencia<T> {

    private String archivo;

    public GestorPersistencia(String archivo) {
        this.archivo = archivo;
    }
    
    public void guardar(List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.archivo+".bin"))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Error al guardar datos: "+e.getMessage());
        }
    }

    public List<T> cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.archivo+".bin"))) {
            return (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar datos: "+e.getMessage());
            return null;
        }
    }
}