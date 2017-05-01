package objects;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * 
 *         The class for object which will contain all the information regarding
 *         the a hotel whose reviews are being scanned to search for a topic
 *         information. The HotelObj has a map of the user evaluated hotel
 *         attributes, a map of the search topic with its estimation and general
 *         information about the hotel in hand.
 * 
 */
public class HotelObj {
	private HotelInfo info;

	// point awarded attributes <attrName, <count/sum/avg.>>
	private Map<String, CountNValue> attributes = new HashMap<>();

	// topic sentiment point <topicName, <point/sentence/review_info>>
	private Map<String, TopicSentiment> topicInfo = new HashMap<>();

	public double getAttributeAvgPoint(String attribute) {
		return attributes.get(attribute).properyAvgPoint();
	}

	/*
	 * add automatically the value of the attribute to it's allready existing
	 * count and sum values
	 */
	public void addAttributeUserValue(String attribute, double val) {
		attributes.get(attribute).addValCount(val);
	}

	public CountNValue getAttributeData(String attribute) {
		return attributes.get(attribute);
	}

	public Map<String, CountNValue> getAttribute() {
		return attributes;
	}

	public void addAttribute(String attribute) {
		CountNValue cnv = new CountNValue();
		attributes.put(attribute, cnv);
	}

}
