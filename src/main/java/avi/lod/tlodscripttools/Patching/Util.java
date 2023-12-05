package avi.lod.tlodscripttools.Patching;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static File itemFile = new File("Item_IDs.csv");
    public static String getItemNameFromId(int itemId){

        FileReader filereader = null;
        try {
            filereader = new FileReader(itemFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        CSVReader csvReader = new CSVReader(filereader);
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
        System.out.println(yieldEntries);
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
