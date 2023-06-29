public class Pair {
    private Bailarin hombre;
    private Bailarin mujer;

    private int tiempoBaile;

    public Pair(Bailarin hombre, Bailarin mujer) {
        this.hombre = hombre;
        this.mujer = mujer;
    }
    public void setTiempoBaile(int tiempoBaile) {
        this.tiempoBaile = tiempoBaile;
    }
    public int getTiempoBaile() {
        return tiempoBaile;
    }


    public Bailarin getHombre() {
        return hombre;
    }

    public Bailarin getMujer() {
        return mujer;
    }
}
