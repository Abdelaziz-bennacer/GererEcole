package fr.abdel.metier;

public class Adresse {

    int idAdresse;
    int numRue;
    String nomRue;
    int codePostale;
    String ville;
    String pays;

    public Adresse(){};
    public Adresse(int idAdresse, int numRue, String nomRue, int codePostale, String ville, String pays){
        super();
        this.idAdresse = idAdresse;
        this.numRue = numRue;
        this.nomRue = nomRue;
        this.codePostale = codePostale;
        this.ville = ville;
        this.pays = pays;
    };

    public Adresse(int numRue, String nomRue, int codePostale, String ville, String pays) {
        super();
        this.numRue = numRue;
        this.nomRue = nomRue;
        this.codePostale = codePostale;
        this.ville = ville;
        this.pays = pays;
    }



    public  int getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(int id) {
        this.idAdresse = idAdresse;
    }

    public int getNumRue() {
        return numRue;
    }

    public void setNumRue(int numRue) {
        this.numRue = numRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public int getCodePostale() {
        return codePostale;
    }

    public void setCodePostal(int codePostal) {
        this.codePostale = codePostale;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "idAdresse=" + idAdresse +
                ", numRue=" + numRue +
                ", nomRue='" + nomRue + '\'' +
                ", codePostal=" + codePostale +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                '}';
    }
}
