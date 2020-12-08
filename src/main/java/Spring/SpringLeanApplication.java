package Spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="Spring.Service")
public class SpringLeanApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringLeanApplication.class, args);

    }

}
