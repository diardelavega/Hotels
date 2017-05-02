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

	private float commentSentiment = 0;

	/*
	 * separate every sentence in the comment and examin them individually to
	 * spot the search topic
	 */
	public void perSentenceSearch(String comment, String topic) {

		Locale locale = Locale.UK;
		BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
		breakIterator.setText(comment);
		int start = breakIterator.first();
		int end = breakIterator.next();
		boolean foundFlag = false;
		String sentence;

		while (end != BreakIterator.DONE) {
			sentence = comment.substring(start, end);
			foundFlag = topicSearch(sentence, topic);
			if (foundFlag) {
				System.out.printf("%s   %b \n", sentence, foundFlag);
				// loop the sentence and search for semantic corresponding words
				commentSentiment += semanticSearch(sentence);
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
	private boolean topicSearch(String sent, String searchTerm) {
		boolean foundTopicFlag = false;
		Locale locale = Locale.UK;
		BreakIterator breakIterator = BreakIterator.getWordInstance(locale);
		breakIterator.setText(sent);

		int start = breakIterator.first();
		int end = breakIterator.next();
		String word;
		while (end != BreakIterator.DONE) {
			word = sent.substring(start, end);
			if (word.equalsIgnoreCase(searchTerm)) {
				foundTopicFlag = true;
				break;
			}
			start = end;
			end = breakIterator.next();
		}
		return foundTopicFlag;
	}

	/*
	 * This function determines if the sentiment in the sentence, in which the
	 * search-topic is mentioned. For every word in the sentence check in the
	 * Commons semantic structures to see if it's a semantically valuable word
	 * and consider it for the numerical evaluation of the topic sentiment.
	 */
	private float semanticSearch(String sent) {
		/**
		 * From a syntactical point of view of the English language (in which
		 * language the comments are), the intensifiers normally would go in
		 * front of the adjective or phrase that it is trying to emphasize. The
		 * way this function works is by getting the intensifiers of the
		 * sentence first and multiplying that to the first semantically
		 * positive or negative phrase. After that intent to emphasize has been
		 * applied to a phrase then the multiplier (the numerical value of the
		 * intensifier) becomes 0 and it is ready to find another positive or
		 * negative instance to apply emphases to.
		 */

		// values for the numerical representation of sentiment in the sentence
		float sentMultyplier = 0;
		float sentencePositive = 0;
		float sentenceNegative = 0;

		Locale locale = Locale.UK;
		BreakIterator breakIterator = BreakIterator.getWordInstance(locale);
		breakIterator.setText(sent);

		int start = breakIterator.first();
		int end = breakIterator.next();
		String word;
		while (end != BreakIterator.DONE) {
			if (end - start <= 2) {// skip small words{
				start = end;
				end = breakIterator.next();
				continue;
			}
			word = sent.substring(start, end).toLowerCase();

			if (Commons.intensifiers.containsKey(word)) {
				sentMultyplier += Commons.intensifiers.get(word);
			} else if (Commons.positive.containsKey(word)) {
				if (sentMultyplier != 0) {
					sentencePositive += sentMultyplier * Commons.positive.get(word);
					sentMultyplier = 0;
				} else {
					sentencePositive += Commons.positive.get(word);
				}
			} else if (Commons.negative.containsKey(word)) {
				if (sentMultyplier != 0) {
					sentenceNegative += sentMultyplier * Commons.negative.get(word);
				} else {
					sentenceNegative += Commons.negative.get(word);
					sentMultyplier = 0;
				}
			}

			start = end;
			end = breakIterator.next();
		}
		System.out.printf("pos : %.2f,   neg: %.2f \n", sentencePositive, sentenceNegative);
		return (sentencePositive - sentenceNegative);
	}

	/**
	 * POSIBLE EXTENTIONS**************************************************
	 * 
	 * public float stringSimilarity(String word, String topic) { return 0; }
	 * 
	 * public String stemming(String word) { return word; }
	 */
}
