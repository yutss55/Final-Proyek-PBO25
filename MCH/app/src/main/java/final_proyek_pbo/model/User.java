package final_proyek_pbo.model;

public class User {
    private String nama;
    private String email;
    private String password;

    public User(String nama, String email, String password){
        this.nama = nama;
        this.email = email;
        this.password = password;

    }
    public String getNama(){
        return nama;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
}
