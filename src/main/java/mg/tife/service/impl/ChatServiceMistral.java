package mg.tife.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import mg.tife.entity.Sentiment;
import mg.tife.service.ChatService;

@Service
public class ChatServiceMistral implements ChatService {

	private MistralAiChatModel chatModel;
	
	private  ChatClient chatClient;
	
	private VectorStore vectorStore;
	
	private final EmbeddingModel embeddingModel;
	
	public ChatServiceMistral(ChatClient.Builder builder,MistralAiChatModel chatModel,VectorStore vectorStore,EmbeddingModel embeddingModel) {
		this.chatClient = builder.build();
		this.chatModel = chatModel;
		this.vectorStore = vectorStore;
		this.embeddingModel = embeddingModel;
	}
	
	@Value("classpath:/prompts/prompt_sentiment.st")
    private Resource promptResourceSentiment;
	
	@Value("classpath:/prompts/prompt_bible.st")
    private Resource promptResourceBible;
	
	@Override
	public String sendMessage(String msg) {
		return chatModel.call(msg);
	}

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

	@Override
	public String askingBible(String msg) {
		SearchRequest request = SearchRequest.query(msg).withTopK(4);
		List<Document> documents = this.vectorStore.similaritySearch(request);
		PromptTemplate promptTemplate = new PromptTemplate(promptResourceBible);
        List<String> context = documents.stream().map(d-> d.getContent()).toList();
        context.stream().forEach(System.out::println);
        Prompt prompt = promptTemplate.create(Map.of("context",context,"question",msg));
        return chatClient.prompt(prompt).call().content();
	}


	@Override
	public Map embedding(String message) {
		var embeddingResponse = this.embeddingModel.embedForResponse(List.of(message));
        return Map.of("embedding", embeddingResponse);
	}
}
