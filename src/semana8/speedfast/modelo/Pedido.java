package semana8.speedfast.modelo;

public class Pedido {
    private int id;
    private String direccion;
    private String tipo;
    private String estado;

    public Pedido(int id, String direccion, String tipo, String estado) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Pedido(String direccion, String tipo, String estado) {
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getId() { return id; }
    public String getDireccion() { return direccion; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
}