package final_proyek_pbo.view; // ⬅️ Sesuaikan nama package depannya

import final_proyek_pbo.model.JalurKarir; 
import final_proyek_pbo.model.Soal;       
import final_proyek_pbo.model.Tahapan;    

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RoadmapView extends BorderPane {

    private VBox roadmapContainer;    
    private MediaPlayer activeMediaPlayer;

   
    public RoadmapView() {
        this.setStyle("-fx-background-color: #0d1117;");

        
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15, 20, 10, 20));
        topBar.setStyle("-fx-background-color: #161b22; -fx-border-color: #30363d; -fx-border-width: 0 0 1 0;");

        Button btnBack = new Button("← Kembali ke Menu Utama");
        btnBack.setStyle("-fx-background-color: transparent; -fx-text-fill: #c9d1d9; -fx-font-weight: bold; -fx-cursor: hand;");
        
        
        btnBack.setOnAction(e -> {
            System.out.println("Tombol kembali diklik (Hubungkan dengan navigasi utama)");
        });

        topBar.getChildren().add(btnBack);
        this.setTop(topBar);

       
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(30));
        
        Label titleLabel = new Label(" Skill Roadmap & Pengembangan Kompetensi");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.WHITE);

        Label subtitleLabel = new Label("Rencanakan jalur karier kalian, kuasai keahlian baru, dan temukan workshop di MCH yang sesuai untuk menyelesaikannya.");
        subtitleLabel.setTextFill(Color.web("#c9d1d9"));
        subtitleLabel.setWrapText(true);

        
        VBox comboContainer = new VBox(10);
        comboContainer.setStyle("-fx-background-color: #161b22; -fx-padding: 15; -fx-background-radius: 8; -fx-border-color: #30363d; -fx-border-radius: 8;");
        
        Label comboLabel = new Label("PILIH MINAT KARIR KAMU\nPeta kurikulum belajar akan berubah menyesuaikan industri yang kamu tuju:");
        comboLabel.setTextFill(Color.web("#8b949e"));
        comboLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        
        ComboBox<JalurKarir> karirComboBox = new ComboBox<>();
        karirComboBox.setStyle("-fx-background-color: #0d1117; -fx-text-fill: white; -fx-border-color: #30363d; -fx-border-radius: 4;");
        karirComboBox.setPrefWidth(350);

        karirComboBox.getItems().addAll(siapkanDataKarir());
        
        comboContainer.getChildren().addAll(comboLabel, karirComboBox);

        

        roadmapContainer = new VBox(0);

        karirComboBox.setOnAction(e -> {
            JalurKarir pilihan = karirComboBox.getValue();
            if (pilihan != null) {
                tampilkanTahapan(pilihan.getDaftarTahapan());
            }
        });

        mainContent.getChildren().addAll(titleLabel, subtitleLabel, comboContainer, roadmapContainer);
        
        
        
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setStyle("-fx-background: #0d1117; -fx-background-color: transparent;");
        scrollPane.setFitToWidth(true);
        scrollPane.setBorder(Border.EMPTY);
        
        this.setCenter(scrollPane);

        karirComboBox.getSelectionModel().selectFirst();
    }

    private void tampilkanTahapan(List<Tahapan> tahapanList) {
        roadmapContainer.getChildren().clear();
        
        for (int i = 0; i < tahapanList.size(); i++) {
            Tahapan t = tahapanList.get(i);
            roadmapContainer.getChildren().add(buatKartuTahapan(t));
            
            if (i < tahapanList.size() - 1) {
                HBox arrowContainer = new HBox();
                arrowContainer.setPadding(new Insets(5, 0, 5, 55));
                
                Polygon arrow = new Polygon(0.0, 0.0, 12.0, 0.0, 6.0, 8.0);
                arrow.setFill(Color.web("#30363d"));
                
                arrowContainer.getChildren().add(arrow);
                roadmapContainer.getChildren().add(arrowContainer);
            }
        }
    }

    private VBox buatKartuTahapan(Tahapan tahapan) {
        VBox card = new VBox(12);
        card.setStyle("-fx-background-color: #161b22; -fx-padding: 20; -fx-background-radius: 8; -fx-border-color: #30363d; -fx-border-radius: 8;");

        HBox headerBox = new HBox(15);
        headerBox.setAlignment(Pos.CENTER_LEFT);

        StackPane numberPane = new StackPane();
        Circle circle = new Circle(16, Color.web("#161b22"));
        circle.setStroke(Color.web("#30363d"));
        circle.setStrokeWidth(1.5);
        Label numberLabel = new Label(String.valueOf(tahapan.getNomor()));
        numberLabel.setTextFill(Color.WHITE);
        numberLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        numberPane.getChildren().addAll(circle, numberLabel);

        Label titleLabel = new Label("Tahap " + tahapan.getNomor() + ": " + tahapan.getJudul());
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.WHITE);

        headerBox.getChildren().addAll(numberPane, titleLabel);

        Label descLabel = new Label(tahapan.getDeskripsi());
        descLabel.setTextFill(Color.web("#c9d1d9"));
        descLabel.setPadding(new Insets(0, 0, 0, 48));
        descLabel.setWrapText(true);

        HBox actionBox = new HBox(15);
        actionBox.setAlignment(Pos.CENTER_LEFT);
        actionBox.setPadding(new Insets(5, 0, 0, 48));

        Label actionInfo = new Label("Aksi: " + tahapan.getInfoAksi());
        actionInfo.setStyle("-fx-background-color: #21262d; -fx-padding: 6 12; -fx-background-radius: 4;");
        actionInfo.setTextFill(Color.web("#8b949e"));

        Button actionBtn = new Button(tahapan.getTextTombol());
        actionBtn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-weight: bold; -fx-background-radius: 4; -fx-padding: 6 12; -fx-cursor: hand;");
        
        actionBtn.setOnAction(e -> {
            String namaFile = tahapan.getNamaFileVideo(); 
            if (namaFile != null && namaFile.endsWith(".mp4")) {
                bukaVideoPlayer(tahapan.getJudul(), namaFile);
            }
        });

        Button btnKuis = new Button(" Mulai Kuis");
        btnKuis.setStyle("-fx-background-color: #30363d; -fx-text-fill: #c9d1d9; -fx-font-weight: bold; -fx-background-radius: 4; -fx-padding: 6 12; -fx-cursor: hand;");

        btnKuis.setOnAction(e -> {
            if (!tahapan.getDaftarSoal().isEmpty()) {
                bukaJendelaKuis(tahapan.getJudul(), tahapan.getDaftarSoal());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Belum ada kuis untuk tahapan ini.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        actionBox.getChildren().addAll(actionInfo, actionBtn, btnKuis);
        card.getChildren().addAll(headerBox, descLabel, actionBox);

        return card;
    }

    private void bukaVideoPlayer(String judulTahapan, String namaFileVideo) {
        try {
            if (activeMediaPlayer != null) {
                activeMediaPlayer.stop();
            }

            URL resourceUrl = getClass().getResource("/video/" + namaFileVideo);
            
            if (resourceUrl == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("File Tidak Ditemukan");
                alert.setContentText("File video '" + namaFileVideo + "' tidak ditemukan di folder resources.");
                alert.showAndWait();
                return;
            }

            Media media = new Media(resourceUrl.toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            activeMediaPlayer = mediaPlayer;
            
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaView.setFitWidth(800);
            mediaView.setPreserveRatio(true);

            Slider timeSlider = new Slider();
            timeSlider.setPrefWidth(600);
            
            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                if (!timeSlider.isValueChanging() && media.getDuration() != null) {
                    timeSlider.setValue(newValue.toSeconds() / media.getDuration().toSeconds() * 100);
                }
            });
            
            timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (timeSlider.isValueChanging() && media.getDuration() != null) {
                    mediaPlayer.seek(media.getDuration().multiply(newValue.doubleValue() / 100.0));
                }
            });

            Label volumeLabel = new Label("🔊");
            volumeLabel.setTextFill(Color.WHITE);
            
            Slider volumeSlider = new Slider(0, 1, 0.5);
            volumeSlider.setPrefWidth(100);
            mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty());

            HBox sliderBox = new HBox(10, timeSlider, volumeLabel, volumeSlider);
            sliderBox.setAlignment(Pos.CENTER);
            sliderBox.setPadding(new Insets(10, 0, 5, 0));

            Button btnPlay = new Button("▶ PLAY");
            btnPlay.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;");
            
            Button btnPause = new Button("⏸ PAUSE");
            btnPause.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;");
            
            Button btnStop = new Button("⏹ STOP");
            btnStop.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;");
            
            Button btnClose = new Button("✕ CLOSE");
            btnClose.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;");

            HBox controlBtns = new HBox(15);
            controlBtns.setAlignment(Pos.CENTER);
            controlBtns.getChildren().addAll(btnPlay, btnPause, btnStop, btnClose);

            VBox controlBox = new VBox(5);
            controlBox.setAlignment(Pos.CENTER);
            controlBox.setPadding(new Insets(15));
            controlBox.setStyle("-fx-background-color: #0d1117;");
            controlBox.getChildren().addAll(sliderBox, controlBtns);

            VBox videoRoot = new VBox(mediaView, controlBox);
            videoRoot.setStyle("-fx-background-color: #000000;");
            videoRoot.setAlignment(Pos.CENTER);

            Stage videoStage = new Stage();
            videoStage.setTitle(judulTahapan + " - Video Player");
            videoStage.setScene(new Scene(videoRoot));

            btnPlay.setOnAction(e -> mediaPlayer.play());
            btnPause.setOnAction(e -> mediaPlayer.pause());
            btnStop.setOnAction(e -> mediaPlayer.stop());
            btnClose.setOnAction(e -> {
                mediaPlayer.stop();
                videoStage.close();
            });

            videoStage.setOnCloseRequest(e -> mediaPlayer.stop());
            videoStage.show();
            mediaPlayer.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bukaJendelaKuis(String judulTahapan, List<Soal> daftarSoal) {
        Stage kuisStage = new Stage();
        kuisStage.setTitle("Kuis Pemahaman: " + judulTahapan);

        VBox layoutKuis = new VBox(20);
        layoutKuis.setPadding(new Insets(25));
        layoutKuis.setStyle("-fx-background-color: #0d1117;");
        layoutKuis.setAlignment(Pos.TOP_LEFT);

        Label titleKuis = new Label(" Uji Kompetensi: " + judulTahapan);
        titleKuis.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleKuis.setTextFill(Color.WHITE);
        layoutKuis.getChildren().add(titleKuis);

        List<ToggleGroup> kumpulanGrupPilihan = new ArrayList<>();

        for (int i = 0; i < daftarSoal.size(); i++) {
            Soal soal = daftarSoal.get(i);

            VBox boxSoal = new VBox(10);
            boxSoal.setStyle("-fx-background-color: #161b22; -fx-padding: 15; -fx-background-radius: 6; -fx-border-color: #30363d; -fx-border-radius: 6;");

            Label lblPertanyaan = new Label((i + 1) + ". " + soal.getPertanyaan());
            lblPertanyaan.setTextFill(Color.WHITE);
            lblPertanyaan.setFont(Font.font("System", FontWeight.SEMI_BOLD, 13));
            lblPertanyaan.setWrapText(true);
            boxSoal.getChildren().add(lblPertanyaan);

            ToggleGroup grupPilihan = new ToggleGroup();
            kumpulanGrupPilihan.add(grupPilihan);

            for (int j = 0; j < soal.getPilihan().length; j++) {
                RadioButton rb = new RadioButton(soal.getPilihan()[j]);
                rb.setTextFill(Color.web("#c9d1d9"));
                rb.setToggleGroup(grupPilihan);
                rb.setUserData(j); 
                boxSoal.getChildren().add(rb);
            }

            layoutKuis.getChildren().add(boxSoal);
        }

        Button btnSubmit = new Button("Kirim Jawaban");
        btnSubmit.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;");
        
        btnSubmit.setOnAction(e -> {
            int skorBenar = 0;
            boolean semuaDiisi = true;

            for (int i = 0; i < daftarSoal.size(); i++) {
                ToggleGroup grup = kumpulanGrupPilihan.get(i);
                if (grup.getSelectedToggle() == null) {
                    semuaDiisi = false;
                    break;
                } else {
                    int jawabanUser = (int) grup.getSelectedToggle().getUserData();
                    if (jawabanUser == daftarSoal.get(i).getIndeksJawabanBenar()) {
                        skorBenar++;
                    }
                }
            }

            if (!semuaDiisi) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Harap jawab semua pertanyaan terlebih dahulu!", ButtonType.OK);
                alert.showAndWait();
            } else {
                double nilaiAkhir = ((double) skorBenar / daftarSoal.size()) * 100;
                
                Alert infoHasil = new Alert(Alert.AlertType.INFORMATION);
                infoHasil.setTitle("Hasil Kuis");
                infoHasil.setHeaderText(nilaiAkhir >= 70 ? " Selamat, Kamu Lulus!" : "❌ Maaf, Kamu Belum Lulus");
                infoHasil.setContentText("Skor Kamu: " + String.format("%.0f", nilaiAkhir) + "\nJawaban Benar: " + skorBenar + " dari " + daftarSoal.size() + " soal.");
                infoHasil.showAndWait();

                if (nilaiAkhir >= 70) {
                    kuisStage.close();
                }
            }
        });

        layoutKuis.getChildren().add(btnSubmit);

        ScrollPane scrollPane = new ScrollPane(layoutKuis);
        scrollPane.setStyle("-fx-background: #0d1117; -fx-background-color: transparent;");
        scrollPane.setFitToWidth(true);

        Scene sceneKuis = new Scene(scrollPane, 550, 500);
        kuisStage.setScene(sceneKuis);
        kuisStage.show();
    }

    
    private List<JalurKarir> siapkanDataKarir() {
        List<JalurKarir> daftarKarir = new ArrayList<>();

        
        JalurKarir frontendDev = new JalurKarir("Frontend Developer (Web & UI)");
        
        Tahapan ft1 = new Tahapan(1, "Dasar HTML, CSS, & JavaScript", "Pelajari struktur web dasar.", "Belajar Mandiri", "🎬 Tonton Video Dasar", "Belajar HTML & CSS untuk PEMULA - Full Lengkap.mp4");
        ft1.tambahSoal(new Soal("Apa kepanjangan resmi dari HTML?", new String[]{"Hyper Text Markup Language", "High Tech Modern Language", "Hyperlink and Text Management"}, 0));
        ft1.tambahSoal(new Soal("Tag HTML mana yang digunakan untuk membuat judul (heading) terbesar?", new String[]{"<heading>", "<h6>", "<h1>"}, 2));
        frontendDev.tambahTahapan(ft1);
        
        Tahapan ft2 = new Tahapan(2, "Framework Modern (React/Vue)", "Kuasai komponen UI modern.", "Belajar Mandiri", "🎬 Tonton Video Framework", "Ngerti Framework Dalam 3 Menit.mp4");
        ft2.tambahSoal(new Soal("Apa fungsi utama dari Framework Frontend seperti React?", new String[]{"Mengelola database server", "Mempermudah pembuatan UI berbasis komponen", "Mengamankan jaringan website"}, 1));
        frontendDev.tambahTahapan(ft2);
        
        Tahapan ft3 = new Tahapan(3, "Pemrograman Javascript Lanjutan", "Perdalam logika asinkronus javascript.", "Belajar Mandiri", "🎬 Tonton Video JS Lanjutan", "Belajar Dasar Pemrograman Javascript 1 Jam.mp4");
        ft3.tambahSoal(new Soal("Fitur JavaScript apa yang digunakan untuk menangani proses asinkronus modern?", new String[]{"var dan let", "if dan else", "async dan await"}, 2));
        frontendDev.tambahTahapan(ft3);
        
        daftarKarir.add(frontendDev);


        
        JalurKarir backendDev = new JalurKarir("Backend Developer (Server & Database)");
        
        Tahapan bt1 = new Tahapan(1, "Dasar Node.js Runtime", "Memahami arsitektur server side menggunakan Javascript lingkungan Node.", "Belajar Mandiri", "🎬 Tonton Video Node.js", "Node.js.mp4");
        bt1.tambahSoal(new Soal("Node.js memungkinkan kita menjalankan JavaScript di mana?", new String[]{"Di dalam browser", "Di lingkungan Server side", "Di dalam mesin database"}, 1));
        backendDev.tambahTahapan(bt1);
        
        Tahapan bt2 = new Tahapan(2, "Manajemen Relational Database", "Kuasai query dasar tabel menggunakan MySQL Database.", "Belajar Mandiri", "🎬 Tonton Video SQL", "Tutorial MySQL Database (Bahasa Indonesia).mp4");
        bt2.tambahSoal(new Soal("Perintah SQL manakah yang dipakai untuk mengambil data dari tabel?", new String[]{"SELECT", "INSERT", "DELETE"}, 0));
        backendDev.tambahTahapan(bt2);
        
        Tahapan bt3 = new Tahapan(3, "Implementasi RESTful API", "Membuat endpoint data terstruktur aman.", "Belajar Mandiri", "🎬 Tonton Video API", "API.mp4");
        bt3.tambahSoal(new Soal("HTTP Method mana yang umumnya digunakan untuk membuat data baru pada REST API?", new String[]{"GET", "POST", "DELETE"}, 1));
        backendDev.tambahTahapan(bt3);
        
        daftarKarir.add(backendDev);

        

        JalurKarir dataScience = new JalurKarir("Data Scientist (Analisis & AI)");
        
        Tahapan dt1 = new Tahapan(1, "Dasar Pemrograman Python", "Sintaksis dasar variabel, perulangan, dan koleksi data Python.", "Belajar Mandiri", "🎬 Tonton Video Python", "Python.mp4");
        dt1.tambahSoal(new Soal("Bagaimana cara menulis komentar di bahasa pemrograman Python?", new String[]{"// Ini komentar", "# Ini komentar", "/* Ini komentar */"}, 1));
        dataScience.tambahTahapan(dt1);
        
        Tahapan dt2 = new Tahapan(2, "Visualisasi Data Grafis", "Membuat insight data menarik menggunakan library Matplotlib.", "Belajar Mandiri", "🎬 Tonton Video Visualisasi", "Membuat Visualisasi Grafis Menggunakan Matplotlib #34 - Belajar Python Untuk AI & Data Science.mp4");
        dt2.tambahSoal(new Soal("Library Python apa yang populer digunakan khusus untuk visualisasi grafis?", new String[]{"Matplotlib", "Django", "Flask"}, 0));
        dataScience.tambahTahapan(dt2);
        
        Tahapan dt3 = new Tahapan(3, "Machine Learning Dasar", "Mengenal algoritma dasar kecerdasan buatan.", "Belajar Mandiri", "🎬 Tonton Video ML", "ML Dasar.mp4");
        dt3.tambahSoal(new Soal("Apa yang membedakan Supervised Learning dengan Unsupervised Learning?", new String[]{"Penggunaan database SQL", "Adanya data berlabel sebagai target belajar", "Kecepatan proses server"}, 1));
        dataScience.tambahTahapan(dt3);
        
        daftarKarir.add(dataScience);

        return daftarKarir;
    }
}