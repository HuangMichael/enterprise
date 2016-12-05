
package com.novsky;

import com.novsky.liseners.AppilicationSessionExpire;
import com.novsky.liseners.AppilicationStartUp;
import com.novsky.liseners.AppilicationStopped;
import com.novsky.liseners.AppilicationStartUp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 *
 */
@EnableTransactionManagement //启用事务管理
@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableScheduling

public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new AppilicationStartUp());
        app.addListeners(new AppilicationSessionExpire());
        app.addListeners(new AppilicationStopped());
        app.run(args);
    }
}
