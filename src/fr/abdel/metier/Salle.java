package fr.abdel.metier;

public class Salle {
    private int id;
    private String code;
    private String libelle;

    public Salle(){
        super();
    }


    public Salle(int id, String code, String libelle){
        super();
        this.id = id;
        this.code = code;
        this.libelle = libelle;
    }

    public Salle(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
