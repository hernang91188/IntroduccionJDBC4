package domain;

public class PersonaDTO {
    private int id_persona;
    private String nombre;
    private String apellido;
    private String mail;
    private int telefono;
    public PersonaDTO(){};
    public PersonaDTO(int id_persona){this.id_persona=id_persona;}
    public PersonaDTO(String nombre, String apellido, String mail, int telefono){
        this.nombre=nombre;
        this.apellido=apellido;
        this.mail=mail;
        this.telefono=telefono;
    };
    public PersonaDTO(int id_persona, String nombre, String apellido, String mail, int telefono){
        this.id_persona=id_persona;
        this.nombre=nombre;
        this.apellido=apellido;
        this.mail=mail;
        this.telefono=telefono;
    };

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" +
                "id_persona=" + id_persona +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
