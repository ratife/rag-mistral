package mg.tife.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import mg.tife.service.ChatBibleService;

@Service
public class ChatBibleHighLevelCodeServiceMistral implements ChatBibleService {

	private  ChatClient chatClient;
	
	public ChatBibleHighLevelCodeServiceMistral(ChatClient.Builder builder,VectorStore vectorStore) {
		this.chatClient = builder
				.defaultAdvisors(new QuestionAnswerAdvisor(vectorStore))
				.build();
	}
	
	@Override
	public String ask(String msg) {
		return chatClient.prompt()
                .user(msg)
                .call().content();

	}
}
