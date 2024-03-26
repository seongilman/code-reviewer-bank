package com.example.demo;

import com.example.demo.mail.MailQueueWatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	MailQueueWatcher mailQueueWatcher;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void startMailQueueWatcher() {
		new Thread(mailQueueWatcher).start();
	}
}
