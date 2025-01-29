package mg.tife.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mg.tife.service.AiService;
import mg.tife.service.ChatBibleService;
import mg.tife.service.ConversationService;

@AllArgsConstructor
@Service
public class AiServiceImpl implements AiService{
	
	private ChatBibleService chatBibleService;
	
	private ConversationService conversationService;
	
	@Override
	public String ask(Long convId, String msg) {
		conversationService.addMessage(convId,msg);
		String rep = chatBibleService.ask(msg);
		conversationService.addReponse(convId,rep);
		return rep;
	}
}