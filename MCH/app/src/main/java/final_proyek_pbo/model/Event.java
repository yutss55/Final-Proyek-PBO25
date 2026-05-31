package final_proyek_pbo.model;

public class Event extends EntitasKreatif {

    public Event(String nama) {
        super(nama);
    }

    @Override
    public String getDeskripsiPeran() {
        return "Event Kreatif";
    }
}