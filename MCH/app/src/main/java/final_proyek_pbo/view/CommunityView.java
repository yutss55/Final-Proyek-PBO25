package final_proyek_pbo.view;

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

    public CommunityView() {

        root = new VBox(25);
        root.setPadding(new Insets(30));

        loadCSS();
        bikinHeader();
        bikinProjectSection();

        setContent(root);
        setFitToWidth(true);
    }

    private void loadCSS() {

    var css = getClass().getResource("/css/community.css");

    System.out.println("CSS = " + css);

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
            System.out.println("Kembali");
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

        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(btnTim, btnProject);

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

    private void bikinProjectSection() {

        TilePane tile = new TilePane();
        tile.setHgap(20);
        tile.setVgap(20);
        tile.setPrefColumns(3);

        for (int i = 1; i <= 9; i++) {

            VBox card = createCard(
                    "Proyek " + i,
                    "Deskripsi singkat proyek ke " + i
            );

            tile.getChildren().add(card);
        }

        root.getChildren().add(tile);
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
            System.out.println("DETAIL DIKLIK");
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