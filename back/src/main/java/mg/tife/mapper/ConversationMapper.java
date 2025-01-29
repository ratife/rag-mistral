package mg.tife.mapper;

import org.mapstruct.Mapper;

import mg.tife.dto.ConversationDTO;
import mg.tife.entity.Conversation;

@Mapper(componentModel = "spring")
public interface ConversationMapper {
    ConversationDTO toDTO(Conversation conv);
}