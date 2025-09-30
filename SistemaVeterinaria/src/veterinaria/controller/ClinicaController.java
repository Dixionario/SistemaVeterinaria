package veterinaria.controller;

import veterinaria.entities.Animal;
import veterinaria.service.AnimalService;

import java.util.List;

public class ClinicaController {

    private final AnimalService service;

    public ClinicaController(AnimalService service) {
        this.service = service;
    }

    public Animal crear(Animal a) throws Exception {
        return service.crearAnimal(a);
    }

    public List<Animal> listar() throws Exception {
        return service.listarAnimales();
    }

    public Animal buscar(int id) throws Exception {
        return service.buscarPorId(id);
    }

    public boolean actualizar(Animal a) throws Exception {
        return service.actualizarAnimal(a);
    }

    public boolean eliminar(int id) throws Exception {
        return service.eliminarAnimal(id);
    }
}
