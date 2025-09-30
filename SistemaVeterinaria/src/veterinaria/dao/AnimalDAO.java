package veterinaria.dao;

import veterinaria.entities.Animal;
import java.util.List;

public interface AnimalDAO {
    Animal add(Animal animal) throws Exception;
    List<Animal> getAll() throws Exception;
    Animal getById(int id) throws Exception;
    boolean update(Animal animal) throws Exception;
    boolean delete(int id) throws Exception;
}
