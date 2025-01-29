package mg.tife.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mg.tife.dto.ConversationDTO;
import mg.tife.entity.Conversation;
import mg.tife.holder.CurrentUserHolder;
import mg.tife.mapper.ConversationMapper;
import mg.tife.service.ConversationService;


@Tag(name = "HomeController", description = "Conversation point d'entrée")
@RestController
@AllArgsConstructor
@RequestMapping("/conversation")
public class ConversationController {
	
	private ConversationService conversationService;
	private ConversationMapper conversationMapper;
	private CurrentUserHolder currentUserHolder;
	
	@Operation(summary = "list de conversation")
    @GetMapping("/list")
    public ResponseEntity<Page<ConversationDTO>> getConversation(Pageable pageable) {
		Page<Conversation> conversations =  conversationService.getConversationByUserId(currentUserHolder.getUser().getId(), pageable);
		return  new ResponseEntity<>(conversations.map(conversationMapper::toDTO), HttpStatus.OK);
    }
	
	@Operation(summary = "creation de conversation")
    @GetMapping("/new")
    public ResponseEntity<ConversationDTO> newConversation() {
		Conversation conversation =  conversationService.createConversation(currentUserHolder.getUser().getId());
		return  new ResponseEntity<>(conversationMapper.toDTO(conversation), HttpStatus.OK);
    }
}