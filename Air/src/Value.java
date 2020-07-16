public class Value {
    private String date;
    private Double value;

    public Value(String date, Double value) {
        this.date = "";
        this.value = null;
    }

    public String getDate() {
        return date;
    }

    public Double getValue() {
        return value;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
