package model;

public class Empleado {
    private String documento;
    private String nombre;
    private String telefono;
    private String tipo;
    private int horasTrabajadas;

    public Empleado(String documento, String nombre, String telefono, String tipo, int horasTrabajadas) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipo = tipo;
        this.horasTrabajadas = horasTrabajadas;
    }

    public String getDocumento() { return documento; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getTipo() { return tipo; }
    public int getHorasTrabajadas() { return horasTrabajadas; }
}

