package operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import objects.CountNValue;
import objects.HotelInfo;
import objects.HotelObj;
import objects.TopicSentiment;

public class ReadHotelFiles implements ReadData{

	private final String folderPath;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public ReadHotelFiles() {
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			folderPath = "C:/hotel";
		} else
			folderPath = "/home/user/hotel";
	}

	public ReadHotelFiles(String folderPath) {
		this.folderPath = folderPath;
	}

	public void readHotelData() throws IOException {

		File dataFolder = new File(folderPath);
		if (!dataFolder.isDirectory()) {
			System.out.println("This is not a directories path");
			return;
		}
		JsonReader jr;
		JsonParser parser = new JsonParser();
		// JsonElement element ;
		for (String f : dataFolder.list()) {
			System.out.println(f);
			jr = new JsonReader(new FileReader(filemaker(f)));
			JsonElement element = parser.parse(jr);
			JsonObject jobj = element.getAsJsonObject();
			// System.out.println(jobj.getAsJsonArray("Reviews"));
			JsonObject hotelInfo = jobj.getAsJsonObject("HotelInfo");
			for (Entry<String, JsonElement> es : hotelInfo.entrySet()) {
				 System.out.println(es.getKey()+" : "+gson.fromJson(es.getValue(), String.class));
			}

			// for( JsonElement arrelem:jobj.getAsJsonArray("Reviews")){
			// JsonObject jo=arrelem.getAsJsonObject();
			// System.out.println(jo);
			// }
			// Set<Map.Entry<String, JsonElement>> entries =
			// jobj.entrySet();//will return members of your object
			// for (Map.Entry<String, JsonElement> entry: entries) {
			// System.out.println(entry.getKey()+" : "+entry.getValue());
			//
			// }
			// Object el = gson.fromJson(jr, Object.class);
			// while(jr.hasNext()){
			// System.out.println(jr.nextName());
			// }
		}

	}

	private void getHotelInfo(JsonObject hotelInfo) {
		HotelObj hobj = new HotelObj();
		for (Entry<String, JsonElement> es : hotelInfo.entrySet()) {
			switch (es.getKey()) {
			case "Name":
				hobj.setName(gson.fromJson(es.getValue(), String.class));
				break;
			case "HotelURL":
				hobj.setUrl(gson.fromJson(es.getValue(), String.class));
				break;
			case "Address":
				hobj.setHtmlAddress(gson.fromJson(es.getValue(), String.class));
				break;
			case "HotelID":
				hobj.setName(gson.fromJson(es.getValue(), String.class));
				break;
			case "ImgURL":
				hobj.setImgUrl(gson.fromJson(es.getValue(), String.class));
				break;
			case "Price":
				//TODO get the price string and elaborate
				hobj.setName(gson.fromJson(es.getValue(), String.class));
				break;
			}
			// System.out.println(es.getKey()+" "+es.getValue());
		}

	}

	
	private double[] priceEstimation(String priceStr){
		double[] priceUpDown= new double[2];
		
		return priceUpDown;
	}
	
	private String filemaker(String f) {
		return folderPath + "/" + f;
	}

	@Override
	public HotelInfo getHotelInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, CountNValue> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, TopicSentiment> getTopicMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
