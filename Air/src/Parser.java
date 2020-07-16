import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static java.lang.Math.abs;

//import jdk.incubator.http.HttpClient;
//import java.util.*;
//import java.util.IllegalFormatException;

public class Parser {

    private HashMap<Integer,Station> stations;

    public HashMap<Integer, Station> getStations() {
        return stations;
    }

    public void setStations(HashMap<Integer, Station> stations) {
        this.stations = stations;
    }

    public Parser(HashMap<Integer, Station> stations) {
        this.stations = stations;
    }

    public Parser() {
    }

    String getGson(int operation, String arg) throws IllegalArgumentException {
        URL url;
        HttpURLConnection con = null;
        String mes = "";
        try {
            String urlAllStations = "http://api.gios.gov.pl/pjp-api/rest/station/findAll";
            String urlSensors = "http://api.gios.gov.pl/pjp-api/rest/station/sensors/";
            //{stationID}
            String urlSensorsData = "http://api.gios.gov.pl/pjp-api/rest/data/getData/";
            // {SensorID}
            String urlAQID = "http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/";
            //{stationId}
            url = new URL(urlAllStations);
            switch (operation) {
                case 1: {
                    break;
                }
                case 2: {
                    url = new URL(urlSensors + "//" + arg);
                    break;
                }
                case 3: {
                    url = new URL(urlSensorsData + "//" + arg);
                    break;
                }
                case 4: {
                    url = new URL(urlAQID + "//" + arg);
                    break;
                }
                default: {
                    mes = "Illegal operating name: [" + Integer.toString(operation) +
                            "], mast be 1..4";
                    //throw new IllegalArgumentException(mes);
                }
            }
            con = (HttpURLConnection) url.openConnection();
  //          con.setDoOutput(true);
  //          con.setInstanceFollowRedirects(false);
  //          con.setRequestMethod("REST");
           // Gson gson1 = new Gson().toJson(new InputStreamReader(con.getInputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            String result="";
            StringBuffer text = new StringBuffer();
            while ((line = in.readLine()) != null) {
                text.append(line);
                result+=line;
            }
            in.close();
            if (mes.length() > 0) {
                throw new IllegalArgumentException(mes);
            }
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("bad URL))))))))))))))");
            //new URL() failed
        } catch (IOException e) {
            //OpeConnection() failed
            e.printStackTrace();
            System.out.println("IOEXCEPTION((((((((");

        } catch (IllegalArgumentException a) {
            System.out.println(a.getMessage() + "aaaaaaaaaaaaaargument!!!!!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) con.disconnect();
          //  String errorMessage = new StringBuffer();
           // errorMessage.append("Parsing error");

        }
        return "Parsing error";
    }

    Calendar getDate(int year, int mounth,int day, int hour){
        Calendar dateC = new GregorianCalendar(year,mounth-1,day);
        dateC.set(Calendar.HOUR_OF_DAY,hour);
        return dateC;
    }

    Calendar getDate(String dateS){
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar dateC = Calendar.getInstance();
        try {
            dateC.setTime(df.parse(dateS));
        }catch (ParseException p){
            System.out.println("Wrong date format "+ dateS);
        }
        return dateC;

    }

    Station getStation(String stationName){
        try {
                for (Integer key : stations.keySet()) {
                    Station s=stations.get(key);
                    if (s.getStationName().equals(stationName)) {
                        return s;
                    }
                }
                throw new IllegalArgumentException("Wrong station name ["+stationName+"]\n");

        }catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
            return null;
        }
    }

