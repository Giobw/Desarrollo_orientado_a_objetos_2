package speedfast;

public class PedidoComida extends Pedido {
    private int cantidadPlatos;
    private String restaurante;

    public PedidoComida(int nroPedido, String cliente, String direccion, int cantidadPlatos, String restaurante) {
        super(nroPedido, cliente, direccion);
        this.cantidadPlatos = cantidadPlatos;
        this.restaurante = restaurante;
    }

    // 1. Lógica específica de tiempo
    @Override
    public void calcularTiempoEntrega() {
        int tiempoPrep = cantidadPlatos * 10;
        int tiempoTotal = tiempoPrep + 15;
        System.out.println("Tiempo estimado comida (" + restaurante + "): " + tiempoTotal + " minutos.");
    }

    // 2. Asignación automática
    @Override
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor en MOTO para mantener la comida caliente.");
        this.estado = "Asignado Moto";
    }

    // 3. Interfaces
    @Override
    public void despachar() {
        this.estado = "En camino";
        System.out.println("El pedido de " + restaurante + " ha sido retirado y va en camino.");
    }

    @Override
    public void cancelar() {
        this.estado = "Cancelado";
        System.out.println("Pedido de comida cancelado. Comida desechada.");
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial Comida #" + nroPedido + " (" + restaurante + "): Creado -> " + estado);
    }
}