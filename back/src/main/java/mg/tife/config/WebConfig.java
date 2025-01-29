package mg.tife.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import mg.tife.interceptor.UserInterceptor;


@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer{
	private final UserInterceptor userInterceptor;
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor);
    }
}