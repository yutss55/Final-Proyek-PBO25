package final_proyek_pbo.view;

import final_proyek_pbo.Main;
import final_proyek_pbo.data.UserData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomeView extends VBox {

    public HomeView() {
        if (UserData.currentUser == null) {
        Main.navigateTo("LOGIN"); 
        return;
    }
        this.getStyleClass().add("main-bg");
        this.setSpacing(20);
        this.setFillWidth(true);

        this.getChildren().addAll(buildNavbar(), buildMainDashboardArea(), buildMenuCards());
    }

    private Button creatNavBtn(String text){
        Button button = new Button(text);
        button.getStyleClass().add("nav-btn");
        return button;
    }

    private ContextMenu creatprofilMenu(Button btnCircle){
        ContextMenu profilMenu =new ContextMenu();
        MenuItem profilItem = new MenuItem("Profile");
        MenuItem logItem = new MenuItem("Logout");

        profilItem.setOnAction(e -> {
            Main.navigateTo("PROFILE");
        });
        

        logItem.setOnAction(e->{
            LandingView landing = new LandingView();
            btnCircle.getScene().setRoot(
                landing.getView()
            );
        });
        profilMenu.getItems().addAll(profilItem,new SeparatorMenuItem(), logItem);

        return profilMenu;
    }
    private HBox buildNavbar() {
        Button btnHome = new Button("Home");
        btnHome.getStyleClass().add("nav-btn-active");
        Button btnProgram = creatNavBtn("Program");
        btnProgram.setOnAction(e-> Main.navigateTo("EVENT"));
        Button btnBooking = creatNavBtn("Facilities");
        btnBooking.setOnAction(e-> Main.navigateTo("BOOKING"));
        Button btnCommunity = creatNavBtn("Community");
        btnCommunity.setOnAction(e-> Main.navigateTo("COMMUNITY"));
        Button btnRoadMap = creatNavBtn("Road Map");
        btnRoadMap.setOnAction(e-> Main.navigateTo("ROADMAP"));

        Button btnCircle = new Button("👤");
        btnCircle.getStyleClass().add("nav-circle");

        ContextMenu profilMenu = creatprofilMenu(btnCircle);
        btnCircle.setOnAction(e->
            profilMenu.show(btnCircle, Side.BOTTOM, 0, 0));
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox navbar = new HBox(8, btnHome, btnProgram, btnCommunity, btnBooking, btnRoadMap, spacer, btnCircle);
        navbar.getStyleClass().add("navbar-container");
        navbar.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(navbar, new Insets(15, 15, 0, 15));

        return navbar;
    }

    private ImageView insertIcon(String filename, double size) {
        Image icon = new Image(getClass().getResourceAsStream("/images/" + filename));
        ImageView iv = new ImageView(icon);
        iv.setFitWidth(size);
        iv.setFitHeight(size);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        return iv;
    }

    private StackPane buildMainDashboardArea() {
        StackPane areaTengah = new StackPane();
        VBox.setVgrow(areaTengah, Priority.ALWAYS);
        VBox.setMargin(areaTengah, new Insets(5, 15, 5, 15));

        VBox dashboardPanel = new VBox(15);
        dashboardPanel.getStyleClass().add("content-panel"); 
        dashboardPanel.setPadding(new Insets(30));
        dashboardPanel.setMinHeight(380); 
        VBox.setVgrow(dashboardPanel, Priority.ALWAYS);
        
        ImageView homeIcon = insertIcon("home.png", 80);
        StackPane iconBox = new StackPane(homeIcon);
        iconBox.getStyleClass().add("icon-box");
        StackPane.setAlignment(homeIcon, Pos.CENTER);

        Label titleLabel = new Label("Dashboard");
        titleLabel.getStyleClass().add("title-label");
        Label statusLabel = new Label("Status: Online");
        statusLabel.getStyleClass().add("status-label");

        VBox textBox = new VBox(4, titleLabel, statusLabel);
        textBox.setAlignment(Pos.CENTER_LEFT);

        HBox headerRow = new HBox(20, iconBox, textBox);
        headerRow.setAlignment(Pos.CENTER_LEFT);

        VBox dashboardText = new VBox(10);
        dashboardText.setAlignment(Pos.TOP_LEFT);
        Label welcomeTitle = new Label ("Selamat Datang Kembali");
        welcomeTitle.getStyleClass().add("welcome-title");
        Label welcomeSub = new Label ("\"Mari lanjutkan produktivitas dan kembangkan keahlian Anda hari ini.\"");
        welcomeSub.getStyleClass().add("welcome-subtitle");

        dashboardText.getChildren().addAll(welcomeTitle, welcomeSub);
        dashboardPanel.getChildren().addAll(headerRow, dashboardText);


        ImageView mascot = insertIcon("character.png", 400);
        mascot.setMouseTransparent(true);
        
        StackPane.setAlignment(mascot, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(mascot, new Insets(0, 30, -15, 0));

        areaTengah.getChildren().addAll(dashboardPanel, mascot);

        return areaTengah;
    }

    private HBox buildMenuCards() {
        HBox row = new HBox(16, 
            buildCard("event.png", "Event &", "Workshop", ()-> Main.navigateTo("EVENT")),
            buildCard("komunitas.png", "Komunitas", "Kreatif", ()-> Main.navigateTo("COMMUNITY")),
            buildCard("booking.png", "Booking", "Fasilitas",()-> Main.navigateTo("BOOKING")),
            buildCard("skill.png", "Skill", "RoadMap",()->Main.navigateTo("ROADMAP"))
        );
        row.setPadding(new Insets(5, 15, 15, 15));
        row.setAlignment(Pos.CENTER);
        return row;
    }

    private VBox buildCard(String iconFile, String line1, String line2, Runnable action) {
        ImageView iconImg = insertIcon(iconFile, 46);

        Label title1 = new Label(line1);
        title1.getStyleClass().add("card-title");
        Label title2 = new Label(line2);
        title2.getStyleClass().add("card-title");
        VBox titleBox = new VBox(2, title1, title2);

        Button arrow = new Button("→");
        arrow.getStyleClass().add("card-arrow");
        arrow.setOnAction(e-> action.run());

        Region hSpacer = new Region();
        HBox.setHgrow(hSpacer, Priority.ALWAYS);

        Region vSpacer = new Region();
        VBox.setVgrow(vSpacer, Priority.ALWAYS); 
        
        VBox card = new VBox(10);
        card.getStyleClass().add("menu-card");
        card.setPrefHeight(160);
        card.setPadding(new Insets(18));
        
        HBox.setHgrow(card, Priority.ALWAYS);
        HBox topRow = new HBox(iconImg, hSpacer, arrow);
        topRow.setAlignment(Pos.CENTER_LEFT);
        card.getChildren().addAll(topRow, vSpacer, titleBox);
        card.setCursor(javafx.scene.Cursor.HAND); // Mengubah efek kursor menjadi jari penunjuk
        card.setOnMouseClicked(e -> action.run());
        return card;
    }
}