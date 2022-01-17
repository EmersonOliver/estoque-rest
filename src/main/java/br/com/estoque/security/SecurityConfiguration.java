package br.com.estoque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.com.estoque.token.filter.TokenAuthenticationFilter;
import br.com.estoque.usuario.service.AuthenticationService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private JwtAuthenticationEntrypoint entrypoint;
	
	@Autowired
	private TokenAuthenticationFilter tokenAuthFilter;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
	      .cors().disable()
	      .csrf().disable()
	      .exceptionHandling()
	        .authenticationEntryPoint(entrypoint).and()
	      .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	      .authorizeRequests()
	        .antMatchers("/auth","/usuario/register").permitAll()
	        .antMatchers("/v2/api-docs",
	                     "/configuration/ui",
	                     "/swagger-resources/**",
	                     "/configuration/security",
	                     "/swagger-ui.html",
	                     "/webjars/**").permitAll()
	        .anyRequest().authenticated().and()
	      .addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	 @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source =
	            new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("*");
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }
}
