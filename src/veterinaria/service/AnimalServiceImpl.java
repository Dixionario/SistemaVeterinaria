package veterinaria.service;

import veterinaria.dao.AnimalDAO;
import veterinaria.entities.Animal;

import java.util.List;

public class AnimalServiceImpl implements AnimalService {

    private final AnimalDAO dao;

    public AnimalServiceImpl(AnimalDAO dao) {
        this.dao = dao;
    }

    @Override
    public Animal crearAnimal(Animal animal) throws Exception {
        return dao.add(animal);
    }

    @Override
    public List<Animal> listarAnimales() throws Exception {
        return dao.getAll();
    }

    @Override
    public Animal buscarPorId(int id) throws Exception {
        return dao.getById(id);
    }

    @Override
    public boolean actualizarAnimal(Animal animal) throws Exception {
        return dao.update(animal);
    }

    @Override
    public boolean eliminarAnimal(int id) throws Exception {
        return dao.delete(id);
    }
}
