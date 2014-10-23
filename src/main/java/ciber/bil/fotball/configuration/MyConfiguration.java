package ciber.bil.fotball.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class MyConfiguration {

	//Fixing issue with Java Optionals not converting correctly to json
	@Bean
	public MappingJackson2HttpMessageConverter jackson2HttpMessageConverterWithOptionalSupport() {
		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		final ObjectMapper jacksonOptionalMapper = new ObjectMapper().findAndRegisterModules();
		jacksonOptionalMapper.enable(SerializationFeature.INDENT_OUTPUT); //prettyprint
		jacksonConverter.setObjectMapper(jacksonOptionalMapper);
		return jacksonConverter;
	}
}
