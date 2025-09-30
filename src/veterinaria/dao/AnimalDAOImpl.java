package veterinaria.dao;

import veterinaria.entities.Animal;
import veterinaria.entities.Perro;
import veterinaria.entities.Gato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAOImpl implements AnimalDAO {

    private final String url;
    private final String user;
    private final String password;

    public AnimalDAOImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public Animal add(Animal animal) throws Exception {
        String sql = "INSERT INTO animal (nombre, edad, nivel_entrenamiento, `tamaño`, vive_en_interior, nro_vidas) VALUES (?,?,?,?,?,?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, animal.getNombre());
            ps.setInt(2, animal.getEdad());

            if (animal instanceof Perro) {
                Perro p = (Perro) animal;
                if (p.getNivelEntrenamiento() != null) ps.setInt(3, p.getNivelEntrenamiento()); else ps.setNull(3, Types.INTEGER);
                if (p.getTamano() != null) ps.setString(4, p.getTamano()); else ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.BOOLEAN);
                ps.setNull(6, Types.INTEGER);
            } else if (animal instanceof Gato) {
                Gato g = (Gato) animal;
                ps.setNull(3, Types.INTEGER);
                ps.setNull(4, Types.VARCHAR);
                if (g.getViveEnInterior() != null) ps.setBoolean(5, g.getViveEnInterior()); else ps.setNull(5, Types.BOOLEAN);
                if (g.getNroVidas() != null) ps.setInt(6, g.getNroVidas()); else ps.setNull(6, Types.INTEGER);
            } else {
                // Por si agregan otro tipo en el futuro
                ps.setNull(3, Types.INTEGER);
                ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.BOOLEAN);
                ps.setNull(6, Types.INTEGER);
            }

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    animal.setId(id);
                }
            }
            return animal;
        }
    }

    @Override
    public List<Animal> getAll() throws Exception {
        String sql = "SELECT * FROM animal";
        List<Animal> lista = new ArrayList<>();
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Animal a = rsToAnimal(rs);
                if (a != null) lista.add(a);
            }
        }
        return lista;
    }

    @Override
    public Animal getById(int id) throws Exception {
        String sql = "SELECT * FROM animal WHERE id = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rsToAnimal(rs);
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(Animal animal) throws Exception {
        String sql = "UPDATE animal SET nombre=?, edad=?, nivel_entrenamiento=?, `tamaño`=?, vive_en_interior=?, nro_vidas=? WHERE id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, animal.getNombre());
            ps.setInt(2, animal.getEdad());

            if (animal instanceof Perro) {
                Perro p = (Perro) animal;
                if (p.getNivelEntrenamiento() != null) ps.setInt(3, p.getNivelEntrenamiento()); else ps.setNull(3, Types.INTEGER);
                if (p.getTamano() != null) ps.setString(4, p.getTamano()); else ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.BOOLEAN);
                ps.setNull(6, Types.INTEGER);
            } else if (animal instanceof Gato) {
                Gato g = (Gato) animal;
                ps.setNull(3, Types.INTEGER);
                ps.setNull(4, Types.VARCHAR);
                if (g.getViveEnInterior() != null) ps.setBoolean(5, g.getViveEnInterior()); else ps.setNull(5, Types.BOOLEAN);
                if (g.getNroVidas() != null) ps.setInt(6, g.getNroVidas()); else ps.setNull(6, Types.INTEGER);
            } else {
                ps.setNull(3, Types.INTEGER);
                ps.setNull(4, Types.VARCHAR);
                ps.setNull(5, Types.BOOLEAN);
                ps.setNull(6, Types.INTEGER);
            }

            ps.setInt(7, animal.getId());
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM animal WHERE id = ?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            int r = ps.executeUpdate();
            return r > 0;
        }
    }

    private Animal rsToAnimal(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        int edad = rs.getInt("edad");

        Integer nivelEntrenamiento = (rs.getObject("nivel_entrenamiento") != null) ? rs.getInt("nivel_entrenamiento") : null;
        String tamano = rs.getString("tamaño");

        Boolean viveEnInterior = (rs.getObject("vive_en_interior") != null) ? rs.getBoolean("vive_en_interior") : null;
        Integer nroVidas = (rs.getObject("nro_vidas") != null) ? rs.getInt("nro_vidas") : null;

        if (nivelEntrenamiento != null || tamano != null) {
            return new Perro(id, nombre, edad, nivelEntrenamiento, tamano);
        } else if (viveEnInterior != null || nroVidas != null) {
            return new Gato(id, nombre, edad, viveEnInterior, nroVidas);
        } else {
            return null;
        }
    }
}
