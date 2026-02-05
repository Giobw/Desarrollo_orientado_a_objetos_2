package speedfast;

import java.util.LinkedList;
import java.util.Queue;

public class ZonaDeCarga {
    // Almacena los pedidos usando una estructura de lista/cola
    private Queue<Pedido> pedidos = new LinkedList<>();

    // Método para agregar pedidos de forma segura
    public synchronized void agregarPedido(Pedido p) {
        pedidos.add(p);
        System.out.println("Pedido #" + p.getId() + " agregado. Destino: " + p.getDireccionEntrega());
    }

    // garantiza que el retiro sea seguro y único
    public synchronized Pedido retirarPedido() {
        if (pedidos.isEmpty()) {
            return null; // Retorna null si no hay pedidos
        }
        return pedidos.poll(); // Extrae el pedido de la cola de forma segura
    }

    // Método auxiliar para saber si el proceso terminó
    public synchronized boolean estaVacia() {
        return pedidos.isEmpty();
    }
}