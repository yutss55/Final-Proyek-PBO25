package final_proyek_pbo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class RegisterView {

    private BorderPane root;
    private Hyperlink loginLink;
    private Button registbtn;

    public RegisterView() {

        root = new BorderPane();
        root.getStyleClass().add("login-root");

        StackPane centerContainer = new StackPane();

        Label greetings = new Label("Let's Get Started!");
        greetings.getStyleClass().add("login-title");

        TextField name = new TextField();
        name.setPromptText("Full Name");
        name.setMaxWidth(300);
        name.getStyleClass().add("login-field");

        TextField email = new TextField();
        email.setPromptText("Email");
        email.setMaxWidth(300);
        email.getStyleClass().add("login-field");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(300);
        password.getStyleClass().add("login-field");

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");
        confirmPassword.setMaxWidth(300);
        confirmPassword.getStyleClass().add("login-field");

        registbtn = new Button("Register");
        registbtn.setPrefWidth(300);
        registbtn.setPrefHeight(45);
        registbtn.getStyleClass().add("login-button");

        loginLink = new Hyperlink("Already have an account? Login");
        loginLink.getStyleClass().add("login-link");
           
         VBox registCard = new VBox(
                greetings,
                name,
                email,
                password,
                confirmPassword,
                registbtn,
                loginLink
        );
        registCard.getStyleClass().add("login-card");
        registCard.setAlignment(Pos.CENTER);
        registCard.setSpacing(15);
        registCard.setPadding(new Insets(30));

        registCard.setMaxWidth(420);
        registCard.setMaxHeight(420);


        centerContainer.getChildren().add(registCard);
        root.setCenter(centerContainer);
        
    
    }
        
    public BorderPane getView() {
        return root;
    }

    public Hyperlink getLoginLink() {
        return loginLink;
    }
    public Button getRegisterButton() {
        return registbtn;
    }
}