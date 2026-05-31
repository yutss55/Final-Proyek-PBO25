package final_proyek_pbo;
import final_proyek_pbo.view.LandingView;
import final_proyek_pbo.view.LoginView;
import final_proyek_pbo.view.RegisterView;
import final_proyek_pbo.view.EventView;
import final_proyek_pbo.view.HomeView;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import final_proyek_pbo.model.User;
import final_proyek_pbo.data.UserData;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Font.loadFont(getClass().getResource("/fonts/Inter_28pt-Regular.ttf").toExternalForm(), 14);
        Font.loadFont(getClass().getResource("/fonts/Inter_24pt-Bold.ttf").toExternalForm(), 14);
        System.out.println(
            Font.loadFont(
                getClass().getResourceAsStream("/fonts/Inter_28pt-Regular.ttf"),
                14
            )
        );

        Scene scene = new Scene(
               new EventView(),
                1000,
                780
        );

        scene.getStylesheets().add(
                getClass()
                        .getResource("/css/style.css")
                        .toExternalForm()
        );

        // showLanding(scene);

        primaryStage.setTitle("PINISI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showLanding (Scene scene){
        LandingView landingView = new LandingView();
        landingView.getLoginbtn().setOnAction(e->{showLogin(scene);});
        scene.setRoot(landingView.getView());
        landingView.getStartbtn().setOnAction(e->{showRegister(scene);});
    }
    private void showLogin (Scene scene){
        LoginView loginView = new LoginView();
        loginView.getCreateAccountLink().setOnAction(e->{showRegister(scene);});
        
        loginView.getLoginButton().setOnAction(e ->{
            String email = loginView.getEmailField().getText();
            String password = loginView.getPasswordField().getText();

            boolean loginBerhasil = false;

            if (email.isEmpty() || password.isEmpty()){
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Email dan Password harus diisi!");
                alert.showAndWait();
                return;
            }


            for(User user : UserData.users){
                if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                    UserData.currentUser = user;
                    loginBerhasil = true;
                      break;
                };
            }
            if(loginBerhasil){
                showHome(scene);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login gagal");
                alert.setHeaderText(null);
                alert.setContentText("Email atau password salah!");
                alert.showAndWait();
                }
        });
        scene.setRoot(loginView.getView());
    }
    private void showRegister(Scene scene) {

        RegisterView registerView = new RegisterView();

        registerView.getLoginLink().setOnAction(e -> {
        showLogin(scene);});

        registerView.getRegisterButton().setOnAction(e -> {

            String nama = registerView.getNameField().getText();
            String email = registerView.getEmailField().getText();
            String password = registerView.getPasswordField().getText();
            String confirmPassword =
                    registerView.getConfirmPasswordField().getText();

            if (nama.isEmpty()
                    || email.isEmpty()
                    || password.isEmpty()
                    || confirmPassword.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Semua field harus diisi!");
                alert.showAndWait();

                return;
            }

            if (!password.equals(confirmPassword)) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Password tidak cocok!");
                alert.showAndWait();

                return;
            }

            for (User user : UserData.users){
                if(user.getEmail().equals(email)){
                    Alert alert = new Alert (Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Email sudah digunakan!");
                    alert.showAndWait();

                    return;
                }
            }

            User user = new User(
                    nama,
                    email,
                    password
            );

            UserData.users.add(user);
            System.out.println("Jumlah user: " + UserData.users.size());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Berhasil");
            alert.setHeaderText(null);
            alert.setContentText("Registrasi berhasil!");
            alert.showAndWait();
            showLogin(scene);

        });
        
        scene.setRoot(registerView.getView());
    }
    private void showHome(Scene scene){
        HomeView homeView = new HomeView();
        scene.setRoot(homeView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}