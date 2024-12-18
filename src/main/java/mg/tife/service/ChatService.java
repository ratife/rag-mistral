package mg.tife.service;

import java.util.Map;

import mg.tife.entity.Sentiment;

public interface ChatService {
	public String sendMessage(String msg);
	public Sentiment guessSentiment(String msg);
	public String askingBible(String msg);
	public Map embedding(String msg);
}