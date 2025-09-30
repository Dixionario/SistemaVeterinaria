package veterinaria.entities;

public class Gato extends Animal {
    private Boolean viveEnInterior;
    private Integer nroVidas;

    public Gato(String nombre, int edad, Boolean viveEnInterior, Integer nroVidas) {
        super(nombre, edad);
        this.viveEnInterior = viveEnInterior;
        this.nroVidas = nroVidas;
    }

    public Gato(int id, String nombre, int edad, Boolean viveEnInterior, Integer nroVidas) {
        super(id, nombre, edad);
        this.viveEnInterior = viveEnInterior;
        this.nroVidas = nroVidas;
    }

    public Boolean getViveEnInterior() { return viveEnInterior; }
    public void setViveEnInterior(Boolean viveEnInterior) { this.viveEnInterior = viveEnInterior; }

    public Integer getNroVidas() { return nroVidas; }
    public void setNroVidas(Integer nroVidas) { this.nroVidas = nroVidas; }

    @Override
    public void emitirSonido() { System.out.println("Miau!"); }

    @Override
    public String toString() {
        return toStringBasic() + " | Tipo: Gato | ViveEnInterior: " + viveEnInterior + " | NroVidas: " + nroVidas;
    }
}

