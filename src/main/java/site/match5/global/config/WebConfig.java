package site.match5.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.match5.interceptor.KakaoInterceptor;
import site.match5.interceptor.AuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //카카오 콜백용 인터셉터
        registry.addInterceptor(new KakaoInterceptor())
                .addPathPatterns("/callback", "/callback**")
                        .order(Ordered.HIGHEST_PRECEDENCE);

        //세션보안 인터셉터
        registry.addInterceptor(new AuthInterceptor())

                // 인터셉터로 막음
                .addPathPatterns("/**") //모든 브라우저경로
                .addPathPatterns("/api/**") //모든 api경로
                
                // 허용(인터셉터 적용안하겠다; 로그인,회원가입 등)
                .excludePathPatterns("/images/**","/css/**","/js/**","/*.ico") //정적파일
                .excludePathPatterns("/","/login","/signup","/auth") //브라우저경로
                .excludePathPatterns("/api/auth/kakao/**","/api/auth/login","/api/auth/signup") //api경로
                .excludePathPatterns("/api/**","/test/**"); //api 테스트용으로 추가

    }















//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        //COR 허용
//        registry.addMapping("/**")
//                .allowedOriginPatterns("http://localhost:8080", "http://54.180.159.24:8080")
//                .allowCredentials(true)
//                .maxAge(3600)
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("Origin", "Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization");
//
//    }

}