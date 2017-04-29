package objects;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * 
 *
 */
public class HotelObj {
	private Map<String, CountNValue> topics;

	private String name;
	private String htmlAddress;
	private String location;
	private String url;
	private String imgUrl;
	private Double lowerPrice;
	private Double upperPrice;

	public HotelObj() {
		super();
	}

	public double getTopicAvgPoint(String topic) {
		return topics.get(topic).properyAvgPoint();
	}

	public void addTopicUserValue(String topic, double val) {
		topics.get(topic).addValCount(val);
	}

	public CountNValue getTopicData(String topic) {
		return topics.get(topic);
	}

	public Map<String, CountNValue> getTopic() {
		return topics;
	}

	public void addTopic(String topic) {
		CountNValue cnv = new CountNValue();
		topics.put(topic, cnv);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHtmlAddress() {
		return htmlAddress;
	}

	public void setHtmlAddress(String htmlAddress) {
		this.htmlAddress = htmlAddress;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Double getLowerPrice() {
		return lowerPrice;
	}

	public void setLowerPrice(Double lowerPrice) {
		this.lowerPrice = lowerPrice;
	}

	public Double getUpperPrice() {
		return upperPrice;
	}

	public void setUpperPrice(Double upperPrice) {
		this.upperPrice = upperPrice;
	}

}
