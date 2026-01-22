package speedfast;

public class PedidoEncomienda extends Pedido {
    private double pesoKg;
    private double distanciaKm;

    public PedidoEncomienda(int nroPedido, String cliente, String direccion, double pesoKg, double distanciaKm) {
        super(nroPedido, cliente, direccion);
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
    }

    // Implementación del método abstracto
    @Override
    public void calcularTiempoEntrega() {
        int tiempo = (int) (distanciaKm * 5);
        System.out.println("Tiempo estimado encomienda: " + tiempo + " min (Distancia: " + distanciaKm + "km)");
    }

    // Sobrescritura de asignarRepartidor
    @Override
    public void asignarRepartidor() {
        String vehiculo = (pesoKg > 50) ? "Camión" : "Furgoneta";
        System.out.println("Asignando " + vehiculo + " automáticamente para la encomienda.");
        this.estado = "Asignado Auto";
    }

    // Métodos de las interfaces
    @Override
    public void despachar() {
        this.estado = "Despachado";
        System.out.println("Encomienda en ruta hacia " + this.direccion);
    }

    @Override
    public void cancelar() {
        this.estado = "Cancelado";
        System.out.println("El envío de la encomienda ha sido cancelado.");
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial Encomienda #" + nroPedido + ": Creado -> " + estado);
    }
}