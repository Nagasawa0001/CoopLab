package core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import core.exception.AuthenticationFailureHandlerImpl;
import core.exception.AuthenticationSuccessHandlerImpl;
import core.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/users/**").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
            .loginProcessingUrl("/signin").permitAll()
            .usernameParameter("email")
            .passwordParameter("password")
            .successHandler(authenticationSuccessHandler())
            .failureHandler(authenticationFailureHandler())
//            .successHandler(authenticationSuccessHandler())
//            .failureHandler(authenticationFailureHandler())
        .and()
        // LOGOUT
        .logout()
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID");
//            .logoutSuccessHandler(logoutSuccessHandler())
            //.addLogoutHandler(new CookieClearingLogoutHandler())
		http.csrf().disable();

		http.exceptionHandling()
        //Actually Spring already configures default AuthenticationEntryPoint - LoginUrlAuthenticationEntryPoint
        //This one is REST-specific addition to default one, that is based on PathRequest
        .defaultAuthenticationEntryPointFor(getRestAuthenticationEntryPoint(), new AntPathRequestMatcher("/**"));


	}

	@Autowired
	UserService userDetailService;

	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

	AuthenticationSuccessHandler authenticationSuccessHandler() {
	    return new AuthenticationSuccessHandlerImpl();
	}

	AuthenticationFailureHandler authenticationFailureHandler() {
	    return new AuthenticationFailureHandlerImpl();
	}
}
