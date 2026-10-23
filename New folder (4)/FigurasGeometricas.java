import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ejercicio 8.3 - Gestion de eventos
 * Ventana que calcula el volumen y la superficie de un cilindro,
 * una esfera y una piramide a partir de las medidas ingresadas (en cm).
 */
public class FigurasGeometricas extends JFrame implements ActionListener {

    private static final String CUBO = "cm³";
    private static final String CUADRADO = "cm²";

    private Container contenedor;

    // Cilindro
    private JTextField txtCilRadio;
    private JTextField txtCilAltura;
    private JButton btnCilindro;
    private JLabel lblCilVolumen;
    private JLabel lblCilSuperficie;

    // Esfera
    private JTextField txtEsfRadio;
    private JButton btnEsfera;
    private JLabel lblEsfVolumen;
    private JLabel lblEsfSuperficie;

    // Piramide
    private JTextField txtPirBase;
    private JTextField txtPirAltura;
    private JTextField txtPirApotema;
    private JButton btnPiramide;
    private JLabel lblPirVolumen;
    private JLabel lblPirSuperficie;

    public FigurasGeometricas() {
        super();
        setTitle("Figuras geométricas");
        setSize(520, 510);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        contenedor = getContentPane();
        contenedor.setLayout(null);

        // ---------- Cilindro ----------
        crearTitulo("CILINDRO", 10);
        crearEtiqueta("Radio (cm):", 40, 40, 100);
        txtCilRadio = crearCampo(150, 40);
        crearEtiqueta("Altura (cm):", 40, 70, 100);
        txtCilAltura = crearCampo(150, 70);
        btnCilindro = crearBoton("Calcular", 300, 55);
        lblCilVolumen = crearEtiqueta("Volumen: ", 40, 105, 420);
        lblCilSuperficie = crearEtiqueta("Superficie: ", 40, 130, 420);

        // ---------- Esfera ----------
        crearTitulo("ESFERA", 170);
        crearEtiqueta("Radio (cm):", 40, 200, 100);
        txtEsfRadio = crearCampo(150, 200);
        btnEsfera = crearBoton("Calcular", 300, 200);
        lblEsfVolumen = crearEtiqueta("Volumen: ", 40, 235, 420);
        lblEsfSuperficie = crearEtiqueta("Superficie: ", 40, 260, 420);

        // ---------- Piramide ----------
        crearTitulo("PIRÁMIDE", 300);
        crearEtiqueta("Base (cm):", 40, 330, 100);
        txtPirBase = crearCampo(150, 330);
        crearEtiqueta("Altura (cm):", 40, 360, 100);
        txtPirAltura = crearCampo(150, 360);
        crearEtiqueta("Apotema (cm):", 40, 390, 100);
        txtPirApotema = crearCampo(150, 390);
        btnPiramide = crearBoton("Calcular", 300, 360);
        lblPirVolumen = crearEtiqueta("Volumen: ", 40, 425, 420);
        lblPirSuperficie = crearEtiqueta("Superficie: ", 40, 450, 420);
    }

    // ---------- Metodos auxiliares para construir la interfaz ----------

    private void crearTitulo(String texto, int y) {
        JLabel titulo = new JLabel();
        titulo.setText(texto);
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        titulo.setBounds(20, y, 200, 25);
        contenedor.add(titulo);
    }

    private JLabel crearEtiqueta(String texto, int x, int y, int ancho) {
        JLabel etiqueta = new JLabel();
        etiqueta.setText(texto);
        etiqueta.setBounds(x, y, ancho, 25);
        contenedor.add(etiqueta);
        return etiqueta;
    }

    private JTextField crearCampo(int x, int y) {
        JTextField campo = new JTextField();
        campo.setBounds(x, y, 110, 25);
        contenedor.add(campo);
        return campo;
    }

    private JButton crearBoton(String texto, int x, int y) {
        JButton boton = new JButton();
        boton.setText(texto);
        boton.setBounds(x, y, 100, 30);
        boton.addActionListener(this);
        contenedor.add(boton);
        return boton;
    }

