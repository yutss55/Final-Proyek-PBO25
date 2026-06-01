package final_proyek_pbo.view;

import final_proyek_pbo.model.CollabPost;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DetailProyekView extends VBox {
    public static CollabPost currentPost;

    public DetailProyekView() {
        CollabPost post = currentPost;

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

        Label titleLabel = new Label(post.getJudulPostingan());
        titleLabel.getStyleClass().add("main-title");

        Label descLabel = new Label(post.getIsiKonten());
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