package model;

import java.io.Serializable;

public abstract class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L; 
    private static int contadorId = 1;
    protected int id;
    protected String modelo;
    protected double precioBasePorDia;

    public Vehiculo(String modelo, double precioBasePorDia) {
        this.id = contadorId++;
        if (modelo.length() > 2) {
            this.modelo = modelo;
        } else {
            throw new IllegalArgumentException("El modelo debe contener no menos de 3 caracteres");
        }

        if (precioBasePorDia > 0) {
            this.precioBasePorDia = precioBasePorDia;
        } else {
            throw new IllegalArgumentException("El precio debe ser un numero positivo");
        }

    }

    public abstract double calcularCostoAlquiler(int dias);

    public String mostrarResumen() {
        return "Id: " + this.id + "\n" + "Modelo: " + this.modelo + "\n" + "Precio base: " + this.precioBasePorDia;
    }

    public double getPrecioBasePorDia() {
        return precioBasePorDia;
    }

    public int getId() {
        return id;
    }

    public void setPrecioBasePorDia(double precioBasePorDia) {
        this.precioBasePorDia = precioBasePorDia;
    }
    
    public String getModelo(){
        return this.modelo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", modelo=" + modelo + ", precioBasePorDia=" + precioBasePorDia + '}';
    }

}
