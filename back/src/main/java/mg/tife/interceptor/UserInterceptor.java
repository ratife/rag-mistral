package mg.tife.interceptor;

import java.security.Principal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mg.tife.entity.User;
import mg.tife.holder.CurrentUserHolder;

@AllArgsConstructor	
@Component
public class UserInterceptor implements HandlerInterceptor {

		private final  CurrentUserHolder currentUserHolder;
		private final UserDetailsService userDetailsService;

	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        Principal principal = request.getUserPrincipal();
	        if (principal != null) {
	        	String username = principal.getName();
	        	UserDetails user = userDetailsService.loadUserByUsername(username);
	        	currentUserHolder.setUser((User)user);
	        }
	        return true;
	    }
}
