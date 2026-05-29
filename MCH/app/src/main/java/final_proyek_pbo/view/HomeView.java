// package final_proyek_pbo.view;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.control.Button;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.control.Label;

// public class HomeView extends VBox {
//     public HomeView(){
//         this.getStyleClass().add("main-bg");
//         this.setSpacing(16);

//         this.getChildren().addAll(buildNavbar(), buildMainDashboardArea(), buildMenuCards());
//     }
//     private HBox buildNavbar() {
//         Button btnHome = new Button ("Home");
//         btnHome.getStyleClass().add("nav-btn-active");
//         Button btnProgram =new Button ("Program");
//         btnProgram.getStyleClass().add("nav-btn");
//         Button btnCommunity = new Button ("Community");
//         btnCommunity.getStyleClass().add("nav-btn");
//         Button btnRoadMap = new Button ("Road Map");
//         btnRoadMap.getStyleClass().add("nav-btn");
//         Button btnCircle = new Button ("");
//         btnCircle.getStyleClass().add("nav-circle");


//         Region spacer = new Region ();
//         HBox.setHgrow(spacer, Priority.ALWAYS);

//         HBox navbar = new HBox(4, btnHome, btnProgram, btnCommunity, btnRoadMap, spacer, btnCircle);
//         navbar.getStyleClass().add("navbar-container");
//         navbar.setAlignment(Pos.CENTER_LEFT);
//         VBox.setMargin(navbar, new Insets(15,15,0,15));

//         return navbar;
//     }

//     private ImageView insertIcon (String filename, double size){
//         Image icon = new Image(getClass().getResourceAsStream("/images/" + filename));
//         ImageView iv = new ImageView(icon);
//         iv.setFitWidth(size);
//         iv.setFitHeight(size);
//         iv.setPreserveRatio(true);
//         return iv;
//     }

//      private StackPane buildMainDashboardArea() {
//         StackPane areaTengah = new StackPane();
//         areaTengah.setAlignment(Pos.BOTTOM_RIGHT);
//         VBox.setVgrow(areaTengah, Priority.ALWAYS);
//         VBox.setMargin(areaTengah, new Insets(0, 15, 0, 15));

//         VBox dashboardPanel = new VBox(15);
//         dashboardPanel.getStyleClass().add("content-panel"); 
//         dashboardPanel.setPadding(new Insets(20));
//         dashboardPanel.setMinHeight(420); 
//         VBox.setVgrow(dashboardPanel, Priority.ALWAYS);

//         ImageView homeIcon = insertIcon("home.png", 70);
//         StackPane iconBox = new StackPane(homeIcon);
//         iconBox.getStyleClass().add("icon-box");

//         Label titleLabel = new Label("Dashboard");
//         titleLabel.getStyleClass().add("title-label");
//         Label statusLabel = new Label("Status: Online");
//         statusLabel.getStyleClass().add("status-label");

//         VBox textBox = new VBox(3, titleLabel, statusLabel);
//         textBox.setAlignment(Pos.CENTER_LEFT);

//         HBox headerRow = new HBox(16, iconBox, textBox);
//         headerRow.setAlignment(Pos.CENTER_LEFT);

//         dashboardPanel.getChildren().add(headerRow);

//         ImageView mascot = insertIcon("character.png", 520);
//         areaTengah.setAlignment(Pos.BOTTOM_RIGHT);
//         mascot.setTranslateY(0);  
//         mascot.setTranslateX(0);  
//         StackPane.setAlignment(mascot, Pos.BOTTOM_RIGHT);
//         StackPane.setMargin(mascot, new Insets(0, 20, -80, 0));

//         areaTengah.getChildren().addAll(dashboardPanel, mascot);

//         return areaTengah;
//     }
//     private HBox buildMenuCards(){
//         HBox row = new HBox(12, 
//             buildCard("event.png", "Event &", "Workshop", true),
//             buildCard("komunitas.png", "Komunitas", "Kreatif", false),
//             buildCard("booking.png", "Booking", "Fasilitas", true),
//             buildCard("skill.png", "Skill", "RoadMap", false)
//          );
//          row.setPadding(new Insets(0, 15, 15, 15));
//          row.setPrefHeight(160);
//          return row;
//     }

//     private VBox buildCard (String iconFile, String line1, String line2, boolean iconTop){
//         ImageView iconImg = insertIcon(iconFile, 200);

