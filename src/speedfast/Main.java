package speedfast;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN SPEEDFAST ===");

        // 1. POLIMORFISMO
        ArrayList<Pedido> listaPedidos = new ArrayList<>();

        System.out.println("\n--- PROCESANDO PEDIDOS ---");

        // --- CASO 1: PEDIDO ENCOMIENDA (Flujo Normal) ---
        System.out.println("\n[1] Ingresando Encomienda...");
        PedidoEncomienda encomienda = new PedidoEncomienda(101, "Giovanni Bencini", "Av. Brasil 123", 15.0, 8.0);
        listaPedidos.add(encomienda);

        encomienda.mostrarResumen();
        encomienda.calcularTiempoEntrega();   // Lógica específica de Encomienda [cite: 28]
        encomienda.asignarRepartidor();       // Asignación automática (Polimorfismo: Sobrescritura) [cite: 24]
        encomienda.despachar();               // Interfaz Despachable [cite: 31]


        // --- CASO 2: PEDIDO COMIDA (Asignación Manual) ---
        System.out.println("\n[2] Ingresando Pedido de Comida...");
        PedidoComida comida = new PedidoComida(102, "Ana López", "Calle Falsa 123", 3, "Sushi King");
        listaPedidos.add(comida);

        comida.mostrarResumen();
        comida.calcularTiempoEntrega();                    // Lógica específica de Comida
        comida.asignarRepartidor("Carlos (Motorista)");    // Asignación MANUAL (Polimorfismo: Sobrecarga) [cite: 25]
        comida.despachar();


        // --- CASO 3: PEDIDO EXPRESS (Cancelación) ---
        System.out.println("\n[3] Ingresando Pedido Express...");
        PedidoExpress express = new PedidoExpress(103, "Pedro Diaz", "Av. Central 55", 25000);
        listaPedidos.add(express);

        express.mostrarResumen();
        express.calcularTiempoEntrega();
        express.asignarRepartidor();          // Asignación automática

        // Simulamos que el cliente se arrepiente
        System.out.println("(!) Cliente solicita cancelación...");
        express.cancelar();                   // Interfaz Cancelable


        // 2. USO DE INTERFAZ RASTREABLE
        System.out.println("\n========================================");
        System.out.println("       HISTORIAL DE ENTREGAS");
        System.out.println("========================================");
        
        for (Pedido p : listaPedidos) {
            p.verHistorial(); // [cite: 33, 41]
        }
    }
}