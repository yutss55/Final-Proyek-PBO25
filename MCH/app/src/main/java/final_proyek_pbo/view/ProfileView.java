package final_proyek_pbo.view;

import final_proyek_pbo.Main;
import final_proyek_pbo.data.UserData; 
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProfileView extends BorderPane {

    public ProfileView() {
       
        this.getStyleClass().add("main-bg"); 
        
       
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(20, 25, 10, 25));
        
        Button btnBack = new Button("← Kembali ke Dashboard");
        btnBack.getStyleClass().add("btn-back"); 
        btnBack.setStyle("-fx-background-color: #301665; -fx-text-fill: white; -fx-cursor: hand;");
        btnBack.setOnAction(e -> Main.navigateTo("HOME"));
        
        topBar.getChildren().add(btnBack);
        this.setTop(topBar);

       
        String namaUser = "Guest";
        String emailUser = "-";

       
        if (UserData.currentUser != null) {
            namaUser = UserData.currentUser.getNama();
            emailUser = UserData.currentUser.getEmail();
        }

       
        VBox mainContent = new VBox(25);
        mainContent.setAlignment(Pos.TOP_CENTER);
        mainContent.setPadding(new Insets(30, 50, 50, 50));

       
        StackPane avatarBox = new StackPane();
        Circle innerCircle = new Circle(50, Color.web("#9D7BEA"));
        Label avatarLabel = new Label("👤");
        avatarLabel.setFont(Font.font("System", 45));
        avatarLabel.setTextFill(Color.WHITE);
        avatarBox.getChildren().addAll(innerCircle, avatarLabel);

        
        Label nameLabel = new Label(namaUser); 
        nameLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        nameLabel.setTextFill(Color.WHITE);

        Label roleLabel = new Label("Student / Member PINISI");
        roleLabel.setFont(Font.font("System", 14));
        roleLabel.setTextFill(Color.web("#9D7BEA"));

        VBox headerBox = new VBox(5, avatarBox, nameLabel, roleLabel);
        headerBox.setAlignment(Pos.CENTER);

       
        VBox infoCard = new VBox(15);
        infoCard.getStyleClass().add("content-panel");
        infoCard.setStyle("-fx-background-color: rgba(48, 22, 101, 0.4); -fx-background-radius: 15;");
        infoCard.setPadding(new Insets(25));
        infoCard.setMaxWidth(500);

        
        infoCard.getChildren().addAll(
            createGridRow("Nama Lengkap :", namaUser),
            createGridRow("Email Akun   :", emailUser),
            createGridRow("Status User  :", "Premium Member")
        );

        mainContent.getChildren().addAll(headerBox, infoCard);
        this.setCenter(mainContent);
    }

    
    private HBox createGridRow(String labelText, String valueText) {
        Label lblLeft = new Label(labelText);
        lblLeft.setFont(Font.font("System", FontWeight.BOLD, 14));
        lblLeft.setTextFill(Color.web("#9D7BEA"));
        lblLeft.setPrefWidth(140); // Lebar disesuaikan agar rapi

        Label lblRight = new Label(valueText);
        lblRight.setFont(Font.font("System", 14));
        lblRight.setTextFill(Color.WHITE);

        HBox row = new HBox(10, lblLeft, lblRight);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }
}