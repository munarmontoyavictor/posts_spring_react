package com.co.vamunarm.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.co.vamunarm.blog.models.responses.UserRest;
import com.co.vamunarm.blog.security.AppProperties;
import com.co.vamunarm.blog.shared.dto.UserDto;

@SpringBootApplication
@EnableJpaAuditing // permite crear fechas automaticamente en campos tipo @CreateDate
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		System.out.println("Start App blog");
	}

	// Permite hacer un llamado mediante Autowired en las clases
	@Bean
	public BCryptPasswordEncoder bxcBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		// avoid fields
		mapper.typeMap(UserDto.class, UserRest.class)
		.addMappings(m -> m.skip(UserRest::setPosts));
		return mapper;
	}

}
