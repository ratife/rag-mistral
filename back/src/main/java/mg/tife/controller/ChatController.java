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
import mg.tife.service.AiService;


@Tag(name = "CatalogUserController", description = "Point d'accès pour les requêtes chatter mistral")
@RestController
@AllArgsConstructor
@RequestMapping("/chat")
public class ChatController {
	
	private AiService messageService;
	
	@Operation(summary = "Bible asker")
    @PutMapping("/bible")
    public ResponseEntity<String> bible(@RequestParam Long convId, @RequestParam String msg ) {
		return  new ResponseEntity<>(messageService.ask(convId, msg), HttpStatus.OK);
    }
}
