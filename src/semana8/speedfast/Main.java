package semana8.speedfast;

import semana8.speedfast.vista.VistaRepartidor;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaRepartidor ventana = new VistaRepartidor();
            ventana.setVisible(true);
        });
    }
}