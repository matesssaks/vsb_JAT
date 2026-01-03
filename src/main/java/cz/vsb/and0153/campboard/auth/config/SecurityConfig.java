package cz.vsb.and0153.campboard.auth.config;

import org.jspecify.annotations.NonNull;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.support.*;
import org.springframework.aop.support.annotation.AnnotationClassFilter;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.method.AuthorizationAdvisor;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http
			.authorizeHttpRequests(auth -> auth
				.anyRequest().permitAll() // Allow all, because authorization is handled by `AuthorizationAdvisor`
			)
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutSuccessUrl("/")
				.permitAll()
			);

		return http.build();
	}

	@Bean
	@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
	public AuthorizationAdvisor denyAllByDefault() {
		ComposablePointcut isSecured = new ComposablePointcut(new AnnotationMatchingPointcut(null, PreAuthorize.class, true));
		isSecured.union(new AnnotationMatchingPointcut(null, PostAuthorize.class, true));
		isSecured.union(new AnnotationMatchingPointcut(PreAuthorize.class, true));
		isSecured.union(new AnnotationMatchingPointcut(PostAuthorize.class, true));

		MethodMatcher notSecured = new StaticMethodMatcher() {
			@Override
			public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
				return !Pointcuts.matches(isSecured, method, targetClass);
			}
		};

		return new AuthorizationManagerBeforeMethodInterceptor(
			new ComposablePointcut(new AnnotationClassFilter(Controller.class, true), notSecured),
			(auth, obj) -> new AuthorizationDecision(false)
		);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
