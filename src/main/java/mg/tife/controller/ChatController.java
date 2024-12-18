package mg.tife.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mg.tife.entity.Sentiment;
import mg.tife.service.ChatService;


@Tag(name = "CatalogUserController", description = "Point d'accès pour les requêtes chatter mistral")
@RestController
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private ChatService chatService;
	
	@Operation(summary = "chat direct")
    @GetMapping
    public ResponseEntity<String> chat(@RequestParam String msg) {
		return  new ResponseEntity<>(chatService.sendMessage(msg), HttpStatus.OK);
    }
	
	@Operation(summary = "sentiment detecteur")
    @GetMapping("/sentiment")
    public ResponseEntity<Sentiment> sentiment(@RequestParam String msg ) {
		return  new ResponseEntity<>(chatService.guessSentiment(msg), HttpStatus.OK);
    }
	
	@Operation(summary = "Bible asker")
    @GetMapping("/bible")
    public ResponseEntity<String> bible(@RequestParam String msg ) {
		return  new ResponseEntity<>(chatService.askingBible(msg), HttpStatus.OK);
    }
	
	@Operation(summary = "embedding asker")
    @GetMapping("/embeding")
    public ResponseEntity<Map> embedding(@RequestParam String msg ) {
		return  new ResponseEntity<>(chatService.embedding(msg), HttpStatus.OK);
    }
}
