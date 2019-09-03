package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

@SpringBootApplication
public class HelloworldDemoApplication implements EmbeddedServletContainerCustomizer{
    public static void main(String[] args){
        SpringApplication.run(HelloworldDemoApplication.class, args);
    }

    public void customize(ConfigurableEmbeddedServletContainer container) {
        // TODO 自动生成的方法存根
        container.setPort(8088);
    }
}