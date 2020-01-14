package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;


@SpringBootApplication(exclude = SolrAutoConfiguration.class)
public class HelloworldDemoApplication implements EmbeddedServletContainerCustomizer{
    public static void main(String[] args){
        SpringApplication.run(HelloworldDemoApplication.class, args);
    }

    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8088);
    }
}