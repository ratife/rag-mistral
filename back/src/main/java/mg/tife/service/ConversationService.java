package mg.tife.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mg.tife.entity.Conversation;
import mg.tife.entity.Message;

public interface ConversationService {
	public Page<Message> getMessageByConversationId(Long conversationId,Pageable pageable);
	public Page<Conversation> getConversationByUserId(Long userId,Pageable pageable);
	public Conversation createConversation(Long userId);
	
	public void addMessage(Long convId, String msg);
	public void addReponse(Long convId,String rep);
}