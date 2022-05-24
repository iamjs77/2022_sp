package test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.lang.annotation.ElementType;

public class ValueType {
    public static void main(String[] args) {
        String filePath = "sample.json";
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(filePath));
            JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);

            for(String key : jsonObj.keySet() ) {
                StringBuffer sb = new StringBuffer();
                sb.append("Key : ").append(key).append(" / ").append("Value Type : ");

                JsonElement element = jsonObj.get(key);

                if( element.isJsonPrimitive() ) {
                    if(element.getAsJsonPrimitive().isString()) {
                        sb.append("String");
                    } else if(element.getAsJsonPrimitive().isBoolean()) {
                        sb.append("Number");
                    } else if(element.getAsJsonPrimitive().isNumber()) {
                        sb.append("Boolean");
                    }
                } else if (element.isJsonArray()) {
                    sb.append("Array");
                } else if (element.isJsonObject()) {
                    sb.append("Object");
                } else if (element.isJsonNull()) {
                    sb.append("null");
                }

                System.out.println(sb.toString());
            }

        }catch (Exception e) {

        }

    }
//    public static void main(String[] args) {
//        String filePath = "sample.json";
//        Path jsonFilePath = Paths.get(filePath);
//        try {
//            String wholeData = new String(Files.readAllBytes(jsonFilePath));
//            Gson gson = new Gson();
//            JsonObject jsonObj = gson.fromJson(wholeData, JsonObject.class);
//
//            for (String key : jsonObj.keySet()) {
//                System.out.print("Key : " + key + " / Value Type : ");
//                JsonElement je = jsonObj.get(key);
//                if (je.isJsonPrimitive()) {
//                    if (je.getAsJsonPrimitive().isString()) {
//                        System.out.println("String");
//                    } else if (je.getAsJsonPrimitive().isNumber()) {
//                        System.out.println("Number");
//                    } else if (je.getAsJsonPrimitive().isBoolean()) {
//                        System.out.println("Boolean");
//                    } else if (je.getAsJsonPrimitive().isJsonNull()) {
//                        System.out.println("null");
//                    }
//                } else if (je.isJsonArray()) {
//                    System.out.println("Array");
//                } else if (je.isJsonObject()) {
//                    System.out.println("Object");
//                } else if (je.isJsonNull()) {
//                    System.out.println("null");
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
