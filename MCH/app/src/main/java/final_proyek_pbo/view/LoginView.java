package final_proyek_pbo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Button;

public class LoginView {

    private BorderPane root;
    private Button loginbtn;
    private Hyperlink createAccount;
    private TextField email;
    private PasswordField password;

    public LoginView() {

        root = new BorderPane();
        root.getStyleClass().add("login-root");

        StackPane centerContainer = new StackPane();

        Label greetings = new Label("Welcome Back!");
        greetings.getStyleClass().add("login-title");

        email = new TextField();
        email.setPromptText("Email/Username");
        email.setMaxWidth(300);
        email.getStyleClass().add("login-field");

        password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(300);
        password.getStyleClass().add("login-field");

        Hyperlink forgotPassword = new Hyperlink("forgot password?");
        forgotPassword.getStyleClass().add("login-link");

        loginbtn = new Button("Log in");
        loginbtn.setPrefWidth(300);
        loginbtn.setPrefHeight(45);
        loginbtn.getStyleClass().add("login-button");

        createAccount = new Hyperlink("or create account");
        createAccount.getStyleClass().add("login-link");
           
         VBox loginCard = new VBox(
                greetings,
                email,
                password,
                forgotPassword,
                loginbtn,
                createAccount
        );
        loginCard.getStyleClass().add("login-card");
        loginCard.setAlignment(Pos.CENTER);
        loginCard.setSpacing(15);
        loginCard.setPadding(new Insets(30));

        loginCard.setMaxWidth(420);
        loginCard.setMaxHeight(320);


        centerContainer.getChildren().add(loginCard);

        root.setCenter(centerContainer);
    
    }
        
    public BorderPane getView() {
        return root;
    }
    public Hyperlink getCreateAccountLink() {
    return createAccount;
    }
    public Button getLoginButton() {
    return loginbtn;
    }
    public TextField getEmailField(){
        return email;
    }
    public PasswordField getPasswordField(){
        return password;
    }
}