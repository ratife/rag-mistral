package mg.tife.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import mg.tife.service.ChatService;

@Service
public class ChatServiceMistral implements ChatService {

	private MistralAiChatModel chatModel;
	
	private  ChatClient chatClient;
	
	public ChatServiceMistral(ChatClient.Builder builder,MistralAiChatModel chatModel) {
		this.chatClient = builder.build();
		this.chatModel = chatModel;
	}
	
	@Value("classpath:/prompts/prompt_sentiment.st")
    private Resource promptResourceSentiment;
	
	@Override
	public String sendMessage(String msg) {
		return chatModel.call(msg);
	}

	@Override
	public String guessSentiment(String msg) {
		PromptTemplate promptTemplate = new PromptTemplate(promptResourceSentiment);
    	Prompt prompt = promptTemplate.create();
        return chatClient.prompt()
                .system(prompt.getContents())
                .user(msg)
                .call()
                .content();
	}

}
