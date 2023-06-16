package com.project.accomatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class AccomatchApplication {

//	@Autowired
//	private MailSender senderService;

	public static void main(String[] args) {
		SpringApplication.run(AccomatchApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void sendMail(){
//		senderService.sendEmail("hg15yogish@gmail.com", "Test Subject", "Test Body");
//	}

//	public static final Logger logger = (Logger) LogManager.getLogger();
//	@GetMapping("/get")
//	public String getString(){
//		logger.info("This is the first log");
//		return "String";
//
//	}

}
