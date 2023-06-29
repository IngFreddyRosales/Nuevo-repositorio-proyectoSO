import java.util.Random;

public class Bailarin {
    private String nombre;
    private int tiempoBaile;
    private boolean bailando;
    public Random rn = new Random();

    // Constructor
    public Bailarin(String nombre) {
        this.nombre = nombre;
        this.tiempoBaile = rn.nextInt(3); // Tiempo máximo de baile inicial
        System.out.println(this.tiempoBaile);
        this.bailando = false;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public int getTiempoBaile() {
        return tiempoBaile;
    }

    public void setTiempoBaile(int tiempoBaile) {
        this.tiempoBaile = tiempoBaile;
    }

    public boolean isBailando() {
        return bailando;
    }
    public void setBailando(boolean isBailando ){
        this.bailando = isBailando;
    }
}

