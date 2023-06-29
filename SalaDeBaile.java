import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SalaDeBaile extends JFrame {
    private List<Bailarin> hombres;
    private List<Bailarin> mujeres;
    private List<Pair> parejas;

    private boolean baileActivo;
    private boolean baileTerminado;


    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;

    // Componentes de la interfaz gráfica
    private JButton btnIniciar;
    private JPanel panel;

    // Constructor
    public SalaDeBaile() {
        hombres = new ArrayList<>();
        mujeres = new ArrayList<>();
        parejas = new ArrayList<>();
        System.out.println(hombres);


        // Crear bailarines predeterminados
        crearBailarines();

        // Configuración de la interfaz gráfica
        panel = new SalaDeBaileGUI(hombres,mujeres,parejas);
        btnIniciar = new JButton("Iniciar");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread hilo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        iniciarBaile();
                    }
                });
                hilo.start();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        panel.setLayout(new BorderLayout());
        panel.add(btnIniciar, BorderLayout.NORTH);
        getContentPane().add(panel);
        setVisible(true);
    }

    // Método para crear bailarines predeterminados
    private void crearBailarines() {
        // Crear hombres
        hombres.add(new Bailarin("Bruno"));
        hombres.add(new Bailarin("Sebastian"));
        hombres.add(new Bailarin("Fernando"));
        hombres.add(new Bailarin("Fabricio"));
        hombres.add(new Bailarin("Favio"));
        hombres.add(new Bailarin("Paul"));

        // Crear mujeres
        mujeres.add(new Bailarin("Helen"));
        mujeres.add(new Bailarin("Camila"));
        mujeres.add(new Bailarin("Mariana"));
        mujeres.add(new Bailarin("Maria"));
        mujeres.add(new Bailarin("Carla"));
        mujeres.add(new Bailarin("Alison"));
        System.out.println("Se crean bailarines");
    }

    // Método para iniciar el baile
    private void iniciarBaile() {
        // Realizar el emparejamiento de forma aleatoria
        System.out.println("Inicia baile");
        parejas.clear();
        if (hombres.size() > 0 && mujeres.size() > 0){
            //parejas.clear();

        while (!hombres.isEmpty() && !mujeres.isEmpty()) {
            Random rand = new Random();

            Bailarin hombre = hombres.remove(rand.nextInt(hombres.size()));
            Bailarin mujer = mujeres.remove(rand.nextInt(mujeres.size()));

            parejas.add(new Pair(hombre, mujer));
        }
        baileActivo = true;
        while (baileActivo) {
            for (Pair pareja : parejas) {
                bailar(pareja);

            }

            // Verificar si todas las parejas han terminado de bailar
            boolean todasBailando = true;


            if (todasBailando) {
                System.out.println("Entro al for 3");
                reiniciarBaile();
            }
         }

        }

        if (!hombres.isEmpty()) {
            for (Bailarin hombre : hombres) {
                hombre.setBailando(false);
            }
        }

        if (!mujeres.isEmpty()) {
            for (Bailarin mujer : mujeres) {
                mujer.setBailando(false);
            }
        }

        // Iniciar el baile
        for (Pair pareja : parejas) {
            bailar(pareja);

            System.out.println("Empezaron a bailar"+hombres + mujeres);
        }
    }

    private void reiniciarBaile() {

        for (Pair pareja : parejas) {
            pareja.getHombre().setBailando(false);
            pareja.getMujer().setBailando(false);
            pareja.getHombre().setTiempoBaile(10);
            pareja.getMujer().setTiempoBaile(10);
        }

        for (Bailarin hombre : hombres) {
            hombre.setBailando(false);
            hombre.setTiempoBaile(10);

        }

        for (Bailarin mujer : mujeres) {
            mujer.setBailando(false);
            mujer.setTiempoBaile(10);

        }
        // Limpiar la pista de baile
        hombres.addAll(parejas.stream().map(Pair::getHombre).collect(Collectors.toList()));
        mujeres.addAll(parejas.stream().map(Pair::getMujer).collect(Collectors.toList()));
        parejas.clear();

        baileTerminado = false;

        // Actualizar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            panel.repaint();
        });
    }

    // Método para simular el baile de una pareja
    private void bailar(Pair pareja) {
        Bailarin hombre = pareja.getHombre();
        Bailarin mujer = pareja.getMujer();



        // Simular el baile durante el tiempo máximo permitido
        for (int tiempo = hombre.getTiempoBaile(); tiempo >= 0; tiempo--) {
            // Actualizar los tiempos de baile de los bailarines
            hombre.setTiempoBaile(tiempo);
            mujer.setTiempoBaile(tiempo);

            hombre.setBailando(true);
            mujer.setBailando(true);


            //cambiar el estado del bailarin cuando su tiempo llegue a 0

            // Esperar un segundo (1000 ms) para simular el tiempo real del baile
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            // Actualizar la interfaz gráfica con el estado actual del baile
            // (puedes implementar esto según tus necesidades)
            actualizarInterfazGrafica();
        }

    }

    // Método para actualizar la interfaz gráfica con el estado actual del baile
    private void actualizarInterfazGrafica() {
        // Implementa la lógica para actualizar la interfaz gráfica con la información de los bailarines y las parejas
        panel.repaint();
        boolean todasBailando = true;
        for (Pair pareja : parejas) {
            if (!pareja.getHombre().isBailando() || !pareja.getMujer().isBailando()) {
                todasBailando = false;
                break;
            }
        }

        // Reiniciar el baile si todas las parejas están bailando
        if (todasBailando) {
            reiniciarBaile();
        }


    }



    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        new SalaDeBaile();
    }
}


