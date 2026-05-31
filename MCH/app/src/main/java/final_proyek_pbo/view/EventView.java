package final_proyek_pbo.view;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import final_proyek_pbo.controller.EventController;
import final_proyek_pbo.model.Event;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class EventView extends ScrollPane {

    private final EventController controller;

    private GridPane eventGrid;
    private TextField searchField;
    private TableView<Event> riwayatTable;

    public EventView(EventController controller) {
        this.controller = controller;

        setFitToWidth(true);
        setPannable(true);
        setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background: transparent;");
        getStyleClass().add("event-scrollpane");

        VBox outerContainer = new VBox(20);
        outerContainer.getStyleClass().add("event-root");
        outerContainer.setPadding(new Insets(20));
        outerContainer.setAlignment(Pos.TOP_CENTER);

        VBox contentWrapper = new VBox(25);
        contentWrapper.setMaxWidth(1100);
        contentWrapper.setPrefWidth(1100);
        contentWrapper.setAlignment(Pos.TOP_LEFT);

        contentWrapper.getChildren().addAll(
                buildToolbar(),
                buildEventSection(),
                buildRiwayatSection()
        );

        outerContainer.getChildren().addAll(
                buildNavbar(),
                contentWrapper
        );

        setContent(outerContainer);

        loadEvents(controller.getDaftarEvent());

        controller.getRiwayatPendaftaran().addListener((ListChangeListener<Event>) change -> {
            loadEvents(controller.filterKategori(searchField.getPromptText().contains("Cari") ? "SEMUA" : searchField.getText()));
            riwayatTable.refresh();
        });
    }

    private HBox buildNavbar() {
        HBox navbar = new HBox(20);
        navbar.setAlignment(Pos.CENTER_LEFT);
        navbar.getStyleClass().add("navbar-container");
        navbar.setMaxWidth(1100); 

        Button homeBtn = new Button("Home");
        homeBtn.getStyleClass().add("nav-btn");

        Button programBtn = new Button("Program");
        programBtn.getStyleClass().add("nav-btn-active");

        Button communityBtn = new Button("Community");
        communityBtn.getStyleClass().add("nav-btn");

        Button roadmapBtn = new Button("Road Map");
        roadmapBtn.getStyleClass().add("nav-btn");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button profileBtn = new Button();
        profileBtn.getStyleClass().add("profile-circle");

        navbar.getChildren().addAll(
            homeBtn,
            programBtn,
            communityBtn,
            roadmapBtn,
            spacer,
            profileBtn
        );

        return navbar;
    }

    private VBox buildToolbar() {
        VBox wrapper = new VBox(15);

        HBox topBar = new HBox(12);
        topBar.setAlignment(Pos.CENTER_LEFT);

        ImageView EventIcon = new ImageView(
        new Image(getClass().getResourceAsStream( "/images/event.png")));

        EventIcon.setFitWidth(24);
        EventIcon.setFitHeight(24);
        EventIcon.setPreserveRatio(true);

        HBox searchBox = new HBox(8);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.getStyleClass().add("search-box");

        searchField = new TextField();
        searchField.setPromptText("Cari Workshop...");
        searchField.getStyleClass().add("search-field");
        searchField.setPrefWidth(220);
        HBox.setHgrow(searchField, Priority.ALWAYS);

        Label searchIcon = new Label("🔍");
        searchIcon.getStyleClass().add("search-icon");

        searchBox.getChildren().addAll(searchField, searchIcon);

        searchField.textProperty().addListener(
                (obs, oldVal, newVal) -> loadEvents(controller.cariEvent(newVal))
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button roadmapFilterBtn = createFilterButton("Road Map");
        Button desainBtn       = createFilterButton("Desain");
        Button codingBtn       = createFilterButton("Coding");
        Button bisnisBtn       = createFilterButton("Bisnis");

        roadmapFilterBtn.getStyleClass().add("filter-btn-active");

        roadmapFilterBtn.setOnAction(e -> {
            resetActiveFilter(roadmapFilterBtn, desainBtn, codingBtn, bisnisBtn);
            loadEvents(controller.getDaftarEvent());
        });
        desainBtn.setOnAction(e -> {
            resetActiveFilter(desainBtn, roadmapFilterBtn, codingBtn, bisnisBtn);
            loadEvents(controller.filterKategori("DESAIN"));
        });
        codingBtn.setOnAction(e -> {
            resetActiveFilter(codingBtn, roadmapFilterBtn, desainBtn, bisnisBtn);
            loadEvents(controller.filterKategori("CODING"));
        });
        bisnisBtn.setOnAction(e -> {
            resetActiveFilter(bisnisBtn, roadmapFilterBtn, desainBtn, codingBtn);
            loadEvents(controller.filterKategori("BISNIS"));
        });

        topBar.getChildren().addAll(
                EventIcon,
                searchBox,
                spacer,
                roadmapFilterBtn,
                desainBtn,
                codingBtn,
                bisnisBtn
        );

        wrapper.getChildren().add(topBar);
        return wrapper;
    }

    private Button createFilterButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("filter-btn");
        return button;
    }

    private void resetActiveFilter(Button active, Button b1, Button b2, Button b3) {
        b1.getStyleClass().remove("filter-btn-active");
        b2.getStyleClass().remove("filter-btn-active");
        b3.getStyleClass().remove("filter-btn-active");
        active.getStyleClass().remove("filter-btn-active");
        active.getStyleClass().add("filter-btn-active");
    }


    private VBox buildEventSection() {
        eventGrid = new GridPane();
        eventGrid.setHgap(20);
        eventGrid.setVgap(20);
        eventGrid.setAlignment(Pos.TOP_CENTER);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        eventGrid.getColumnConstraints().addAll(col1, col2);

        VBox panel = new VBox(eventGrid);
        panel.getStyleClass().add("event-grid-panel");

        return panel;
    }

    private void loadEvents(ObservableList<Event> events) {
        eventGrid.getChildren().clear();

        int row = 0;
        int col = 0;

        for (Event event : events) {
            EventCard card = new EventCard(event, controller);
            eventGrid.add(card, col, row);

            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }
    }

    private VBox buildRiwayatSection() {
        VBox panel = new VBox(15);
        panel.getStyleClass().add("history-panel");

        HBox titleRow = new HBox(10);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        Label titleIcon = new Label("\uD83D\uDCC5");
        titleIcon.getStyleClass().add("history-title-icon");

        Label title = new Label("RIWAYAT PENDAFTARAN WORKSHOP KAMU (SIMULASI TABLEVIEW)");
        title.getStyleClass().add("history-title");

        titleRow.getChildren().addAll(titleIcon, title);


        riwayatTable = new TableView<>();
        riwayatTable.setPrefHeight(200); 
        riwayatTable.getStyleClass().add("history-table");
        riwayatTable.setPlaceholder(buildEmptyState());
        riwayatTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Event, String> idCol =
        new TableColumn<>("ID DAFTAR");
        idCol.setCellValueFactory(data ->
        new SimpleStringProperty(data.getValue().getRegistrationId()));

        TableColumn<Event, String> namaCol = new TableColumn<>("NAMA WORKSHOP");
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));

        TableColumn<Event, String> kategoriCol = new TableColumn<>("KATEGORI");
        kategoriCol.setCellValueFactory(new PropertyValueFactory<>("kategori"));

        TableColumn<Event, String> mentorCol = new TableColumn<>("MENTOR");
        mentorCol.setCellValueFactory(new PropertyValueFactory<>("mentor"));

        TableColumn<Event, String> statusCol =
        new TableColumn<>("STATUS");
        statusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        riwayatTable.getColumns().addAll(idCol, namaCol, kategoriCol, mentorCol, statusCol);
        riwayatTable.setItems(controller.getRiwayatPendaftaran());

        panel.getChildren().addAll(titleRow, riwayatTable);
        return panel;
    }

    private VBox buildEmptyState() {
        VBox emptyBox = new VBox(8);
        emptyBox.setAlignment(Pos.CENTER);

        Label emptyIcon = new Label("\uD83D\uDCE6");
        emptyIcon.getStyleClass().add("empty-icon");

        Label emptyText = new Label("Belum ada workshop yang kamu ikuti.");
        emptyText.getStyleClass().add("empty-text");

        emptyBox.getChildren().addAll(emptyIcon, emptyText);
        return emptyBox;
    }
}