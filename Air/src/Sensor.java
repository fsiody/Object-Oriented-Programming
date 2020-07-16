import java.util.Calendar;

public class Sensor  {
    private int id;
    private int stationID;
    private Param param;
    private Param params;
    //   private HashMap<String,Param> params;


    public void setParams(Param params) {
        this.params = params;
    }

    public Param getParams() {
        return params;
    }

    public Sensor() {
        this.id = 0;
        this.stationID = 0;
        this.param = null;
        this.params = null;
    }


    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public int getId() {
        return id;
    }

    public int getStationID() {
        return stationID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public boolean compare(Sensor b, Calendar date) {
        Sensor a=this;
        Double aVal = a.params.getVals().get(date);
        Double bVal = b.params.getVals().get(date);
        if (aVal <= bVal) return true;
        else return false;
    }



}
