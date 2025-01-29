package mg.tife.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mg.tife.service.ChatBibleService;

@Primary
@Service
public class ChatBibleLowLevelCodeServiceMistral implements ChatBibleService {

	private ChatClient chatClient;
	private VectorStore vectorStore;
	
	@Value("classpath:/prompts/prompt_bible.st")
    private Resource promptResourceBible;
	
	public ChatBibleLowLevelCodeServiceMistral(ChatClient.Builder builder,VectorStore vectorStore) {
		this.chatClient = builder.build();
	}

	@Override
	public String ask(String msg) {
		// Recherche des documents similaires
		SearchRequest request = SearchRequest.query(msg).withTopK(5);
		List<Document> documents = this.vectorStore.similaritySearch(request);

		// Génération du contexte à partir des documents récupérés
		List<String> context = documents.stream()
		                                .map(Document::getContent) // Méthode référence pour plus de lisibilité
		                                .toList();
		context.forEach(System.out::println); // Affichage direct pour débogage

		// Création du prompt à partir du modèle
		PromptTemplate promptTemplate = new PromptTemplate(promptResourceBible);
		Prompt prompt = promptTemplate.create(Map.of(
		    "context", String.join("\n", context), // Concaténation pour structurer le contexte
		    "question", msg
		));

		// Appel au client AI pour obtenir la réponse
		return chatClient.prompt(prompt).call().content();

	}
}
