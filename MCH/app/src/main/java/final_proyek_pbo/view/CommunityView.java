package final_proyek_pbo.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CommunityView extends ScrollPane {
    private VBox mainContainer;
    private FlowPane cardsGrid;
    private Button btnCariTim; 
    private Button btnCariProyek;    
    
    public CommunityView() {
        buildMainLayout();
        buildHeaderAndFilters();
        buildCardsGrid();
        loadDummyData();
        initRootConfiguration();
    }

    private void buildMainLayout() {
        mainContainer = new VBox();
        mainContainer.setSpacing(20);
        mainContainer.setPadding(new javafx.geometry.Insets(20));
        this.setContent(mainContainer);

    }
    private void buildHeaderAndFilters() {
        Label titleLabel = new Label("Community");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        mainContainer.getChildren().add(titleLabel);
        
        HBox buttonContainer = new HBox();
        btnCariTim = new Button("Cari Tim"); 
        btnCariProyek = new Button("Cari Proyek");
        buttonContainer.getChildren().addAll(btnCariTim, btnCariProyek);   
        mainContainer.getChildren().add(buttonContainer);
        
    }
    private void buildCardsGrid() {
        cardsGrid = new FlowPane();
        cardsGrid.setHgap(10);          
        cardsGrid.setVgap(10);
        mainContainer.getChildren().add(cardsGrid); 

    }
    private VBox createCommunityCard(String title, String description, String tag) {
        VBox card = new VBox();
        card.setSpacing(10);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true);
        
        Label tagLabel = new Label(tag);
        tagLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #888;");
        
        card.getChildren().addAll(titleLabel, descriptionLabel, tagLabel);
        return card;
    }
    private void loadDummyData() {
        for (int i = 1; i <= 10; i++) {
            String tagAktif = (i % 2 == 0) ? "[Cari_Tim]" : "[Cari_Proyek]";
            VBox card = createCommunityCard("Proyek " + i, "Deskripsi singkat untuk proyek " + i, tagAktif);
            cardsGrid.getChildren().add(card);
        }
    }
    public void initRootConfiguration() {
        this.setFitToWidth(true);
        this.setPadding(new Insets(10));
    	this.setStyle("-fx-background-color: #f0f0f0;");
    	cardsGrid.setPrefWidth(this.getWidth() - 40); 
    	cardsGrid.setPrefWrapLength(this.getWidth() - 40); 
    	this.widthProperty().addListener((obs, oldVal, newVal) -> {
        cardsGrid.setPrefWidth(newVal.doubleValue() - 40);
        cardsGrid.setPrefWrapLength(newVal.doubleValue() - 40);
        });
    }

    
}
