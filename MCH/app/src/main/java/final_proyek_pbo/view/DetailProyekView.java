package final_proyek_pbo.view;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DetailProyekView extends VBox {

    public DetailProyekView() {
        setSpacing(20);
        setPadding(new Insets(30));
        
        var css = getClass().getResource("/css/community.css");
        if (css != null) {
            this.getStylesheets().add(css.toExternalForm());
        }
        
        this.getStyleClass().add("page-root"); 

        Button btnKembali = new Button("← Kembali");
        btnKembali.getStyleClass().add("back-button"); 
        btnKembali.setOnAction(e -> {
        final_proyek_pbo.Main.navigateTo("COMMUNITY");
        });

        Label titleLabel = new Label("Detail Pengembangan Proyek IT");
        titleLabel.getStyleClass().add("main-title");

        Label descLabel = new Label("Proyek ini berfokus pada integrasi arsitektur sistem informasi cerdas.\n"
                + "Dibutuhkan keahlian pemrograman Backend (Java/Python) dan analisis data.");
        descLabel.getStyleClass().add("sub-title");
        descLabel.setWrapText(true);

        Button btnAjukan = new Button("Ajukan Kolaborasi Sekarang");
        btnAjukan.getStyleClass().add("btn-main"); 
        
        btnAjukan.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kolaborasi Berhasil");
            alert.setHeaderText(null);
            alert.setContentText("Permohonan kolaborasi Anda telah berhasil dikirimkan kepada pemilik proyek!");
            alert.showAndWait();
        });

        getChildren().addAll(btnKembali, titleLabel, descLabel, btnAjukan);
    }
}