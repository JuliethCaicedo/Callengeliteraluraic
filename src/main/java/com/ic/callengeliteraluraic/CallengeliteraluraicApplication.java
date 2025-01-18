package com.ic.callengeliteraluraic;

import com.ic.callengeliteraluraic.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CallengeliteraluraicApplication implements CommandLineRunner {


	private final Principal principal;

	@Autowired
	public CallengeliteraluraicApplication(Principal principal) {
		this.principal = principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(CallengeliteraluraicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.muestraElMenu();


	}
}
