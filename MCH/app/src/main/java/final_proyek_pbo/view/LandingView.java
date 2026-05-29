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

    javafx.scene.shape.Circle dot = new javafx.scene.shape.Circle(7);
    dot.setFill(javafx.scene.paint.Color.web("#7C5CBF"));

    Label apk = new Label("PINISI");
    apk.setFont(Font.font("Inter", FontWeight.BOLD, 20));
    apk.getStyleClass().add("logo");

    HBox logoBox = new HBox(10, dot, apk);
    logoBox.setAlignment(Pos.CENTER_LEFT);

    Button btnhome = new Button("Home");
    btnhome.getStyleClass().add("nav-btn");
    Button btnProgram = new Button("Program");
    btnProgram.getStyleClass().add("nav-btn");
    Button btncommunity = new Button("Community");
    btncommunity.getStyleClass().add("nav-btn");
    Button btnEvent = new Button("Event");
    btnEvent.getStyleClass().add("nav-btn");
    btnLogin = new Button("Login");
    btnLogin.getStyleClass().add("nav-btn-active");

    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    HBox navbar = new HBox(16);
    navbar.setAlignment(Pos.CENTER_LEFT);
    navbar.setPadding(new Insets(10, 24, 10, 20));
    navbar.getStyleClass().add("navbar");
    navbar.setMaxWidth(Double.MAX_VALUE);
    navbar.getChildren().addAll(logoBox, spacer, btnhome, btnProgram, btncommunity, btnEvent, btnLogin);

    HBox navbarContainer = new HBox(navbar);
    navbarContainer.setPadding(new Insets(20, 24, 0, 24));
    HBox.setHgrow(navbar, Priority.ALWAYS);

    root.setTop(navbarContainer);

    VBox leftcontent = new VBox(18);
    leftcontent.setAlignment(Pos.CENTER_LEFT);
    leftcontent.setPadding(new Insets(0, 0, 0, 80));

    Label title = new Label("Pintar Ningkatin\nSkill IT");
    title.getStyleClass().add("hero-title");

    Label text = new Label("Platform Pengembangan Talenta Digital\nMasyarakat Kota Makassar.");
    text.getStyleClass().add("hero-description");

    btnStart = new Button("Get Started");
    btnStart.getStyleClass().add("start-button");

    leftcontent.getChildren().addAll(title, text, btnStart);
    HBox.setHgrow(leftcontent, Priority.ALWAYS);

    Image image = new Image(
        getClass().getResource("/images/maskot.png").toExternalForm()
    );
    ImageView mascot = new ImageView(image);
    mascot.setFitWidth(430);
    mascot.setPreserveRatio(true);

    HBox mascotBox = new HBox(mascot);
    mascotBox.setAlignment(Pos.BOTTOM_RIGHT);
    mascotBox.setPadding(new Insets(0, 40, 0, 0));

    HBox pict = new HBox();
    pict.setAlignment(Pos.CENTER);
    pict.setPadding(new Insets(40, 0, 0, 0));
    pict.getChildren().addAll(leftcontent, mascotBox);

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