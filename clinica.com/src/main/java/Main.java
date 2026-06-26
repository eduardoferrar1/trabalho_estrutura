import controller.AnimalController;
import controller.ConsultaController;
import controller.TutorController;
import model.Animal;
import model.Consulta;
import model.Tutor;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        TutorController tutorController =
                new TutorController();

        AnimalController animalController =
                new AnimalController();

        ConsultaController consultaController =
                new ConsultaController();

        System.out.println("=== CLINICA VETERINARIA ===");


        Tutor tutor = new Tutor(
                1,
                "Eduardo Ferrari",
                "Rua das nações 33",
                "(44)99937-5053"
        );

        tutor = tutorController.cadastrarTutor(tutor);

        // 2 - Animal

        Animal animal = new Animal(
                1,
                "cristal",
                "gato",
                "vira-lata",
                1L
        );

        animal = animalController.cadastrarAnimal(animal);

        // 3 - Consulta

        Consulta consulta = new Consulta(
                1,
                "1",
                "10/10/2000",
                "Vacinação",
                120
        );

        consultaController.registrarConsulta(consulta);

        // 4 - Histórico

        consultaController.exibirHistorico(animal);

        System.out.println("\nFluxo concluído.");
    }
}