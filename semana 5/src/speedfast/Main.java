package speedfast;

public class Main {
    public static void main(String[] args) {
        System.out.println("[Zona de carga inicializada]");
        ZonaDeCarga zona = new ZonaDeCarga();

        zona.agregarPedido(new Pedido(1, "Santiago Centro"));
        zona.agregarPedido(new Pedido(2, "Providencia"));
        zona.agregarPedido(new Pedido(3, "Ñuñoa"));
        zona.agregarPedido(new Pedido(4, "Recoleta"));
        zona.agregarPedido(new Pedido(5, "Las Condes"));


        Thread h1 = new Thread(new Repartidor("Juan", zona));
        Thread h2 = new Thread(new Repartidor("Camila", zona));
        Thread h3 = new Thread(new Repartidor("Pedro", zona));

        h1.start();
        h2.start();
        h3.start();

        try {
            h1.join();
            h2.join();
            h3.join();
            System.out.println("Todos los pedidos han sido entregados correctamente.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}