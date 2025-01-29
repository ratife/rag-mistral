package mg.tife.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mg.tife.service.ChatService;

@AllArgsConstructor
@Service
public class ChatDirectServiceMistral implements ChatService {

	MistralAiChatModel chatModel;
	
	@Override
	public String sendMessage(String msg) {
		return chatModel.call(msg);
	}
}