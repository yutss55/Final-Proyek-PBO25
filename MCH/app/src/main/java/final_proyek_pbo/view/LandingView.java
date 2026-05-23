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

    public LandingView() {
        root = new BorderPane();
        Label apk = new Label("PINISI");
        // root.setCenter(apk);
        apk.setFont(Font.font("Inter", FontWeight.BOLD, 36));
                    apk.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
            );

        HBox navbar = new HBox();
        navbar.setSpacing(20);
        navbar.setAlignment(Pos.CENTER_LEFT);

        Button btnhome = new Button("Home");
        btnhome.setStyle("-fx-background-color: transparent;-fx-text-fill: white;");
        Button btncommunity = new Button("Community");
        btncommunity.setStyle("-fx-background-color: transparent;-fx-text-fill: white;");
        Button btnEvent = new Button("Event");
        btnEvent.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
        Button btnLogin = new Button("Login");
        btnLogin.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: white;" +
            "-fx-border-color: white;" +
            "-fx-border-radius: 20;" +
            "-fx-background-radius: 20;" +
            "-fx-padding: 1 10 1 10;"
        );
                

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
        title.setStyle( "-fx-font-size: 40px;" +
                        "-fx-font-weight: bold;"+ 
                        "-fx-text-fill: white");
        Label text = new Label("Platform Pengembangan Talenta Digital\nMasyarakat Kota Makassar");
        text.setStyle("-fx-font-size: 15px;"+ "-fx-text-fill: white");
        Button btnStart = new Button("Get Started");
        btnStart.setStyle(
            "-fx-background-color: #6D4AFF;" +
            "-fx-border-color: white;"+
            "-fx-text-fill: white;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 25;" +
            "-fx-border-radius: 40;"+
            "-fx-padding: 12 25 12 25;"
        );

        leftcontent.getChildren().addAll(title, text, btnStart);
        leftcontent.setSpacing(20);

        Image image = new Image(
                getClass().getResourceAsStream("/images/maskot.png")
        );

        ImageView mascot = new ImageView(image);

        mascot.setFitWidth(350);
        mascot.setPreserveRatio(true);
        
        pict.getChildren().addAll(leftcontent, mascot);
        root.setCenter(pict);
        root.setStyle(
             "-fx-background-color: linear-gradient(to right, #000033, #0600AB);"
        );
    }

    public BorderPane getView() {
        return root;
    }
}