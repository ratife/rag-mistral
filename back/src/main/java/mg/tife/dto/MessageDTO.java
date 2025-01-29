package mg.tife.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MessageDTO {
	private Long id;
	private String sender;
    private String text;
	private Date timestamp;
}