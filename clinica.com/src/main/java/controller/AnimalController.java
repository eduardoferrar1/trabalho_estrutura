package controller;

import model.Animal;
import service.AnimalService;

import java.sql.SQLException;

public class AnimalController {

    private final AnimalService service;

    public AnimalController() {
        this.service = new AnimalService();
    }

    public Animal cadastrarAnimal(Animal animal) {

        try {

            return service.salvar(animal);

        } catch (IllegalArgumentException e) {

            System.out.println("Erro: " + e.getMessage());

        } catch (SQLException e) {

            System.out.println("Erro BD: " + e.getMessage());
        }

        return null;
    }
}