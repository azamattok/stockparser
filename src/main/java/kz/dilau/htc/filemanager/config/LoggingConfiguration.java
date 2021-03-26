package kz.dilau.htc.filemanager.config;

import kz.dilau.htc.filemanager.util.Constants;
import kz.dilau.htc.filemanager.web.rest.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfiguration {
    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilter(){
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoggingFilter());
        registrationBean.addUrlPatterns(Constants.API_PATH + "/*", Constants.OPEN_API_PATH + "/*");

        return registrationBean;
    }
}
