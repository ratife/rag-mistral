package mg.tife.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import mg.tife.entity.Sentiment;
import mg.tife.service.SentimentChatService;

@Service
public class SentimentChatServiceImpl implements SentimentChatService {

	private  ChatClient chatClient;
	
	public SentimentChatServiceImpl(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}
	
	@Value("classpath:/prompts/prompt_sentiment.st")
    private Resource promptResourceSentiment;
	
	@Override
	public Sentiment guessSentiment(String msg) {
		PromptTemplate promptTemplate = new PromptTemplate(promptResourceSentiment);
		Prompt promptSentiment = promptTemplate.create();
        return chatClient.prompt()
                .system(promptSentiment.getContents())
                .user(msg)
                .call()
                .entity(Sentiment.class);
	}
}
