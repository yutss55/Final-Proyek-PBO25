package final_proyek_pbo.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DetailProyekView extends ScrollPane {
    private VBox mainContainer;
    private Button btnKembali;
    private Button btnDaftarKolaborasi;

    public DetailProyekView() {
        buildMainLayout();
        buildDetailContent();
        initRootConfiguration();
    }

    private void buildMainLayout() {
        mainContainer = new VBox();
        mainContainer.setSpacing(25);
        mainContainer.setPadding(new Insets(30));
        
        mainContainer.setMaxHeight(Double.MAX_VALUE);
        mainContainer.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(mainContainer, Priority.ALWAYS);

        try {
            var cssResource = getClass().getResource("/css/detailproyek.css");
            if (cssResource != null) {
                mainContainer.getStylesheets().add(cssResource.toExternalForm());
                mainContainer.getStyleClass().add("page-root"); 
            }
        } catch (Exception e) {
            System.out.println("Gagal memuat CSS di DetailView: " + e.getMessage());
        }
        
        this.setContent(mainContainer);
    }

    private void buildDetailContent() {
        // Tombol Kembali Berbentuk Panah Kiri
        btnKembali = new Button("← Kembali");
        btnKembali.getStyleClass().add("back-button");

        btnKembali.setOnAction(e -> {
        final_proyek_pbo.Main.navigateTo("COMMUNITY");
   });

        VBox detailCard = new VBox();
        detailCard.setSpacing(20);
        detailCard.setPadding(new Insets(30));
        detailCard.getStyleClass().add("collab-card"); 
        detailCard.setMaxWidth(800); 

        Label infoTitle = new Label("Detail Informasi Kolaborasi");
        infoTitle.getStyleClass().add("collab-card-title");
        infoTitle.setStyle("-fx-font-size: 22px;"); 

        // Deskripsi Lengkap Proyek
        Label descriptionLabel = new Label(
            "Nama Proyek: Pengembangan Platform PINISI (Kreavo)\n\n" +
            "Deskripsi:\nProyek ini bertujuan untuk membangun platform inkubasi talenta digital masyarakat " +
            "Kota Makassar. Kami membutuhkan tim yang solid untuk mengeksekusi bagian Front-End maupun Back-End.\n\n" +
            "Kriteria Anggota Terbuka:\n" +
            "• Memahami konsep dasar PBO (Java / JavaFX)\n" +
            "• Mampu bekerja sama menggunakan Git/GitHub\n" +
            "• Berkomitmen menyelesaikan proyek tepat waktu"
        );
        descriptionLabel.getStyleClass().add("collab-desc");
        descriptionLabel.setWrapText(true);

        btnDaftarKolaborasi = new Button("Ajukan Kolaborasi Sekarang");
        btnDaftarKolaborasi.getStyleClass().add("start-button");
        btnDaftarKolaborasi.setPrefWidth(250);

        detailCard.getChildren().addAll(infoTitle, descriptionLabel, btnDaftarKolaborasi);

        mainContainer.getChildren().addAll(btnKembali, detailCard);
    }

    private void initRootConfiguration() {
        this.setFitToWidth(true);
        this.setFitToHeight(true);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
    }
}