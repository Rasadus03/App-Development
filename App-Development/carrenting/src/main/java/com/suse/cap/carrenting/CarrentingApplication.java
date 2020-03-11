package com.suse.cap.carrenting;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.suse.cap.carrenting.appconfig.AppConfig;
import com.suse.cap.carrenting.data.controller.PopulateData;
@EnableAutoConfiguration
@SpringBootApplication
public class CarrentingApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CarrentingApplication.class)
        .initializers(new AppConfig())
        .listeners(new PopulateData())
        .application()
        .run(args);
	}

}
