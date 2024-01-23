package codingtechniques;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@ComponentScan(basePackages = { "main.java.codingtechniques" })
@SpringBootApplication
public class UserRegistrationApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(UserRegistrationApplication.class, args);
}


}
