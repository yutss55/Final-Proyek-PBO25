package final_proyek_pbo.view;

import final_proyek_pbo.controller.CommunityController;
import final_proyek_pbo.model.CollabPost;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
        root.getChildren().add(tileContainer);

        tampilkanPostingan(controller.getKumpulanChatMaster());

        setContent(root);
        setFitToWidth(true);
    }

    private void loadCSS() {
        var css = getClass().getResource("/css/community.css");
        if (css != null) {
            root.getStylesheets().add(css.toExternalForm());
            root.getStyleClass().add("page-root");
        } else {
            System.out.println("STYLE.CSS TIDAK DITEMUKAN");
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

        btnTim.setOnAction(e -> {
            tampilkanPostingan(controller.filterChatBerdasarkanTag("[Cari_Tim]"));
            setTombolAktif(btnTim, btnProject, btnReset);
        });

        btnProject.setOnAction(e -> {
            tampilkanPostingan(controller.filterChatBerdasarkanTag("[Cari_Proyek]"));
            setTombolAktif(btnProject, btnTim, btnReset);
        });

        btnReset.setOnAction(e -> {
            tampilkanPostingan(controller.getKumpulanChatMaster());
            setTombolAktif(btnReset, btnTim, btnProject);
        });

        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(btnTim, btnProject, btnReset);

        VBox header = new VBox(15);
        header.getChildren().addAll(
                btnKembali,
                title,
                sub,
                search,
                buttonBox
        );

        root.getChildren().add(header);
    }

    private void setTombolAktif(Button tombolAktif, Button... tombolLainnya) {
        if (!tombolAktif.getStyleClass().contains("btn-active")) {
            tombolAktif.getStyleClass().add("btn-active");
        }
        
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
            VBox card = createCard(
                    post.getNamaPembuat() + " • " + post.getJudulPostingan(),
                    post.getIsiKonten()
            );
            tileContainer.getChildren().add(card);
        }
    }

    private VBox createCard(String title, String desc) {
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
        btnDetail.getStyleClass().add("collab-card-tag");

        btnDetail.setOnAction(e -> {
            final_proyek_pbo.Main.navigateTo("DETAIL_PROYEK");
        });

        card.getChildren().addAll(
                titleLabel,
                descriptionLabel,
                btnDetail
        );

        return card;
    }
}