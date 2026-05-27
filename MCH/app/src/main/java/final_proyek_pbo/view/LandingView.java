package final_proyek_pbo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LandingView {
    private BorderPane root;
    private Button btnLogin;
    private Button btnStart;

    public LandingView() {
        root = new BorderPane();
        Label apk = new Label("PINISI");
        apk.setFont(Font.font("Inter", FontWeight.BOLD, 36));
        apk.getStyleClass().add("logo");

        HBox navbar = new HBox();
        navbar.setSpacing(20);
        navbar.setAlignment(Pos.CENTER_LEFT);

        Button btnhome = new Button("Home");
        btnhome.getStyleClass().add("nav-button");
        Button btncommunity = new Button("Community");
        btncommunity.getStyleClass().add("nav-button");
        Button btnEvent = new Button("Event");
        btnEvent.getStyleClass().add("nav-button");
        btnLogin = new Button("Login");
        btnLogin.getStyleClass().add("login-button");
                

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        navbar.getChildren().addAll(apk, spacer, btnhome, btncommunity, btnEvent, btnLogin);
        root.setTop(navbar);
        navbar.setPadding(
            new Insets(25, 60, 25, 60)
                );

        HBox pict = new HBox();
        pict.setSpacing(250);
        pict.setAlignment(Pos.CENTER_LEFT);
        pict.setPadding(
            new Insets(90, 100, 90, 100)
        );

        VBox leftcontent = new VBox();
        leftcontent.setSpacing(25);
        leftcontent.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Pintar Ningkatin\nSkill IT");
        title.getStyleClass().add("hero-title");
        Label text = new Label("Platform Pengembangan Talenta Digital\nMasyarakat Kota Makassar");
        text.getStyleClass().add("hero-description");
        btnStart = new Button("Get Started");
        btnStart.getStyleClass().add("start-button");

        leftcontent.getChildren().addAll(title, text, btnStart);
        leftcontent.setSpacing(20);

       Image image = new Image(
            getClass().getResource("/images/maskot.png").toExternalForm()
        );

        ImageView mascot = new ImageView(image);


        mascot.setFitWidth(350);
        mascot.setPreserveRatio(true);
        
        pict.getChildren().addAll(leftcontent, mascot);
        root.setCenter(pict);
        root.getStyleClass().add("landing-root");
    }

    public BorderPane getView() {
        return root;
    }
    public Button getLoginbtn (){
        return btnLogin;
    }
    public Button getStartbtn(){
        return btnStart;
    }
}