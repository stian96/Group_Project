package com.example.java_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.models.vehicles.four_wheeled_vehicles.Car;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DetailedAdViewController implements Initializable {
    private RentOutAd selectedAd;
    private Car vehicle;

    @FXML
    private Label selectedOwner, selectedMan, selectedModel, selectedModelYear, selectedGearType,
            selectedFuelType,
            selectedSeatingCapacity,
            selectedTowingCapacity;


    @FXML
    private Button button_back;

    @FXML private AnchorPane scenePane;
    Stage stage;



    public void fillData(RentOutAd roa) {
        selectedAd = roa;
        vehicle = (Car) roa.getVehicle();
        selectedOwner.setText(roa.getUser().getFirstName());
        selectedMan.setText(roa.getVehicle().getManufacturer());
        selectedModel.setText(roa.getVehicle().getModel());
        selectedModelYear.setText(String.valueOf(roa.getVehicle().getModelYear()));
        selectedGearType.setText(roa.getVehicle().getGearType());
        selectedFuelType.setText(roa.getVehicle().getEngineType());
        selectedSeatingCapacity.setText(String.valueOf(vehicle.getSeatingCapacity()));
        selectedTowingCapacity.setText(String.valueOf(vehicle.getTowingCapacity()));
    }

    @FXML
    public void changeSceneToFilterCar(ActionEvent event) {

        stage = (Stage) scenePane.getScene().getWindow();
        stage.close();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
           button_back.setOnAction(this::changeSceneToFilterCar);
    }
    public void buttonStyle() {
        button_back.setOnMouseEntered(e -> button_back.setStyle("-fx-background-color: #c9b502;"));
        button_back.setOnMouseExited(e -> button_back.setStyle("-fx-background-color:  #f1c232;"));}
}