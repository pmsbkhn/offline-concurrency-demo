package vn.softwaredesign.offlineconcurrency.layeredservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "vn.softwaredesign")
public class LayeredServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayeredServiceApplication.class, args);
    }

}
