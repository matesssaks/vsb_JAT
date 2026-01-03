package cz.vsb.and0153.campboard.tools.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class LocaleConfig {
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.US);
		resolver.setDefaultTimeZone(TimeZone.getTimeZone("UTC"));
		return resolver;
	}
}
