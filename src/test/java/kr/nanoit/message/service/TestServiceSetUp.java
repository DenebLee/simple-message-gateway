package kr.nanoit.message.service;

import kr.nanoit.repository.ReceivedMessageRepository;
import kr.nanoit.repository.SendToTelecomMessageRepository;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.io.IOException;
import java.util.Properties;


public class TestServiceSetUp {
    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:14.5-alpine");
    Properties properties;

    public static ReceivedMessageRepository receivedMessageRepository;
    public static SendToTelecomMessageRepository sendToTelecomMessageRepository;

    public TestServiceSetUp(String type) throws IOException {
        properties = new Properties();
        properties.setProperty("driver", postgreSQLContainer.getDriverClassName());
        properties.setProperty("url", postgreSQLContainer.getJdbcUrl());
        properties.setProperty("username", postgreSQLContainer.getUsername());
        properties.setProperty("password", postgreSQLContainer.getPassword());
        properties.setProperty("mapper", type + "_POSTGRESQL.xml");

        receivedMessageRepository = ReceivedMessageRepository.createReceiveRepository(properties);
        sendToTelecomMessageRepository = SendToTelecomMessageRepository.createSendMybatis(properties);
    }
}
