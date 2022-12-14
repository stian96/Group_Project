package no.hiof.groupproject.gui.Controller;

import no.hiof.groupproject.gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.tools.db.RetrieveUserDB;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    public static User user;
    @FXML
    protected Label wrongLogin;
    @FXML
    protected TextField tf_userName;
    @FXML
    protected PasswordField tf_password;
    @FXML
    protected Button button_logIn;
    @FXML
    protected Button button_signUp;
    @FXML
    private CheckBox logInCheckBox;

    //This method is when the user clicks on the login. Checks if user in DB if not creates new user and
    //saves to DB
    public void userLogIn(ActionEvent event) throws IOException {
        Main m = new Main();
        User u = RetrieveUserDB.retrieveFromEmail(tf_userName.getText());
        String password = tf_password.getText();
        String email = tf_userName.getText();

        if(!email.isEmpty() && !password.isEmpty()){
            try {
                if(!u.existsInDb()){
                    SignUpCheck();
                }
                else if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                    user = u;
                    m.changeScene("ToGoCar.fxml");

                }
                else {wrongLogin.setText("The email or password is incorrect");}
            }catch (IOException e){
                    System.out.println(e.getMessage());}}

        else {wrongLogin.setText("Please enter an email and password");}}



    public void userSignUp(ActionEvent event) throws IOException {
        SignUpCheck();
    }


    private void SignUpCheck() throws IOException {
        Main m = new Main();

        m.changeScene("SignUp.fxml");

    }
    public void populateTxtFieldsAfterCheckBoxCheck(ActionEvent event){
        if(logInCheckBox.isSelected()){
            tf_userName.setText("stian@stian.no");
            tf_password.setText("password");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInCheckBox.setOnAction(this::populateTxtFieldsAfterCheckBoxCheck);

    }
}
