package avi.lod.tlodscripttools;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Preferences {
    public static Map<String,String> prefs = new HashMap<>();


    public static void savePrefs(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("settings.txt"))) {
            for(Map.Entry<String,String> entry: prefs.entrySet()){
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on your needs
        }
    }
    public static void loadPrefs(){
        try{
            File settings = new File("settings.txt");
            if(!settings.exists()){
                if(settings.createNewFile()){
                    return;
                }
            }
        }catch(IOException err){
            err.printStackTrace();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("settings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    prefs.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on your needs
        }
    }
}
