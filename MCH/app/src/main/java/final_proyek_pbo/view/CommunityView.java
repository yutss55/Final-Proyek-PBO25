package final_proyek_pbo.view;

import final_proyek_pbo.controller.CommunityController;
import final_proyek_pbo.model.CollabPost;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class CommunityView extends ScrollPane {

    private VBox root;
    private TilePane tileContainer;
    private CommunityController controller;

    public CommunityView() {
        this.controller = new CommunityController();

        root = new VBox(25);
        root.setPadding(new Insets(30));

        loadCSS();
        bikinHeader();
        
        tileContainer = new TilePane();
        tileContainer.setHgap(20);
        tileContainer.setVgap(20);
        tileContainer.setPrefColumns(3);
        tileContainer.setStyle("-fx-background-color: transparent;");

        root.getChildren().add(tileContainer);

        tampilkanPostingan(controller.getKumpulanChatMaster());

        setContent(root);
        setFitToWidth(true);
        setFitToHeight(true);
    }

    private void loadCSS() {
        var css = getClass().getResource("/css/community.css");
        if (css != null) {
            root.getStylesheets().add(css.toExternalForm());
            root.getStyleClass().add("page-root");
        }
    }

    private void bikinHeader() {
        Button btnKembali = new Button("← Kembali");
        btnKembali.getStyleClass().add("back-button");
        btnKembali.setOnAction(e -> {
            final_proyek_pbo.Main.navigateTo("HOME");
        });

        Label title = new Label("Community");
        title.getStyleClass().add("main-title");

        Label sub = new Label("Temukan tim dan proyek yang cocok");
        sub.getStyleClass().add("sub-title");

        TextField search = new TextField();
        search.setPromptText("Cari project atau tim...");
        search.getStyleClass().add("search-field");

        Button btnTim = new Button("Cari Tim");
        btnTim.getStyleClass().add("btn-main");

        Button btnProject = new Button("Cari Proyek");
        btnProject.getStyleClass().add("btn-second");
        
        Button btnReset = new Button("Semua");
        btnReset.getStyleClass().add("btn-main");

        Button btnTambah = new Button("+ Buat Postingan");
        btnTambah.getStyleClass().add("btn-main");
        btnTambah.setOnAction(e -> showTambahPostDialog());

        btnTim.setOnAction(e -> {
            tampilkanPostingan(controller.filterChatBerdasarkanTag("[Cari_Tim]"));
            setTombolAktif(btnTim, btnProject, btnReset, btnTambah);
        });

        btnProject.setOnAction(e -> {
            tampilkanPostingan(controller.filterChatBerdasarkanTag("[Cari_Proyek]"));
            setTombolAktif(btnProject, btnTim, btnReset, btnTambah);
        });

        btnReset.setOnAction(e -> {
            tampilkanPostingan(controller.getKumpulanChatMaster());
            setTombolAktif(btnReset, btnTim, btnProject, btnTambah);
        });

        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(btnTim, btnProject, btnReset, btnTambah);

        VBox header = new VBox(15);
        header.getChildren().addAll(btnKembali, title, sub, search, buttonBox);

        root.getChildren().add(header);
    }

    private void setTombolAktif(Button tombolAktif, Button... tombolLainnya) {
        tombolAktif.getStyleClass().add("btn-active");
        for (Button btn : tombolLainnya) {
            btn.getStyleClass().remove("btn-active");
        }
    }

    private void tampilkanPostingan(ObservableList<CollabPost> listPostingan) {
        tileContainer.getChildren().clear();

        if (listPostingan.isEmpty()) {
            Label kosong = new Label("Belum ada postingan kolaborasi.");
            kosong.getStyleClass().add("label-kosong-komunitas"); 
            tileContainer.getChildren().add(kosong);
            return;
        }

        for (CollabPost post : listPostingan) {
            VBox card = createCard(post,
                    post.getNamaPembuat() + " • " + post.getJudulPostingan(),
                    post.getIsiKonten()
            );
            tileContainer.getChildren().add(card);
        }
    }

    private VBox createCard(CollabPost post, String title, String desc) {
        VBox card = new VBox(12);
        card.setPadding(new Insets(20));
        card.setPrefWidth(260);
        card.getStyleClass().add("collab-card");

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("collab-card-title");

        Label descriptionLabel = new Label(desc);
        descriptionLabel.setWrapText(true);
        descriptionLabel.getStyleClass().add("collab-desc");

        Button btnDetail = new Button("Lihat Detail");
        btnDetail.getStyleClass().add("btn-detail");
        btnDetail.setOnAction(e -> {
            DetailProyekView.currentPost = post;
            final_proyek_pbo.Main.navigateTo("DETAIL_PROYEK");
        });

        card.getChildren().addAll(titleLabel, descriptionLabel, btnDetail);

        return card;
    }

    private void showTambahPostDialog() {
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Buat Postingan Baru");

    dialog.getDialogPane().getStylesheets().add(
        getClass().getResource("/css/community.css").toExternalForm()
    );

    dialog.getDialogPane().getStyleClass().add("custom-dialog");

    TextField txtJudul = new TextField();
    txtJudul.setPromptText("Judul");
    
    TextField txtKonten = new TextField();
    txtKonten.setPromptText("Deskripsi");

    ComboBox<String> cmbTag = new ComboBox<>();
    cmbTag.getItems().addAll("[Cari_Tim]", "[Cari_Proyek]");
    cmbTag.setValue("[Cari_Tim]");

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.add(new Label("Judul:"), 0, 0); grid.add(txtJudul, 1, 0);
    grid.add(new Label("Deskripsi:"), 0, 1); grid.add(txtKonten, 1, 1);
    grid.add(new Label("Kategori:"), 0, 2); grid.add(cmbTag, 1, 2);
    
    dialog.getDialogPane().setContent(grid);
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    dialog.setResultConverter(button -> {
        if (button == ButtonType.OK && !txtJudul.getText().isEmpty()) {
            CollabPost newPost = new CollabPost(
                "Postingan Kolaborasi", 0, null,
                final_proyek_pbo.data.UserData.currentUser.getNama(),
                cmbTag.getValue() + " " + txtJudul.getText(),
                txtKonten.getText()
            );
            controller.getKumpulanChatMaster().add(newPost);
            tampilkanPostingan(controller.getKumpulanChatMaster());
        }
        return null;
    });

    dialog.showAndWait();
}
}