package mg.tife.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mg.tife.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
	Page<Message> findByConversationId(Long conversationId, Pageable pageable);
}