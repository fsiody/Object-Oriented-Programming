import java.util.Calendar;
import java.util.HashMap;

public class Param {
    private String paramName;
    private String paramFormula;
    private String paramCode;
    private int idParam;
    private Double lim;

    private String key;
    private Value values[];

    private HashMap<Calendar,Double> vals;

    public void setVals(){
        Parser parser = new Parser();
        this.vals=new HashMap<>();
        for(Value v:values){
            this.vals.put(parser.getDate(v.getDate()),v.getValue());
        }
    }

    public void setVals(HashMap<Calendar, Double> vals) {
        this.vals = vals;
    }

    Double getDateVal(Calendar date){
        return vals.get(date);
    }

    public Param() {
        this.paramName = "";
        this.paramFormula = "";
        this.paramCode = "";
        this.key = "";
        this.idParam = 0;
        this.values = null;
        this.vals=null;
        switch (paramCode){
          //  case ""
        }
    }

    public HashMap<Calendar, Double> getVals() {
        return vals;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getParamName() {
        return paramName;
    }

    public String getParamFormula() {
        return paramFormula;
    }

    public String getParamCode() {
        return paramCode;
    }

    public int getIdParam() {
        return idParam;
    }

    public Value[] getValues() {
        return values;
    }

    public void setValues(Value[] values) {
        this.values = values;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public void setParamFormula(String paramFormula) {
        this.paramFormula = paramFormula;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public void setIdParam(int idParam) {
        this.idParam = idParam;
    }

}
