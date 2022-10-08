package com.github.prbrios.mfeapi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class MfeapiApplication  {

	/*
	public static void main(String[] args) {
		SpringApplication.run(MfeapiApplication.class, args);
	}
	*/

	public static void main(String[] args) {
        if (!java.awt.Desktop.isDesktopSupported()) {
            System.exit(1);
        }

        new SpringApplicationBuilder(MfeapiApplication.class).headless(false).run(args);
    }

	@EventListener(ApplicationReadyEvent.class)
    public void openBrowserAfterStartup() throws IOException, URISyntaxException {
        java.awt.Desktop.getDesktop().browse(new URI("http://localhost:8080"));
    }

}




