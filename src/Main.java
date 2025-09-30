import veterinaria.dao.AnimalDAOImpl;
import veterinaria.service.AnimalServiceImpl;
import veterinaria.controller.ClinicaController;
import veterinaria.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/VeterinariaDB?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String pass = "root";

        AnimalDAOImpl dao = new AnimalDAOImpl(url, user, pass);
        AnimalServiceImpl service = new AnimalServiceImpl(dao);
        ClinicaController controller = new ClinicaController(service);
        ConsoleUI ui = new ConsoleUI(controller);

        ui.start();
    }
}