//         Label title1 = new Label(line1);
//         title1.getStyleClass().add("card-title");
//         Label title2 = new Label(line2);
//         title2.getStyleClass().add("card-title");
//         VBox titleBox = new VBox(2, title1, title2);

//         Button arrow = new Button("›");
//         arrow.getStyleClass().add("card-arrow");

//         Region hSpacer = new Region();
//         HBox.setHgrow(hSpacer, Priority.ALWAYS);

//         HBox arrowRow = new HBox(hSpacer, arrow);
//         arrowRow.setAlignment(Pos.CENTER_RIGHT);

//         Region vSpacer = new Region();
//         VBox.setVgrow(vSpacer, Priority.ALWAYS); 
        
//         VBox card = new VBox(6);
//         card.getStyleClass().add("menu-card");
//         card.setPrefHeight(160);
//         HBox.setHgrow(card, Priority.ALWAYS);

//         if (iconTop){
//             card.getChildren().addAll(iconImg, titleBox, arrowRow);
//         } else {
//             HBox iconWrapper = new HBox(iconImg);
//             iconWrapper.setAlignment(Pos.BOTTOM_RIGHT);
//             card.getChildren().addAll(titleBox, iconWrapper, arrowRow);
//         }
//         return card;
//     }
// }
package final_proyek_pbo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class HomeView extends VBox {

    public HomeView() {
        this.getStyleClass().add("main-bg");
        this.setSpacing(20);
        this.setFillWidth(true);

        this.getChildren().addAll(buildNavbar(), buildMainDashboardArea(), buildMenuCards());
    }

    private HBox buildNavbar() {
        Button btnHome = new Button("Home");
        btnHome.getStyleClass().add("nav-btn-active");
        Button btnProgram = new Button("Program");
        btnProgram.getStyleClass().add("nav-btn");
        Button btnCommunity = new Button("Community");
        btnCommunity.getStyleClass().add("nav-btn");
        Button btnRoadMap = new Button("Road Map");
        btnRoadMap.getStyleClass().add("nav-btn");

        Button btnCircle = new Button("👤");
        btnCircle.getStyleClass().add("nav-circle");
        ContextMenu profile = new ContextMenu();
        MenuItem profilItem = new MenuItem("Profile");
        MenuItem setItem = new MenuItem("Settings");
        MenuItem logItem = new MenuItem("Logout");

        profile.getItems().addAll(profilItem, setItem,new SeparatorMenuItem(), logItem);
        btnCircle.setOnAction(e->{
            profile.show(btnCircle, Side.BOTTOM, 0, 0);
        });
        logItem.setOnAction(e->{
            LandingView landing =new LandingView();
            btnCircle.getScene().setRoot(landing.getView());
        });


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox navbar = new HBox(8, btnHome, btnProgram, btnCommunity, btnRoadMap, spacer, btnCircle);
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

        // Ukuran ikon dashboard diperbesar ke 54 agar terlihat jelas di dalam kotak
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

        // Karakter maskot berukuran ideal agar serasi di pojok kanan bawah
        ImageView mascot = insertIcon("character.png", 400);
        mascot.setMouseTransparent(true); // Agar klik mouse menembus maskot ke tombol di belakangnya
        
        StackPane.setAlignment(mascot, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(mascot, new Insets(0, 30, -15, 0));

        areaTengah.getChildren().addAll(dashboardPanel, mascot);

        return areaTengah;
    }

    private HBox buildMenuCards() {
        HBox row = new HBox(16, 
            buildCard("event.png", "Event &", "Workshop", true),
            buildCard("komunitas.png", "Komunitas", "Kreatif", true),
            buildCard("booking.png", "Booking", "Fasilitas", true),
            buildCard("skill.png", "Skill", "RoadMap", true)
        );
        row.setPadding(new Insets(5, 15, 15, 15));
        row.setAlignment(Pos.CENTER);
        return row;
    }

    private VBox buildCard(String iconFile, String line1, String line2, boolean iconTop) {
        // Ukuran ikon menu card ditingkatkan ke 46 agar lebih menonjol
        ImageView iconImg = insertIcon(iconFile, 46);

        Label title1 = new Label(line1);
        title1.getStyleClass().add("card-title");
        Label title2 = new Label(line2);
        title2.getStyleClass().add("card-title");
        VBox titleBox = new VBox(2, title1, title2);

        Button arrow = new Button("→");
        arrow.getStyleClass().add("card-arrow");

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
    
        return card;
    }
}