public class Station {
    private String adderssStreet;
    private City city;
    private double gegrLat;
    private double gegrLan;
    private String stationName;
    private int id;
    private Sensor sensors[];
    private AQIndex aqIndex;

    public Station() {
        this.adderssStreet = "";
        this.city = null;
        this.gegrLat = 0.0;
        this.gegrLan = 0.0;
        this.stationName = "";
        this.id = 0;
        this.sensors = null;
        this.aqIndex = null;
    }



    public Sensor[] getSensors() {
        return sensors;
    }

    public void setSensors(Sensor[] sensors) {
        this.sensors = sensors;
    }

    public void setAdderssStreet(String adderssStreet) {
        this.adderssStreet = adderssStreet;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setGegrLat(double gegrLat) {
        this.gegrLat = gegrLat;
    }

    public void setGegrLan(double gegrLan) {
        this.gegrLan = gegrLan;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setAqIndex(AQIndex aqIndex) {
        this.aqIndex = aqIndex;
    }

    public String getAdderssStreet() {
        return adderssStreet;
    }

    public City getCity() {
        return city;
    }

    public double getGegrLat() {
        return gegrLat;
    }

    public double getGegrLan() {
        return gegrLan;
    }

    public String getStationName() {
        return stationName;
    }

    public int getId() {
        return id;
    }



    public AQIndex getAqIndex() {
        return aqIndex;
    }
}
