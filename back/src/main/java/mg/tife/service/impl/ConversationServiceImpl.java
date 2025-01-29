package mg.tife.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mg.tife.entity.Conversation;
import mg.tife.entity.Message;
import mg.tife.repository.ConversationRepository;
import mg.tife.repository.MessageRepository;
import mg.tife.service.ConversationService;

@AllArgsConstructor
@Service
public class ConversationServiceImpl implements ConversationService{
	
	MessageRepository messageRepository;
	ConversationRepository conversationRepository;
	
	@Override
	public Page<Message> getMessageByConversationId(Long conversationId,Pageable pageable) {
		return messageRepository.findByConversationId(conversationId,pageable);
	}

	@Override
	public Page<Conversation> getConversationByUserId(Long userId,Pageable pageable) {
		return conversationRepository.findByUserId(userId,pageable);
	}

	@Override
	public Conversation createConversation(Long userId) {
		Conversation conv = new Conversation();
		conv.setUserId(userId);
		conv.setTitle("new Conversation");
		conv.setTimestamp(new Date());
		return conversationRepository.save(conv);
	}

	@Override
	public void addMessage(Long convId, String msg) {
		Optional<Conversation> convRes = conversationRepository.findById(convId);
		convRes.ifPresent((conv)->{
			Message message = new Message();
			message.setConversation(conv);
			message.setText(msg);
			message.setSender("Me");
			message.setTimestamp(new Date());
			messageRepository.save(message);
		});
	}

	@Override
	public void addReponse(Long convId, String rep) {
		Optional<Conversation> convRes = conversationRepository.findById(convId);
		convRes.ifPresent((conv)->{
			Message message = new Message();
			message.setConversation(conv);
			message.setText(rep);
			message.setSender("AI");
			message.setTimestamp(new Date());
			messageRepository.save(message);
			messageRepository.save(message);
		});
	}
}