package org.hiphone.consul.config;

import org.hiphone.consul.config.config.StudentConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author HiPhone
 * 此处开启consul的自动装配配置类，只能使用@ConfigurationProperties这种配置模式
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({StudentConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
