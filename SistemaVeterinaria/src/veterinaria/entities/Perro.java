package veterinaria.entities;

public class Perro extends Animal {
    private Integer nivelEntrenamiento;
    private String tamano;

    public Perro(String nombre, int edad, Integer nivelEntrenamiento, String tamano) {
        super(nombre, edad);
        this.nivelEntrenamiento = nivelEntrenamiento;
        this.tamano = tamano;
    }

    public Perro(int id, String nombre, int edad, Integer nivelEntrenamiento, String tamano) {
        super(id, nombre, edad);
        this.nivelEntrenamiento = nivelEntrenamiento;
        this.tamano = tamano;
    }

    public Integer getNivelEntrenamiento() { return nivelEntrenamiento; }
    public void setNivelEntrenamiento(Integer nivelEntrenamiento) { this.nivelEntrenamiento = nivelEntrenamiento; }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }

    @Override
    public void emitirSonido() { System.out.println("Guau!"); }

    @Override
    public String toString() {
        return toStringBasic() + " | Tipo: Perro | NivelEntrenamiento: " + nivelEntrenamiento + " | Tamaño: " + tamano;
    }
}

