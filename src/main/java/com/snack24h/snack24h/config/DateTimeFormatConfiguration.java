package com.snack24h.snack24h.config;

import java.time.format.DateTimeFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * DataType parameter bind 처리
 */
@Configuration
public class DateTimeFormatConfiguration implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
//    registrar.setDateFormatter(DateTimeFormatter.ISO_DATE);
//    registrar.setDateTimeFormatter(DateTimeFormatter.ISO_DATE_TIME);
    registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    registrar.registerFormatters(registry);
  }
}
