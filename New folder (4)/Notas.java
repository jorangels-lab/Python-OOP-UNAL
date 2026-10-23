import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ejercicio 8.2 - Notas
 * Solicita cinco notas y muestra promedio, desviacion estandar,
 * mayor nota y menor nota.
 */
public class Notas extends JFrame implements ActionListener {

    private static final int CANTIDAD_NOTAS = 5;

    private JLabel[] etiquetasNotas;
    private JTextField[] camposNotas;
    private JButton botonCalcular;
    private JLabel lblPromedio;
    private JLabel lblDesviacion;
    private JLabel lblMayor;
    private JLabel lblMenor;

    public Notas() {
        super();
        setTitle("Calculo de notas");
        setSize(400, 440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container contenedor = getContentPane();
        contenedor.setLayout(null);

        // Titulo / instruccion
        JLabel instruccion = new JLabel("Ingrese las cinco notas del estudiante:");
        instruccion.setBounds(30, 15, 330, 25);
        contenedor.add(instruccion);

        // Etiquetas y campos de texto para las notas
        etiquetasNotas = new JLabel[CANTIDAD_NOTAS];
        camposNotas = new JTextField[CANTIDAD_NOTAS];
        for (int i = 0; i < CANTIDAD_NOTAS; i++) {
            etiquetasNotas[i] = new JLabel();
            etiquetasNotas[i].setText("Nota " + (i + 1) + ":");
            etiquetasNotas[i].setBounds(50, 55 + i * 35, 80, 25);
            contenedor.add(etiquetasNotas[i]);

            camposNotas[i] = new JTextField();
            camposNotas[i].setBounds(140, 55 + i * 35, 120, 25);
            contenedor.add(camposNotas[i]);
        }

        // Boton calcular
        botonCalcular = new JButton();
        botonCalcular.setText("Calcular");
        botonCalcular.setBounds(140, 240, 120, 30);
        botonCalcular.addActionListener(this);
        contenedor.add(botonCalcular);

        // Resultados en la parte inferior de la ventana
        lblPromedio = new JLabel("Promedio: ");
        lblPromedio.setBounds(50, 290, 300, 25);
        contenedor.add(lblPromedio);

        lblDesviacion = new JLabel("Desviacion estandar: ");
        lblDesviacion.setBounds(50, 320, 300, 25);
        contenedor.add(lblDesviacion);

        lblMayor = new JLabel("Mayor nota: ");
        lblMayor.setBounds(50, 350, 300, 25);
        contenedor.add(lblMayor);

        lblMenor = new JLabel("Menor nota: ");
        lblMenor.setBounds(50, 380, 300, 25);
        contenedor.add(lblMenor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonCalcular) {
            calcular();
        }
    }

    private void calcular() {
        double[] notas = new double[CANTIDAD_NOTAS];

        // Lectura y validacion de las notas
        for (int i = 0; i < CANTIDAD_NOTAS; i++) {
            String texto = camposNotas[i].getText().trim().replace(',', '.');
            try {
                notas[i] = Double.parseDouble(texto);
            } catch (NumberFormatException ex) {
                limpiarResultados();
                lblPromedio.setText("Error: la nota " + (i + 1) + " no es un numero valido.");
                return;
            }
        }

        // Promedio, mayor y menor
        double suma = 0;
        double mayor = notas[0];
        double menor = notas[0];
        for (int i = 0; i < CANTIDAD_NOTAS; i++) {
            suma += notas[i];
            if (notas[i] > mayor) {
                mayor = notas[i];
            }
            if (notas[i] < menor) {
                menor = notas[i];
            }
        }
        double promedio = suma / CANTIDAD_NOTAS;

        // Desviacion estandar: raiz de la suma de (nota - promedio)^2 dividida entre n
        double sumaCuadrados = 0;
        for (int i = 0; i < CANTIDAD_NOTAS; i++) {
            sumaCuadrados += Math.pow(notas[i] - promedio, 2);
        }
        double desviacion = Math.sqrt(sumaCuadrados / CANTIDAD_NOTAS);

        lblPromedio.setText(String.format("Promedio: %.2f", promedio));
        lblDesviacion.setText(String.format("Desviacion estandar: %.2f", desviacion));
        lblMayor.setText(String.format("Mayor nota: %.2f", mayor));
        lblMenor.setText(String.format("Menor nota: %.2f", menor));
    }

    private void limpiarResultados() {
        lblDesviacion.setText("Desviacion estandar: ");
        lblMayor.setText("Mayor nota: ");
        lblMenor.setText("Menor nota: ");
    }

    public static void main(String[] args) {
        Notas ventana = new Notas();
        ventana.setVisible(true);
    }
}

