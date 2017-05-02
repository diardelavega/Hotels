package operations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.jdt.internal.compiler.ast.InstanceOfExpression;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import objects.CountNValue;
import objects.HotelInfo;
import objects.HotelObj;
import objects.TopicSentiment;

/**
 * @author Administrator
 * 
 *         This Class is utilized to read the data from the file and search for
 *         the topic requested by the user. All the file has to be read to
 *         search in the comments for the topic.
 * 
 */
public class ReadHotelFiles implements ReadData {

	private final String topic;
	private final String folderPath;
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private HotelInfo hi = new HotelInfo();
	private Map<String, CountNValue> attCNV = new HashMap<>();

	public ReadHotelFiles(String topic) {
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			folderPath = "C:/hotel";
		} else
			folderPath = "/home/user/hotel";
		this.topic = topic;
	}

	public ReadHotelFiles(String folderPath, String topic) {
		this.folderPath = folderPath;
		this.topic = topic;
	}

	public void readHotelData() throws IOException {

		File dataFolder = new File(folderPath);
		if (!dataFolder.isDirectory()) {
			System.out.println("This is not a directories path");
			return;
		}
		JsonReader jr;
		JsonParser parser = new JsonParser();
		for (String f : dataFolder.list()) {

			// parse json file to json object to be able to travers it
			jr = new JsonReader(new FileReader(filemaker(f)));
			JsonElement element = parser.parse(jr);
			JsonObject jobj = element.getAsJsonObject();

			// go through the reviews to evaluate the hotel for a topic.
			JsonArray reviews = jobj.getAsJsonArray("Reviews");
			topicSearch(reviews);

			// TEST print results
			for (String key : attCNV.keySet()) {
				System.out.printf("%s :  %.3f   \n", key, attCNV.get(key).properyAvgPoint());
			}

			// request the hotel info sub-element to extract data
			// JsonObject hotelInfo = jobj.getAsJsonObject("HotelInfo");
			// extractHotelInfo(hotelInfo);

		}

	}

	/* iterate through the reviews to find sentiment about a topic */
	private void topicSearch(JsonArray reviews) {
		for (JsonElement rev : reviews) {
			JsonObject revobj = rev.getAsJsonObject();
			JsonObject rating = revobj.getAsJsonObject("Ratings");
			extractAttributeRatings(rating);

			
			
			String comment= revobj.get("Content").getAsString();
			TopicSearchEvaluation tse = new TopicSearchEvaluation();
			tse.perSentenceSearch(comment, topic);
		}
	}

	/*
	 * go through every hotel attribute evaluated by the user, to produce a
	 * better idea about the hotel general/common services
	 */
	private void extractAttributeRatings(JsonObject rating) {
		for (Map.Entry<String, JsonElement> entry : rating.entrySet()) {
			if (attCNV.containsKey(entry.getKey()))
				attCNV.get(entry.getKey()).addValCount(Float.parseFloat(entry.getValue().getAsString()));
			else {
				CountNValue cnv = new CountNValue();
				cnv.addValCount(Float.parseFloat(entry.getValue().getAsString()));
				attCNV.put(entry.getKey().toString(), cnv);
			} // else
		} // for

	}

	private void extractHotelInfo(JsonObject hotelInfo) {

		for (Entry<String, JsonElement> es : hotelInfo.entrySet()) {
			switch (es.getKey()) {
			case "Name":
				hi.setName(gson.fromJson(es.getValue(), String.class));
				break;
			case "HotelURL":
				hi.setUrl(gson.fromJson(es.getValue(), String.class));
				break;
			case "Address":
				hi.setHtmlAddress(gson.fromJson(es.getValue(), String.class));
				break;
			case "HotelID":
				hi.setName(gson.fromJson(es.getValue(), String.class));
				break;
			case "ImgURL":
				hi.setImgUrl(gson.fromJson(es.getValue(), String.class));
				break;
			case "Price":
				hi.setPrice(gson.fromJson(es.getValue(), String.class));
				break;

			// default:
			// System.out.println("No attribute");
			// break;
			}
		}

	}

	private String filemaker(String f) {
		return folderPath + "/" + f;
	}

	@Override
	public HotelInfo getHotelInfo() {
		// TODO Auto-generated method stub
		return hi;
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
