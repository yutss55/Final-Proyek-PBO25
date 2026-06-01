package final_proyek_pbo.view;

import java.time.LocalDate;

import final_proyek_pbo.controller.BookingController;
import final_proyek_pbo.model.BookingSession;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookingView extends ScrollPane {
    
    private VBox mainContainer;
    private TableView<BookingSession> historyTable;
    private ComboBox<String> roomBox;
    private ComboBox<String> comboBoxJam;
    private DatePicker datePicker;
    private Button btnBookNow;
    private Button btnBackHome;
    private BookingController controller;
    private Label lblTotalRuangan;
    private Label lblDipinjam;
    private Label lblMenunggu;
    private Label lblSelesai;

    public BookingView() {
        this.controller = new BookingController();
        
        buildMainLayout();
        buildHeaderSection();
        buildHistoryTableSection();
        buildStatisticsCards();
        initRootConfiguration();
        
        updateStatistikOtomatis();
    }

    private void buildMainLayout() {
        mainContainer = new VBox(); 
        mainContainer.setSpacing(30); 
        mainContainer.setPadding(new Insets(35)); 
        mainContainer.getStyleClass().add("page-root");
        mainContainer.setStyle("-fx-background-color: #0b071e;");

        try {
            var cssResource = getClass().getResource("/css/booking.css");
            if (cssResource != null) {
                mainContainer.getStylesheets().add(cssResource.toExternalForm());
            }
        } catch (Exception e) {
            System.out.println("CSS gagal dimuat : " + e.getMessage());
        }

        this.setContent(mainContainer);
    }

    private void buildHeaderSection() {
        btnBackHome = new Button("← Kembali");
        btnBackHome.getStyleClass().add("back-button");
        btnBackHome.setOnAction(e -> {
            final_proyek_pbo.Main.navigateTo("HOME");
        });

        Label title = new Label("Form Pemesanan Ruangan PINISI");
        title.getStyleClass().add("main-title");

        Label subtitle = new Label("Pilih ruang kolaborasimu sekarang dan mulailah berlayar menuju masa depan tech-savvy!");
        subtitle.getStyleClass().add("sub-title");

        VBox titleBox = new VBox(10);
        titleBox.getChildren().addAll(title, subtitle);

        roomBox = new ComboBox<>();
        roomBox.setItems(controller.getDaftarRuangan());
        roomBox.setPromptText("Pilih Ruangan");
        
        comboBoxJam = new ComboBox<>();
        comboBoxJam.setItems(controller.getDaftarJamSpesifik());
        comboBoxJam.setPromptText("Pilih Jam");

        datePicker = new DatePicker();
        datePicker.setPromptText("Pilih Tanggal");

        btnBookNow = new Button("📅 Book Now");
        btnBookNow.getStyleClass().add("book-button");

        btnBookNow.setOnAction(e -> {
            String room = roomBox.getValue();
            LocalDate date = datePicker.getValue();
            String jam = comboBoxJam.getValue();
            String namaUser = "Ayu Anggraini";

            if (room == null || date == null || jam == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("Lengkapi data booking dulu");
                alert.showAndWait();
                return;
            }

            boolean isBentrok = controller.cekJadwalBentrok(room, date, jam);
            if (isBentrok) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Booking Gagal");
                alert.setHeaderText(null);
                alert.setContentText("Maaf, ruangan tersebut sudah dipesan pada tanggal dan jam tersebut!");
                alert.showAndWait();
                return;
            }

            boolean sukses = controller.buatPemesanan(namaUser, room, date, jam);
            
            if (sukses) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sukses");
                alert.setHeaderText(null);
                alert.setContentText("Ruangan berhasil dipesan! Silakan cek tabel history.");
                alert.showAndWait();

                updateStatistikOtomatis();
                
                roomBox.setValue(null);
                datePicker.setValue(null);
                comboBoxJam.setValue(null);
            }
        });

        HBox formBox = new HBox(15);
        formBox.setAlignment(Pos.CENTER_LEFT);
        formBox.getChildren().addAll(roomBox, datePicker, comboBoxJam, btnBookNow);

        VBox headerContainer = new VBox(20);
        headerContainer.getChildren().addAll(btnBackHome, titleBox, formBox);

        mainContainer.getChildren().add(headerContainer);
    }

    @SuppressWarnings("unchecked")
    private void buildHistoryTableSection() {
        VBox tableContainer = new VBox();
        tableContainer.setSpacing(20);
        tableContainer.getStyleClass().add("card-container");

        Label historyTitle = new Label("Booking History");
        historyTitle.getStyleClass().add("section-title");
        VBox.setMargin(historyTitle, new Insets(15, 0, 5, 5));

        historyTable = new TableView<>();
        historyTable.getStyleClass().add("modern-table");
        historyTable.setPrefHeight(250);
        historyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<BookingSession, String> nameCol = new TableColumn<>("Nama Ruangan");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().namaRuanganProperty());

        TableColumn<BookingSession, String> dateCol = new TableColumn<>("Tanggal Pinjam");
        dateCol.setCellValueFactory(cellData -> cellData.getValue().tanggalSewaProperty());

        TableColumn<BookingSession, String> jamCol = new TableColumn<>("Waktu / Sesi");
        jamCol.setCellValueFactory(cellData -> cellData.getValue().jamSpesifikProperty());

        TableColumn<BookingSession, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cellData -> cellData.getValue().statusApprovalProperty());

        historyTable.getColumns().addAll(nameCol, dateCol, jamCol, statusCol);
        historyTable.setItems(controller.getRiwayatBookingMaster());
        
        tableContainer.getChildren().addAll(historyTitle, historyTable);
        mainContainer.getChildren().add(tableContainer);
    }

    private void buildStatisticsCards() {
        HBox statsContainer = new HBox();
        statsContainer.setSpacing(20);

        lblTotalRuangan = new Label("0");
        lblDipinjam = new Label("0");
        lblMenunggu = new Label("0");
        lblSelesai = new Label("0");

        VBox card1 = createStatCard(lblTotalRuangan, "Total Ruangan");
        VBox card2 = createStatCard(lblDipinjam, "Dipinjam");
        VBox card3 = createStatCard(lblMenunggu, "Menunggu");
        VBox card4 = createStatCard(lblSelesai, "Selesai");

        statsContainer.getChildren().addAll(card1, card2, card3, card4);
        mainContainer.getChildren().add(statsContainer);
    }

    private VBox createStatCard(Label numberLabel, String text) {
        numberLabel.getStyleClass().add("stat-number");

        Label textLabel = new Label(text);
        textLabel.getStyleClass().add("stat-text");

        VBox card = new VBox();
        card.setSpacing(10);
        card.setPadding(new Insets(20));
        card.setPrefWidth(200);
        card.getStyleClass().add("stat-card");
        card.getChildren().addAll(numberLabel, textLabel);

        return card;
    }

    private void updateStatistikOtomatis() {
        int totalAsetLab = controller.getDaftarRuangan().size(); 
        int jumlahDipinjam = 0;
        int jumlahMenunggu = 0;
        int jumlahSelesai = 0;

        for (BookingSession b : this.controller.getRiwayatBookingMaster()) {
            String status = b.statusApprovalProperty().get();
            if (status.equalsIgnoreCase("Dipinjam") || status.equalsIgnoreCase("Approved")) {
                jumlahDipinjam++; 
            } else if (status.equalsIgnoreCase("Menunggu")) {
                jumlahMenunggu++;
            } else if (status.equalsIgnoreCase("Selesai")) {
                jumlahSelesai++;
            }
        }

        lblTotalRuangan.setText(String.valueOf(totalAsetLab));
        lblDipinjam.setText(String.valueOf(jumlahDipinjam));
        lblMenunggu.setText(String.valueOf(jumlahMenunggu));
        lblSelesai.setText(String.valueOf(jumlahSelesai));
    }

    private void initRootConfiguration() {
        this.setFitToWidth(true);
        this.setPadding(new Insets(10));
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-view-order: 1;
                -fx-viewport-background: transparent;
                -fx-box-border: transparent;
                """);
    }

    public Button getBtnBookNow() {
        return btnBookNow;
    }

    public Button getBtnBackHome() {
        return btnBackHome;
    }

    public TableView<BookingSession> getHistoryTable() {
        return historyTable;
    }
}