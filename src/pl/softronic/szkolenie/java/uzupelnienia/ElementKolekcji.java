package pl.softronic.szkolenie.java.uzupelnienia;

public class ElementKolekcji {
    Integer id;
    String autor = "";
    String tytul = "";

    public ElementKolekcji(Integer id, String autor, String tytul) {
        this.id = id;
        this.autor = autor;
        this.tytul = tytul;

    }

    @Override
    public String toString() {
        return "ElementKolekcji{" +
                "id=" + id +
                ", autor='" + autor + '\'' +
                ", tytul='" + tytul + '\'' +
                '}';
    }
}
