package final_proyek_pbo.view;

import final_proyek_pbo.controller.EventController;
import final_proyek_pbo.model.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EventCard extends HBox {

    private final Event event;
    private final EventController controller;

    public EventCard(Event event, EventController controller) {
        this.event = event;
        this.controller = controller;
        initializeComponents();
    }

    private void initializeComponents() {
        getStyleClass().add("event-card");

        setSpacing(16);
        setPadding(new Insets(16));
        setAlignment(Pos.CENTER_LEFT);

       
        setPrefWidth(520);
        setMaxWidth(560);
        setMinHeight(180);


        StackPane iconBox = new StackPane();
        iconBox.getStyleClass().add("event-icon-box");
        iconBox.getStyleClass().add("event-icon-box-" + event.getKategori().toLowerCase());

        iconBox.setMinWidth(80);
        iconBox.setMaxWidth(80);
        iconBox.setMinHeight(115);
        iconBox.setMaxHeight(115);

        ImageView iconView = new ImageView(
                new Image(getClass().getResourceAsStream(getIconPath())));

        iconView.setFitWidth(42);
        iconView.setFitHeight(42);
        iconView.setPreserveRatio(true);

        iconBox.getChildren().add(iconView);


        VBox contentBox = new VBox(6);
        contentBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(contentBox, Priority.ALWAYS);

        HBox badgeRow = new HBox(8);
        badgeRow.setAlignment(Pos.CENTER_LEFT);

        Label kategoriLabel = new Label(event.getKategori());
        kategoriLabel.getStyleClass().addAll("event-badge", "event-badge-" + event.getKategori().toLowerCase());
        badgeRow.getChildren().add(kategoriLabel);

        if (event.isPenuh()) {
            Label penuhBadge = new Label("PENUH");
            penuhBadge.getStyleClass().add("event-badge-penuh");
            badgeRow.getChildren().add(penuhBadge);
        }

        Label namaLabel = new Label(event.getNama());
        namaLabel.getStyleClass().add("event-title");
        namaLabel.setWrapText(true);

        Label deskripsiLabel = new Label(event.getDeskripsi());
        deskripsiLabel.getStyleClass().add("event-desc");
        deskripsiLabel.setWrapText(true);
        deskripsiLabel.setMaxWidth(Double.MAX_VALUE);

        Label mentorLabel = new Label("Mentor: " + event.getMentor());
        mentorLabel.getStyleClass().add("event-mentor");

        Separator separator = new Separator();
        separator.getStyleClass().add("event-separator");
        VBox.setMargin(separator, new Insets(2, 0, 2, 0));

        HBox footerRow = new HBox();
        footerRow.setAlignment(Pos.CENTER_LEFT);

        Label kuotaLabel = new Label("Kuota: " + event.getKuota());
        kuotaLabel.getStyleClass().add("event-kuota");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button daftarButton = new Button();
        updateButtonState(daftarButton);

        daftarButton.setOnAction(e -> {
            boolean berhasil = controller.daftarWorkshop(event);
            if (berhasil) {
                kuotaLabel.setText("Kuota: " + event.getKuota());
                updateButtonState(daftarButton);
            }
        });

        footerRow.getChildren().addAll(kuotaLabel, spacer, daftarButton);

        contentBox.getChildren().addAll(
                badgeRow,
                namaLabel,
                deskripsiLabel,
                mentorLabel,
                separator,
                footerRow
        );

        getChildren().addAll(iconBox, contentBox);
    }

    private void updateButtonState(Button button) {
        button.getStyleClass().removeAll("event-btn", "event-btn-success", "event-btn-disabled");

        if (event.isPenuh()) {
            button.setText("Kuota Habis");
            button.setDisable(true);
            button.getStyleClass().add("event-btn-disabled");
        } else if (event.isTerdaftar()) {
            button.setText("✓ Terdaftar");
            button.setDisable(true);
            button.getStyleClass().add("event-btn-success");
        } else {
            button.setText("Daftar Instan");
            button.setDisable(false);
            button.getStyleClass().add("event-btn");
        }
    }

    private String getIconPath() {
        switch (event.getKategori().toUpperCase()) {
            case "DESAIN": return "/images/event/design.png";
            case "CODING": return "/images/event/coding.png";
            case "BISNIS": return "/images/event/business.png";
            default:       return "/images/event/default.png";
        }
    }
}