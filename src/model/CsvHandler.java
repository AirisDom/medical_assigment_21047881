package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvHandler {

    public static List<String[]> readCsv(String fileName) {
        List<String[]> records = new ArrayList<>();
        File file = null;
        
        try {
            file = new File("resources/" + fileName);
            if (!file.exists()) {
                file = new File(fileName);
            }
            
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + fileName);
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] fields = parseCsvLine(line);
                records.add(fields);
            }
            
            reader.close();
            System.out.println("Successfully read " + records.size() + " lines from " + fileName);
            
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + fileName);
            System.err.println("Searched in: resources/" + fileName + " and " + fileName);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error: IOException occurred while reading " + fileName);
            e.printStackTrace();
        }
        
        return records;
    }
    
    private static String[] parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?=,|$)");
        Matcher matcher = pattern.matcher(line);
        
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                fields.add(matcher.group(1));
            } else {
                fields.add(matcher.group(2));
            }
        }
        
        return fields.toArray(new String[0]);
    }
    
    public static void appendLine(String fileName, String csvLine) {
        File file = null;
        
        try {
            file = new File("resources/" + fileName);
            if (!file.exists()) {
                file = new File(fileName);
            }
            
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            FileWriter writer = new FileWriter(file, true);
            writer.write(csvLine);
            
            if (!csvLine.endsWith("\n")) {
                writer.write("\n");
            }
            
            writer.close();
            System.out.println("Successfully appended line to " + fileName);
            
        } catch (IOException e) {
            System.err.println("Error: IOException occurred while writing to " + fileName);
            e.printStackTrace();
        }
    }
    
    public static String escapeCsvField(String field) {
        if (field == null) {
            return "";
        }
        
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            field = field.replace("\"", "\"\"");
            return "\"" + field + "\"";
        }
        
        return field;
    }
    
    public static String createCsvLine(String... fields) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(escapeCsvField(fields[i]));
        }
        
        return sb.toString();
    }
}
