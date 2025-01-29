package mg.tife.holder;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Getter;
import lombok.Setter;
import mg.tife.entity.User;

@Setter
@Getter
@Component
@RequestScope
public class CurrentUserHolder {
    private User user;
}