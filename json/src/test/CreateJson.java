package test;

import com.google.gson.*;

import java.io.*;

public class CreateJson {

    public static void main(String[] args) {
        JsonElement jsonElement = JsonParser.parseString("{\n" +
                "  \"name\":\"spiderman\",\n" +
                "  \"age\":45,\n" +
                "  \"married\":true,\n" +
                "  \"specialty\":[\"martial art\", \"gun\"],\n" +
                "  \"vaccine\":{\"1st\":done\",\"2nd\":\"expected\",\"3rd\":null},\n" +
                "  \"children\":[{\"name\":\"spiderboy\", \"age\":10},{\"name\":spidergirl\", \"age\":8}],\n" +
                "  \"address\":null\n" +
                "}");

        String jsonStr = jsonElement.toString();


        byte[] bytes = jsonStr.toString().getBytes();
        File file = new File("./sample2.json");

		try {
			BufferedWriter writer = new BufferedWriter((new FileWriter(file)));
			writer.write(jsonStr);
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("name", "spider");
		jsonObject.addProperty("age", 45);
		jsonObject.addProperty("married", true);

		JsonArray specialty = new JsonArray();
		specialty.add("martial art");
		specialty.add("gun");
		jsonObject.add("specialty", specialty);

		JsonObject vaccine = new JsonObject();
		vaccine.addProperty("1st", "done");
		vaccine.addProperty("2nd", "expected");
		vaccine.add("3rd", null);
		jsonObject.add("vaccine", vaccine);

		JsonArray children = new JsonArray();
		JsonObject cAge = new JsonObject();
		cAge.addProperty("name", "spiderboy");
		cAge.addProperty("age", 10);
		children.add(cAge);

		cAge = new JsonObject();
		cAge.addProperty("name", "spidergirl");
		cAge.addProperty("age", 8);
		children.add(cAge);

		jsonObject.add("children", children);
		jsonObject.add("address", null);

		try (Writer writer = new FileWriter("sample.json")) {
			Gson gson2 = new GsonBuilder().serializeNulls().create();
			gson.toJson(jsonObject, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

