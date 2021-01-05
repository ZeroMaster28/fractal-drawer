package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PropertiesLoader {

    private final String EXTENSION = ".properties";

    public Map<String, String> load(String fileName) {
        if(!fileName.contains(".")){
            fileName = fileName + "." + EXTENSION;
        }
        URL url = Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName));
        Map<String, String> properties = new HashMap<>();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()))){
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                if(currentLine.startsWith("#")) {
                    continue;
                }
                String[] el = currentLine.split("=");
                properties.put(el[0], el[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
