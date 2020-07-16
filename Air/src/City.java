public class City {
    private int id;
    private String name;
    private Commune commune;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Commune getCommune() {
        return commune;
    }
}
