package final_proyek_pbo.view;

import final_proyek_pbo.model.Ruangan;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookingView extends ScrollPane {
    private VBox mainContainer;
    private TableView<Ruangan> historyTable;
    private ComboBox<String> roomBox;
    private DatePicker datePicker;
    private Button btnBookNow;
    private Button btnBackHome;

    public BookingView() {
        buildMainLayout();
        buildHeaderSection();
        buildHistoryTableSection();
        buildStatisticsCards();
        initRootConfiguration();
    }

    private void buildMainLayout() {
        mainContainer = new VBox(); 
        mainContainer.setSpacing(30); 
        mainContainer.setPadding(new Insets(35)); 
        mainContainer.getStyleClass().add("page-root");
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

    Label title = new Label("Form Pemesanan Ruangan");
    title.getStyleClass().add("main-title");

    Label subtitle = new Label("Kelola pemesanan ruangan dengan mudah dan cepat");
    subtitle.getStyleClass().add("sub-title");

    VBox titleBox = new VBox(10);
    titleBox.getChildren().addAll(title, subtitle);

    roomBox = new ComboBox<>();
    roomBox.getItems().addAll(
            "Cyber Security",
            "Front-End Development",
            "Back-End Development",
            "Data Science",
            "Artificial Intelligence",
            "UI/UX Design"
    );
    roomBox.setPromptText("Pilih Ruangan");

    datePicker = new DatePicker();
    datePicker.setPromptText("Pilih Tanggal");

    btnBookNow = new Button("📅 Book Now");
    btnBookNow.getStyleClass().add("book-button");

    btnBookNow.setOnAction(e -> {

        String room = roomBox.getValue();

        if (room == null || datePicker.getValue() == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Lengkapi data booking dulu");
            alert.show();
            return;
        }

        historyTable.getItems().add(
                new Ruangan(
                        room,
                        datePicker.getValue().toString(),
                        "Menunggu"
                )
        );

        roomBox.setValue(null);
        datePicker.setValue(null);
    });

    HBox formBox = new HBox(15);
    formBox.setAlignment(Pos.CENTER_LEFT);
    formBox.getChildren().addAll(
            roomBox,
            datePicker,
            btnBookNow
    );

    VBox headerContainer = new VBox(20);
    headerContainer.getChildren().addAll(
            btnBackHome,
            titleBox,
            formBox
    );

    mainContainer.getChildren().add(headerContainer);
}

    @SuppressWarnings("unchecked")
    private void buildHistoryTableSection() {
        VBox tableContainer = new VBox();
        tableContainer.setSpacing(20);
        tableContainer.getStyleClass().add("card-container");

        Label historyTitle = new Label("Booking History");
        historyTitle.getStyleClass().add("section-title");

        historyTable = new TableView<>();
        historyTable.getStyleClass().add("modern-table");
        historyTable.setPrefHeight(250);
        historyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Ruangan, String> nameCol = new TableColumn<>("Nama Ruangan");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("namaRuangan"));

        TableColumn<Ruangan, String> dateCol = new TableColumn<>("Tanggal Pinjam");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));

        TableColumn<Ruangan, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        historyTable.getColumns().addAll(nameCol, dateCol, statusCol);
        tableContainer.getChildren().addAll(historyTitle, historyTable);
        
        mainContainer.getChildren().add(tableContainer);
    }

    private void buildStatisticsCards() {
        HBox statsContainer = new HBox();
        statsContainer.setSpacing(20);

        VBox card1 = createStatCard("12", "Total Ruangan");
        VBox card2 = createStatCard("5", "Dipinjam");
        VBox card3 = createStatCard("3", "Menunggu");
        VBox card4 = createStatCard("20", "Selesai");

        statsContainer.getChildren().addAll(card1, card2, card3, card4);
        mainContainer.getChildren().add(statsContainer);
    }

    private VBox createStatCard(String number, String text) {
        Label numberLabel = new Label(number);
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

    private void initRootConfiguration() {
        this.setFitToWidth(true);
        this.setPadding(new Insets(10));
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-background: transparent;
                """);
    }

    public Button getBtnBookNow() {
        return btnBookNow;
    }

    public Button getBtnBackHome() {
        return btnBackHome;
    }

    public TableView<Ruangan> getHistoryTable() {
        return historyTable;
    }
}
