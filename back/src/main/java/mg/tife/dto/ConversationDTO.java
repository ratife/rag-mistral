package mg.tife.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ConversationDTO {
	private Long id;
	private String title;
	private Date timestamp;
	private Long userId;
}