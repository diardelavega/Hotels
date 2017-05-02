package operations;

import java.text.BreakIterator;
import java.util.Locale;

import utils.Commons;

/**
 * @author Administrator
 * 
 *         search through the comment by breaking it down to sentences and in
 *         each of them look for the topic in hand and the semantic words that
 *         it may be associated with.
 */
public class TopicSearchEvaluation {

	private float multiplier=0;
	
	
	/*
	 * separate every sentence in the comment and examin them individually to
	 * spot the search topic
	 */
	public void sentenceBreak(String comment, String topic) {

		Locale locale = Locale.UK;
		BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
		breakIterator.setText(comment);
		int start = breakIterator.first();
		int end = breakIterator.next();
		boolean foundFlag = false;
		String sentence;

		while (end != BreakIterator.DONE) {
			sentence = comment.substring(start, end);
			foundFlag = wordBreak(sentence, topic, 0);
			if (foundFlag) {
				// loop the sentence and search for semantic corresponding words
				wordBreak(sentence, topic, 1);
			}
			start = end;
			end = breakIterator.next();
		}
	}

	/*
	 * for every word of the sentence, search for the specified topic. If found
	 * determine the semantic meaning by utilizing the semantic file key words
	 * and their values.
	 */
	public boolean wordBreak(String sent, String searchTerm, int searchCategory) {
		boolean foundTopicFlag = false;
		Locale locale = Locale.UK;
		BreakIterator breakIterator = BreakIterator.getWordInstance(locale);
		breakIterator.setText(sent);

		int start = breakIterator.first();
		int end = breakIterator.next();
		String word;
		while (end != BreakIterator.DONE) {
			word = sent.substring(start, end);
			if (searchCategory == 0) {// searching for hotel topic
				if (word.equalsIgnoreCase(searchTerm)) {
					foundTopicFlag = true;
					break;
				}
			} else if (searchCategory == 1) {// search for semantic meaning
				if (Commons.intensifiers.containsKey(word)) {

				}
			}// else if
			
			start = end;
			end = breakIterator.next();
		}
		return foundTopicFlag;
	}

	private void sentimentEvaluation(String sentence) {

	}

	/**
	 * POSIBLE EXTENTIONS**************************************************
	 * 
	 * public float stringSimilarity(String word, String topic) { return 0; }
	 * 
	 * public String stemming(String word) { return word; }
	 */
}
