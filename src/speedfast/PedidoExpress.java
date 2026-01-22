package speedfast;

public class PedidoExpress extends Pedido {
    private double montoCompra;

    // Constructor
    public PedidoExpress(int nroPedido, String cliente, String direccion, double montoCompra) {
        super(nroPedido, cliente, direccion);
        this.montoCompra = montoCompra;
    }

    // 1. Lógica específica
    @Override
    public void calcularTiempoEntrega() {
        System.out.println("Tiempo estimado Express: 25 minutos fijos (Garantía SpeedFast).");
    }

    // 2. Asignación automática
    @Override
    public void asignarRepartidor() {
        System.out.println("Buscando shopper disponible en la zona...");
        this.estado = "Asignado Shopper";
    }

    // 3. Interfaces
    @Override
    public void despachar() {
        this.estado = "Entregado al cliente";
        System.out.println("El pedido express ha sido entregado en mano.");
    }

    @Override
    public void cancelar() {
        this.estado = "Cancelado";
        System.out.println("Compra express cancelada. Productos devueltos a estantería.");
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial Express #" + nroPedido + ": Creado -> Pagado ($" + montoCompra + ") -> " + estado);
    }
}