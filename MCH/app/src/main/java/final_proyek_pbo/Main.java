package final_proyek_pbo;

import final_proyek_pbo.controller.EventController;
import final_proyek_pbo.data.UserData;
import final_proyek_pbo.model.User;
import final_proyek_pbo.view.BookingView;
import final_proyek_pbo.view.CommunityView;
import final_proyek_pbo.view.DetailProyekView;
import final_proyek_pbo.view.EventView;
import final_proyek_pbo.view.HomeView;
import final_proyek_pbo.view.LandingView;
import final_proyek_pbo.view.LoginView;
import final_proyek_pbo.view.RegisterView;
import final_proyek_pbo.view.RoadmapView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private static Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        loadFonts();

        Scene scene = new Scene(new LandingView().getView(), 1000, 780);
        mainScene = scene;

        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        
        showLanding(scene);

        primaryStage.setTitle("PINISI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadFonts() {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter_28pt-Regular.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Inter_24pt-Bold.ttf"), 14);
    }

    public static void navigateTo(String page) {
        Parent nextView = switch (page) {
            case "EVENT" -> new EventView(new EventController());
            case "COMMUNITY" -> new CommunityView();
            case "DETAIL_PROYEK" -> new DetailProyekView();
            case "BOOKING" -> new BookingView();
            case "ROADMAP" -> new RoadmapView();
            case "HOME" -> new HomeView();
            default -> null;
        };

        if (nextView != null) {
            mainScene.setRoot(nextView);
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showLanding(Scene scene) {
        LandingView landingView = new LandingView();
        landingView.getLoginbtn().setOnAction(e -> showLogin(scene));
        landingView.getStartbtn().setOnAction(e -> showRegister(scene));
        scene.setRoot(landingView.getView());
    }

    private void showLogin(Scene scene) {
        LoginView loginView = new LoginView();
        loginView.getCreateAccountLink().setOnAction(e -> showRegister(scene));
        
        loginView.getLoginButton().setOnAction(e -> {
            String email = loginView.getEmailField().getText();
            String password = loginView.getPasswordField().getText();

            if (email.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Email dan Password harus diisi!");
                return;
            }

            UserData.currentUser = UserData.users.stream()
                    .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                    .findFirst()
                    .orElse(null);

            if (UserData.currentUser != null) {
                showHome(scene);
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Gagal", "Email atau password salah!");
            }
        });
        scene.setRoot(loginView.getView());
    }

    private void showRegister(Scene scene) {
        RegisterView registerView = new RegisterView();
        registerView.getLoginLink().setOnAction(e -> showLogin(scene));

        registerView.getRegisterButton().setOnAction(e -> {
            String nama = registerView.getNameField().getText();
            String email = registerView.getEmailField().getText();
            String password = registerView.getPasswordField().getText();
            String confirmPassword = registerView.getConfirmPasswordField().getText();

            if (nama.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Semua field harus diisi!");
                return;
            }

            if (!password.equals(confirmPassword)) {
                showAlert(Alert.AlertType.ERROR, "Error", "Password tidak cocok!");
                return;
            }

            if (UserData.users.stream().anyMatch(u -> u.getEmail().equals(email))) {
                showAlert(Alert.AlertType.ERROR, "Error", "Email sudah digunakan!");
                return;
            }

            UserData.users.add(new User(nama, email, password));
            showAlert(Alert.AlertType.INFORMATION, "Berhasil", "Registrasi berhasil!");
            showLogin(scene);
        });
        
        scene.setRoot(registerView.getView());
    }

    private void showHome(Scene scene) {
        scene.setRoot(new HomeView());
    }

    public static void main(String[] args) {
        launch(args);
    }
}