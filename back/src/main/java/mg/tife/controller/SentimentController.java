package mg.tife.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mg.tife.entity.Sentiment;
import mg.tife.service.SentimentChatService;


@Tag(name = "SentimentController", description = "Point d'accès pour les requêtes chatter mistral")
@RestController
@AllArgsConstructor
@RequestMapping("/chat")
public class SentimentController {
	
	private SentimentChatService sentimentChatService;
	
	@Operation(summary = "sentiment detecteur")
    @PutMapping("/sentiment")
    public ResponseEntity<Sentiment> sentiment(@RequestParam String msg) {
		return  new ResponseEntity<>(sentimentChatService.guessSentiment(msg), HttpStatus.OK);
    }
}
