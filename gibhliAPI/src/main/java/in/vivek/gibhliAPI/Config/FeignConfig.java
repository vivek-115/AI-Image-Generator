package in.vivek.gibhliAPI.Config;

import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FeignConfig {


    //to handle multipart/form-data request
    @Bean
    public SpringFormEncoder feignFormEncoder(){
        return new SpringFormEncoder(
                new SpringEncoder(()->new HttpMessageConverters(new RestTemplate().getMessageConverters()))
        );
    }
}
