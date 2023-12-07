package avi.lod.tlodscripttools.Patching;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Util {


    public static String getItemNameFromId(int itemId){

        InputStream in = Util.class.getClassLoader().getResourceAsStream("csv/Item_IDs.csv");

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        CSVReader csvReader = new CSVReader(reader);
        List<String[]> allData = null;
        try {
            allData = csvReader.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return allData.get(itemId)[1];
    }


    /**
     * Returns label name of the main controller loop
     */
    public static String getMainControllerLabel(String script){
        List<Integer> yieldEntries = new ArrayList<>();
        int index = script.indexOf("yield");
        while(index != -1){
            yieldEntries.add(index);
            index = script.indexOf("yield", index + 1);
        }
        //System.out.println(yieldEntries);
        return "";
    }

    public static String addTextboxControllerNameless(String script){
        return script;
    }
    public static String addTextboxControllerWName(String script){
        return  script;
    }

    public static String parseMetaStringToString(String code){
        return code;
    }
}
