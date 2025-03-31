package model;

public class Person implements Identifiable<Integer> {
    private int id;
    private String nume;
    private String prenume;
    private int varsta;


    public Person(String nume, String prenume, int varsta){
        this.nume = nume;
        this.prenume = prenume;
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public void setId(Integer id) {
        this.id=id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Id=" + id + "Nume: " + nume + " Prenume:" + prenume + " Varsta:" + varsta ;
    }

}
