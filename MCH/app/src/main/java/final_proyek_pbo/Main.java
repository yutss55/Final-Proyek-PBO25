package final_proyek_pbo;

import final_proyek_pbo.view.LoginView;
import final_proyek_pbo.view.RegisterView;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(
               new BorderPane(),
                1000,
                700
        );

        scene.getStylesheets().add(
                getClass()
                        .getResource("/css/style.css")
                        .toExternalForm()
        );

        showLogin(scene);

        primaryStage.setTitle("PINISI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showLogin (Scene scene){
        LoginView loginView = new LoginView();
        loginView.getCreateAccountLink().setOnAction(e->{showRegister(scene);});

        scene.setRoot(loginView.getView());

    }
    private void showRegister (Scene scene){
        RegisterView registerView = new RegisterView();
        
        registerView.getLoginLink().setOnAction(e->{showLogin(scene);});

        scene.setRoot(registerView.getView());

    }

    public static void main(String[] args) {
        launch(args);
    }
}