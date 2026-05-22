package final_proyek_pbo.view;

import java.beans.VetoableChangeListenerProxy;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class LandingView {
    private BorderPane root;

    public LandingView() {
        root = new BorderPane();
        Label apk = new Label("PINISI");
        // root.setCenter(apk);
        apk.setFont(Font.font("Inter", FontWeight.BOLD, 30));

        HBox navbar = new HBox();
        navbar.setSpacing(20);
        navbar.setAlignment(Pos.CENTER_LEFT);

        Button btnhome = new Button("Home");
        Button btncommunity = new Button("Community");
        Button btnEvent = new Button("Event");
        Button btnLogin = new Button("Login");
        

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
                        "-fx-font-weight: bold;");
        Label text = new Label("Platform Pengembangan Talenta Digital\nMasyarakat Kota Makassar");
        text.setStyle("-fx-font-size: 15px;");
        Button btnStart = new Button("Get Started");
        btnStart.setStyle(
            "-fx-background-radius: 20;" +
            "-fx-padding: 10 20 10 20;"
        );

        leftcontent.getChildren().addAll(title, text, btnStart);
        leftcontent.setSpacing(20);

        Label mascot = new Label("mascot");
        
        pict.getChildren().addAll(leftcontent, mascot);
        root.setCenter(pict);
    }

    public BorderPane getView() {
        return root;
    }
}