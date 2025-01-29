package mg.tife.service;

import mg.tife.entity.Sentiment;

public interface SentimentChatService {
	public Sentiment guessSentiment(String msg);
}