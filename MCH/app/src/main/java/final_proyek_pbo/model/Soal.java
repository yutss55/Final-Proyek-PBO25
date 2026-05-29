package final_proyek_pbo.model;

public class Soal {
    private String pertanyaan;
    private String[] pilihan;
    private int indeksJawabanBenar;

    public Soal(String pertanyaan, String[] pilihan, int indeksJawabanBenar) {
        this.pertanyaan = pertanyaan;
        this.pilihan = pilihan;
        this.indeksJawabanBenar = indeksJawabanBenar;
    }

    public String getPertanyaan() { return pertanyaan; }
    public String[] getPilihan() { return pilihan; }
    public int getIndeksJawabanBenar() { return indeksJawabanBenar; }
}