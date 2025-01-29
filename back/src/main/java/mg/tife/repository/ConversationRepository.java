package mg.tife.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mg.tife.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long>{
	Page<Conversation> findByUserId(Long userId, Pageable pageable);
}