import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SalaDeBaileGUI extends JPanel {
    private List<Bailarin> hombres;
    private List<Bailarin> mujeres;
    private List<Pair> parejas;

    public SalaDeBaileGUI(List<Bailarin> hombre, List<Bailarin> mujeres, List<Pair> parejas) {
        this.hombres = hombre;
        this.mujeres = mujeres;
        this.parejas = parejas;
        setPreferredSize(new Dimension(SalaDeBaile.WIDTH, SalaDeBaile.HEIGHT));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawLines(g);
        drawBailarines(g);

    }

    private void drawLines(Graphics g) {
        g.drawLine(SalaDeBaile.WIDTH / 3, 0, SalaDeBaile.WIDTH / 3, SalaDeBaile.HEIGHT);
        g.drawLine(2 * SalaDeBaile.WIDTH / 3, 0, 2 * SalaDeBaile.WIDTH / 3, SalaDeBaile.HEIGHT);
    }

    private void drawBailarines(Graphics g) {
        // Dibujar hombres en espera
        int xPosHombres = SalaDeBaile.WIDTH / 3 - 50;
        int yPosHombres = 100;

        for (Bailarin hombre : hombres) {
            g.setColor(Color.BLUE);
            g.fillOval(xPosHombres + SalaDeBaile.WIDTH - 700, yPosHombres, 30, 30);
            g.setColor(Color.BLACK);
            g.drawString(hombre.getNombre(), xPosHombres, yPosHombres - 5);
            yPosHombres += 40;
        }

        // Dibujar mujeres en espera
        int xPosMujeres = 2 * SalaDeBaile.WIDTH / 3 + 20;
        int yPosMujeres = 100;

        for (Bailarin mujer : mujeres) {
            g.setColor(Color.PINK);
            g.fillOval(xPosMujeres + SalaDeBaile.WIDTH - 700, yPosMujeres, 30, 30);
            g.setColor(Color.BLACK);
            g.drawString(mujer.getNombre(), xPosMujeres, yPosMujeres - 5);
            yPosMujeres += 40;
        }

        // Dibujar bailarines en la pista
        int xPosPista = SalaDeBaile.WIDTH / 3 + 40;
        int yPosPista = 100;
        int pistaWidth = SalaDeBaile.WIDTH / 3 - 80;
        int pistaHeight = SalaDeBaile.HEIGHT - 200;
        g.setColor(Color.WHITE);
        g.fillOval(xPosPista, yPosPista, pistaWidth, pistaHeight);

        for (Pair pareja : parejas) {
            Bailarin hombre = pareja.getHombre();
            Bailarin mujer = pareja.getMujer();

            if (hombre.isBailando()) {
                g.setColor(Color.BLUE);
                g.fillOval(xPosPista + pistaWidth - 120, yPosPista, 30, 30);
                g.setColor(Color.BLACK);
                g.drawString(hombre.getNombre(), xPosPista, yPosPista + 20);
            }

            if (mujer.isBailando()) {
                g.setColor(Color.PINK);
                g.fillOval(xPosPista + pistaWidth - 50, yPosPista, 30, 30);
                g.setColor(Color.BLACK);
                g.drawString(mujer.getNombre(), xPosPista + pistaWidth, yPosPista + 20);
            }

            yPosPista += 40;
        }
    }

}
