package veterinaria.service;

import veterinaria.entities.Animal;
import java.util.List;

public interface AnimalService {
    Animal crearAnimal(Animal animal) throws Exception;
    List<Animal> listarAnimales() throws Exception;
    Animal buscarPorId(int id) throws Exception;
    boolean actualizarAnimal(Animal animal) throws Exception;
    boolean eliminarAnimal(int id) throws Exception;
}
