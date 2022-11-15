package com.example.java_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import no.hiof.groupproject.models.RentOutAd;
import no.hiof.groupproject.models.vehicle_types.Vehicle;
import no.hiof.groupproject.tools.db.RetrieveAdvertisementDB;
import no.hiof.groupproject.tools.db.RetrieveAvailableWithinDB;
import no.hiof.groupproject.tools.db.RetrieveVehicleDB;
import no.hiof.groupproject.tools.filters.FilterAdvertisement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

public class ToGoCarPageController  {

    @FXML
    private Button button_registerCar;
    @FXML
    private Button button_FindCar;
    @FXML
    private Button button_Advertisement;
    @FXML
    private Button button_profile;
    @FXML
    private Button button_messages;
    @FXML
    private Button button_customerService;
    @FXML
    private Button button_logOut;
    @FXML
    private TextField tf_TownName;
    @FXML
    private DatePicker start_DatePicker;
    @FXML
    private DatePicker return_DatePicker;
    @FXML
    private Label noAvailableCarWarning;
   @FXML
    //private
   //ListView<Integer> vehicleList = new ListView<>() ;
    //private ListView<Advertisement> vehicleList = new ListView<>() ;
    private ListView<Vehicle> vehicleList = new ListView<>() ;
   /*
   @FXML
    void openNePage() throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("FilterCar.fxml"));
       Parent pane = loader.load();
       FilterCarController f = loader.getController();
       f.populateListView(tf_TownName.getText());
       Stage stage = new Stage();
       stage.setScene(new Scene(pane));
       stage.show();
    */

    //ObservableList<Integer> observableList = FXCollections.observableArrayList();
    //ObservableList<Advertisement> observableList = FXCollections.observableArrayList();
    ObservableList<Vehicle> observableListVehicle = FXCollections.observableArrayList();

    FilterCarController f = new FilterCarController();

    //This returns the ad id based on the town inserted into the textfield
    public Integer getAdId(){
        String town = tf_TownName.getText().trim().toLowerCase();

        for(Integer id : FilterAdvertisement.filterToArrayListAdvertisementId(null, null, null,
                town, null, null, null,
                null,null, null)){
            return id;
        }

        return getAdId();}
     //RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
    /*public void populateListView(){
        RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
        Vehicle v = RetrieveVehicleDB.retrieveFromId(roa.getVehicle().getId());
        f.vehicleObservableList.addAll(v);
        System.out.println(f.vehicleObservableList);
        f.getVehicleListView().getItems().addAll(f.vehicleObservableList);


    }

     */

    public String getTextfield(){
        return tf_TownName.getText();

    }

    //returns the startDate picked using the date picker
    public LocalDate getStartDate(){

        return start_DatePicker.getValue();
    }
    //returns the startDate picked using the date picker
    public LocalDate getReturnDate(){

        return return_DatePicker.getValue();


    }



    //prints the ad id into the listview
    /*
    public void loadAds(){
                observableList.addAll(getAdId());
            System.out.println(observableList);
                vehicleList.getItems().addAll(observableList);

        }

     */



    public String findAnAvailableCar(){
        String status = "success";
        if(getStartDate() == null || getReturnDate() == null){
            setNoAvailableCarWarning("pick dates");
            status = "error";}
        else {
            try {
                RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
                if(!roa.checkIfDateIsAvailable(getStartDate(),getReturnDate())){
                    setNoAvailableCarWarning("pick Other dates");
                    status = "error";}


            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        return status;

    }




    public void FindBookCar(ActionEvent event) throws IOException{
           Main m = new Main();

       if(event.getSource() == button_FindCar){
           if(findAnAvailableCar().equals("success")){

               //populateListView();

           }
        }
    else noAvailableCarWarning.setText("check other dates");}
    public void displayName(String username) {
        noAvailableCarWarning.setText("Hello: " + username);
    }

   @FXML
    private void dateSelection(){
        if(start_DatePicker.getValue() == null || return_DatePicker.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(start_DatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            alert.show();
            return;
        }
        if(start_DatePicker.getValue().isBefore(LocalDate.now()) || return_DatePicker.getValue().isBefore(LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select an actual date!");
            alert.show();
            return;
        }
        if(return_DatePicker.getValue().isBefore(start_DatePicker.getValue())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("End date is before start date!");
            alert.show();
            return;
        }

        LocalDate fromDate = start_DatePicker.getValue();

        LocalDate toDate = return_DatePicker.getValue();


       TreeMap<LocalDate, LocalDate> availableDates = new TreeMap<>();
       availableDates.put(fromDate,toDate);
       RentOutAd roa = (RentOutAd) RetrieveAdvertisementDB.retrieveFromId(getAdId());
       TreeMap<LocalDate, LocalDate> s = RetrieveAvailableWithinDB.retrieve(roa);
       roa.checkIfDateIsAvailable(fromDate,toDate);

       if(roa.availableWithinExistsInDb(fromDate,toDate)){

           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText("not available");
           alert.show();

       }
    }


    private int ConvertIntoNumeric(String xVal)
    {
        try
        {
            return Integer.parseInt(xVal);
        }
        catch(Exception ex)
        {
            return 0;
        }
    }


        public void setNoAvailableCarWarning(String text){
            noAvailableCarWarning.setText(text);
        }

        public void userRegisterCar(ActionEvent event) throws IOException {
            Main m = new Main();
            m.changeScene("RegisterACar.fxml");
        }
        public void carAdvertisement(ActionEvent event)throws IOException{
            Main m = new Main();
            m.changeScene("Advertisement.fxml");
        }


        public void userProfile(ActionEvent event) throws IOException{
            Main m = new Main();
            m.changeScene("Profile.fxml");
        }

        public void message_menu(ActionEvent event) throws IOException{
            Main m = new Main();
            m.changeScene("LogIn.fxml");
        }
        public void customerService(ActionEvent event) throws IOException{
            Main m = new Main();
            m.changeScene("LogIn.fxml");
        }
        public void userLogOut(ActionEvent event) throws IOException{
            Main m = new Main();
            m.changeScene("LogIn.fxml");
        }

        public void AdvertisementScene(ActionEvent event) throws IOException{
            Main m = new Main();
            m.changeScene("Advertisement.fxml");
    }







}