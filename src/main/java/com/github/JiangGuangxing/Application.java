package com.github.JiangGuangxing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

import java.io.IOException;

/**
 * @author 姜广兴
 * @since 2017/11/1
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    private Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new ApplicationPidFileWriter("./app.pid"));
        app.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("服务启动完成!");
    }
}
