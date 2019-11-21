package io.turntabl.tcmsCustomers;


import io.turntabl.tcmsCustomers.models.CustomerTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@EnableSwagger2
@SpringBootApplication
public class TcmsCustomersApplication {

	private RedisServer redisServer;

	public static void main(String[] args) {
		SpringApplication.run(TcmsCustomersApplication.class, args);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}


	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("customers");
	}

	@Bean
	public RedisTemplate<String, CustomerTO> redisTemplate() {
		final RedisTemplate<String, CustomerTO> template = new RedisTemplate<String, CustomerTO>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<CustomerTO>(CustomerTO.class));
		return template;
	}
}