    // ---------- Gestion de eventos ----------

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();
        try {
            if (origen == btnCilindro) {
                double radio = leerValor(txtCilRadio, "el radio del cilindro");
                double altura = leerValor(txtCilAltura, "la altura del cilindro");
                mostrarResultados(new Cilindro(radio, altura), lblCilVolumen, lblCilSuperficie);
            } else if (origen == btnEsfera) {
                double radio = leerValor(txtEsfRadio, "el radio de la esfera");
                mostrarResultados(new Esfera(radio), lblEsfVolumen, lblEsfSuperficie);
            } else if (origen == btnPiramide) {
                double base = leerValor(txtPirBase, "la base de la pirámide");
                double altura = leerValor(txtPirAltura, "la altura de la pirámide");
                double apotema = leerValor(txtPirApotema, "el apotema de la pirámide");
                mostrarResultados(new Piramide(base, altura, apotema), lblPirVolumen, lblPirSuperficie);
            }
        } catch (IllegalArgumentException ex) {
            // Incluye NumberFormatException (valor no numerico) y valores no positivos
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Dato inválido", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lee un numero positivo desde un campo de texto.
     * Lanza IllegalArgumentException con un mensaje claro si el dato no es valido.
     */
    private double leerValor(JTextField campo, String descripcion) {
        String texto = campo.getText().trim().replace(',', '.');
        double valor;
        try {
            valor = Double.parseDouble(texto);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("Ingrese un valor numérico válido para " + descripcion + ".");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Ingrese un valor mayor que cero para " + descripcion + ".");
        }
        return valor;
    }

    /** Polimorfismo: funciona con cualquier subclase de FiguraGeometrica. */
    private void mostrarResultados(FiguraGeometrica figura, JLabel lblVolumen, JLabel lblSuperficie) {
        lblVolumen.setText(String.format("Volumen: %.2f %s", figura.calcularVolumen(), CUBO));
        lblSuperficie.setText(String.format("Superficie: %.2f %s", figura.calcularSuperficie(), CUADRADO));
    }

    public static void main(String[] args) {
        FigurasGeometricas ventana = new FigurasGeometricas();
        ventana.setVisible(true);
    }
}

/**
 * Clase abstracta que representa una figura geometrica tridimensional.
 * Cada figura concreta debe saber calcular su volumen y su superficie.
 */
abstract class FiguraGeometrica {

    /** Retorna el volumen de la figura en cm3. */
    public abstract double calcularVolumen();

    /** Retorna la superficie total de la figura en cm2. */
    public abstract double calcularSuperficie();
}

/**
 * Cilindro definido por su radio y su altura (en centimetros).
 * Volumen    = PI * r^2 * h
 * Superficie = 2 * PI * r * (r + h)
 */
class Cilindro extends FiguraGeometrica {

    private double radio;
    private double altura;

    public Cilindro(double radio, double altura) {
        this.radio = radio;
        this.altura = altura;
    }

    public double getRadio() {
        return radio;
    }

    public double getAltura() {
        return altura;
    }

    @Override
    public double calcularVolumen() {
        return Math.PI * Math.pow(radio, 2) * altura;
    }

    @Override
    public double calcularSuperficie() {
        return 2 * Math.PI * radio * (radio + altura);
    }
}

/**
 * Esfera definida por su radio (en centimetros).
 * Volumen    = 4/3 * PI * r^3
 * Superficie = 4 * PI * r^2
 */
class Esfera extends FiguraGeometrica {

    private double radio;

    public Esfera(double radio) {
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    @Override
    public double calcularVolumen() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radio, 3);
    }

    @Override
    public double calcularSuperficie() {
        return 4 * Math.PI * Math.pow(radio, 2);
    }
}


class Piramide extends FiguraGeometrica {

    private double base;
    private double altura;
    private double apotema;

    public Piramide(double base, double altura, double apotema) {
        this.base = base;
        this.altura = altura;
        this.apotema = apotema;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }

    public double getApotema() {
        return apotema;
    }

    @Override
    public double calcularVolumen() {
        return Math.pow(base, 2) * altura / 3;
    }

    @Override
    public double calcularSuperficie() {
        return Math.pow(base, 2) + 2 * base * apotema;
    }
}
