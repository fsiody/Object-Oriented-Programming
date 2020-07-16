import com.google.gson.Gson;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.HashMap;


public class main {


    public static void main(String[] args) {

        Options airOptions = new Options();
        Option airQualityID = new Option("q","airQuality",true,"Displays the QualityID of air on this station");



        //ComandLine c = new CommandLine;
        Parser parser=new Parser(null);
        String stationsStr = parser.getGson(1, "").toString();





        HashMap<Integer,Station> stations=new HashMap<>();
      //  stations=new Gson().fromJson(stationsStr,stations.getClass());

        parser.setStations(stations);




       Station stationsA[]=null;
        if (!stationsStr.equals("Parsing error")) {
            stationsA = new Gson().fromJson(stationsStr, Station[].class);
            for(Station s : stationsA){
                stations.put(s.getId(),s);
                String aqIndex = parser.getGson(4, Integer.toString(s.getId())).toString();
                s.setAqIndex(new Gson().fromJson(aqIndex,AQIndex.class));
                String sensorsStr = parser.getGson(2, Integer.toString(s.getId()));
                if (!sensorsStr.equals("Parsing error")) {
                    s.setSensors(new Gson().fromJson(sensorsStr, Sensor[].class));
                    for (Sensor sens : s.getSensors()) {
                        String sensorParamsStr = parser.getGson(3, Integer.toString(sens.getId()));
                        sens.setParams(new Gson().fromJson(sensorParamsStr, Param.class));
                        sens.getParams().setVals();
                    }
                }
            }
        }else System.out.println(stationsStr);




        //airQuality
        System.out.println(new Parser().getIdQuality("Działoszyn"));




        System.out.println(new Parser().getParam("PM10","2019-01-20 15:00:00","Działoszyn"));
       System.out.println(new Parser().getParamAvg("PM10","2019-01-20 15:00:00","2019-01-20 20:00:00","Działoszyn"));
    //   System.out.println(new Parser().getParamNameWithMaxDelta(12,"Działoszyn"));

    }


}
   // System.out.println()
    // new Parser().getIdQuality();





