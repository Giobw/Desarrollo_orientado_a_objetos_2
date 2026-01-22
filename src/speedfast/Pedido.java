package speedfast;

// Implementamos las interfaces aquí para que todas las hijas las tengan [cite: 27, 34]
public abstract class Pedido implements Despachable, Cancelable, Rastreable {

    protected int nroPedido;
    protected String cliente;
    protected String direccion;
    protected String estado;

    // Constructor
    public Pedido(int nroPedido, String cliente, String direccion) {
        this.nroPedido = nroPedido;
        this.cliente = cliente;
        this.direccion = direccion;
        this.estado = "Ingresado";
    }

    // Método abstracto
    public abstract void calcularTiempoEntrega();

    // Método concreto
    public void mostrarResumen() {
        System.out.println("--- Resumen del Pedido ---");
        System.out.println("Nro: " + nroPedido);
        System.out.println("Cliente: " + cliente);
        System.out.println("Dirección: " + direccion);
        System.out.println("Estado actual: " + estado);
    }

    // Polimorfismo
    public void asignarRepartidor(String nombre) {
        System.out.println("Repartidor " + nombre + " asignado manualmente al pedido #" + nroPedido);
        this.estado = "Asignado";
    }

    // Método abstracto para Polimorfismo
    public abstract void asignarRepartidor();
}