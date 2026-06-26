package controller;

import model.Tutor;
import service.TutorService;

import java.sql.SQLException;

public class TutorController {

    private final TutorService service;

    public TutorController() {
        this.service = new TutorService();
    }

    public Tutor cadastrarTutor(Tutor tutor) {

        try {
            return service.salvar(tutor);

        } catch (IllegalArgumentException e) {

            System.out.println("Erro: " + e.getMessage());

        } catch (SQLException e) {

            System.out.println("Erro BD: " + e.getMessage());
        }

        return null;
    }
}