    Sensor getSensor(int stationId, String paramKey)throws IllegalArgumentException{
        Sensor resultSensor=null;
        try {
                for (Sensor s : stations.get(stationId).getSensors()) {
                    if (s.getParam().getParamCode().equals(paramKey)) {
                         resultSensor = s;
                    }
                }
                if(resultSensor==null) throw new IllegalArgumentException("Sensor on station #"+
                        stationId + "for param "+ paramKey+" not found");
        }catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
        }
        return resultSensor;
    }

    String getIdQuality(String stationName) throws IllegalArgumentException{
        String result ="";
        try {
            String mes="";
                AQIndex qaid=getStation(stationName).getAqIndex();
                if (qaid != null) {
                    result += "Quality ID on station " + stationName +
                            " = " + qaid.getStIndexLevel().getId() + " (" +
                            qaid.getStIndexLevel().getIndexLevelName() + ")";
                    return result;
                } else throw new IllegalArgumentException("QualityId in station " + stationName + " is null");
        }catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
        }finally {
            return result;
        }
    }

    String getParam(String paramKey,String dateS,String stationName)throws IllegalArgumentException{
        Station station=getStation(stationName);
        Sensor paramSensor=null;
        Double param =-1.0;
        paramSensor=getSensor(station.getId(),paramKey);
        Calendar dateD=getDate(dateS);
        try{
            param=paramSensor.getParams().getDateVal(dateD);
            if(param<0 || param==null) throw new IllegalArgumentException("Param "+paramKey+"  for date string: "+dateS+
                    "for date Date "+dateD+" not found");

        }catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
        }
        return "Param of "+paramKey+" ("+paramSensor.getParam().getParamName()+")"+
                " at Station "+stationName+" at "+dateD.getTime() +" = "+ Double.toString(param);
    }

    String getParamAvg(String paramKey, String startDateS, String endDateS, String stationName){
        Double avg=0.0;
        Double sum=0.0;
        int paramsSummed=0;
        Calendar statD = getDate(startDateS);
        Calendar endD = getDate(endDateS);
        Station station=getStation(stationName);
        Sensor sensor=null;
        sensor=getSensor(station.getId(),paramKey);
        try{
                for(Calendar testDateD: sensor.getParams().getVals().keySet()  ){
                    Double testVal=sensor.getParams().getVals().get(testDateD);
                    if(!testDateD.before(statD) && !testDateD.after(endD) && testVal!=null){
                        sum+=testVal;
                        paramsSummed++;
                    }
                }
                if(sum==0) throw new IllegalArgumentException("There is no data for period "+ statD.toString()+" - "+
                        endD.toString()+" for station "+stationName);
        }catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
        }

        return "Average of a param "+paramKey  +" for period "+ startDateS+" - "+endDateS+" = "+(sum/paramsSummed);
    }














    String getParamNameWithMaxDelta(int hour, String stationName){
        Station station=getStation(stationName);
        int stationId=station.getId();
        Double delta=0.0;
        Double maxDelta=0.0;
        String maxDeltaParam="";
        try {
            String sensorsStr = getGson(2, Integer.toString(stationId));
            if (!sensorsStr.equals("Parsing error")) {
                 station.setSensors(new Gson().fromJson(sensorsStr, Sensor[].class));
                for (Sensor s : station.getSensors()) {
                    String sensorParamsStr = getGson(3, Integer.toString(s.getId()));
                    s.setParams(new Gson().fromJson(sensorParamsStr,Param.class));
                    Double startParam=null;
                    for(Value v : s.getParams().getValues()){
                        Calendar vDate=getDate(v.getDate());
                        Calendar today = Calendar.getInstance();
                        if(today.get(Calendar.YEAR)==vDate.get(Calendar.YEAR) &&
                                today.get(Calendar.MONTH)==vDate.get(Calendar.MONTH) &&
                                today.get(Calendar.DATE)==vDate.get(Calendar.DATE)) {
                            if (vDate.get(Calendar.HOUR_OF_DAY) >= hour) {
                                if(startParam==null) startParam=v.getValue();
                                else{
                                    delta+=abs(v.getValue()-startParam);
                                    startParam=v.getValue();
                                }

                            }
                        }

                    }
                    if(delta>maxDelta){
                        maxDelta=delta;
                        maxDeltaParam=s.getParam().getParamName();
                    }
                    delta=0.0;
                }
                if(maxDelta==null) throw new IllegalArgumentException("Sensor on station "+ stationName+" #"+
                        stationId +" not found");
            }
        }catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
            //поробуй снова этц оптион и предложи ввести другие аргументы!!
        }

        return "Param with max delta today is "+maxDeltaParam+ " delta="+maxDelta;
    }

    void bubbleSort(Sensor[] sensors,Calendar date){
        int n=sensors.length;
        Sensor temp=null;
        for(int i = 0; i<n;i++){
            for(int j=1;j<(n-i);j++){
                if(sensors[j-1].compare(sensors[j],date)){
                    temp=sensors[j-1];
                    sensors[j-1]=sensors[j];
                    sensors[j]=temp;
                }
            }
        }
    }

    Sensor[] getParamsRank(int year, int mounth, int day, int hour, String stationName){
        Station station = getStation(stationName);
        try {
            String sensorsStr = getGson(2, Integer.toString(station.getId()));
            if (!sensorsStr.equals("Parsing error")) {
                station.setSensors(new Gson().fromJson(sensorsStr, Sensor[].class));
                for (Sensor s : station.getSensors()) {
                    String sensorParamsStr = getGson(3, Integer.toString(s.getId()));
                    s.setParams(new Gson().fromJson(sensorParamsStr, Param.class));
                }
            }
        }catch (IllegalArgumentException a){
            System.out.println(a.getMessage());
            //поробуй снова этц оптион и предложи ввести другие аргументы!!
        }
        Calendar date = getDate(year,mounth,day,hour);
        int length=station.getSensors().length;
        Sensor sensors[]=new Sensor[length];
        int i=0;
        for(Sensor s : station.getSensors()){
            sensors[i]=s;
            i++;
        }
        bubbleSort(sensors,date);
        return sensors;
    }

}
