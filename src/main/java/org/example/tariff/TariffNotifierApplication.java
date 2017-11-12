package org.example.tariff;

import org.springframework.boot.Banner.Mode;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TariffNotifierApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
                        .sources(TariffNotifierApplication.class)
                        .bannerMode(Mode.OFF)
                        .run(args);
                
                
	}
}
