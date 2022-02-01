package com.demo.creditservices.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Configuration
@Slf4j
@SuppressWarnings("unused")
public class AppVersionConfiguration {
    @Autowired(required = false)    // When building or running unit-tests
    BuildProperties buildProperties;

    @Bean
    public void getServiceInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                .withLocale( Locale.US )
                .withZone( ZoneId.systemDefault() );
        if (buildProperties != null){
            var componentVersion = buildProperties.getArtifact()
                    + ":" + buildProperties.getVersion()
                    + ", " + formatter.format(buildProperties.getTime());
            log.info("*** App version: {}", componentVersion);
        }
    }
}
