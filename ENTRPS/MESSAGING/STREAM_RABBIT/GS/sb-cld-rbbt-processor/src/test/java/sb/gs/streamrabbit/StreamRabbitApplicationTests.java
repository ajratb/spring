package sb.gs.streamrabbit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.CompositeMessageConverter;
import org.springframework.messaging.converter.MessageConverter;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
class StreamRabbitApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testUsageCostProcessor() {
        try (
                ConfigurableApplicationContext context = new SpringApplicationBuilder(
                        TestChannelBinderConfiguration.getCompleteConfiguration(
                                StreamRabbitApplication.class)).web(WebApplicationType.NONE)
                        .run()) {

            InputDestination source = context.getBean(InputDestination.class);

            String inputName = "Robert";

            MessageConverter converter = context.getBean(CompositeMessageConverter.class);
            Map<String, Object> headers = new HashMap<>();
            headers.put("contentType", "application/json");
            MessageHeaders messageHeaders = new MessageHeaders(headers);
            Message<?> message = converter.toMessage(inputName, messageHeaders);

            source.send(message);

            OutputDestination target = context.getBean(OutputDestination.class);
            Message<byte[]> sourceMessage = target.receive(10000, "sinkinput");

            Person person = (Person) converter
                    .fromMessage(sourceMessage, Person.class);

            assertThat(person).isNotNull();
            assertThat(person.name()).isEqualTo("Robert");
            assertThat(person.processedTimestamp()).isNotNull();
        }
    }

}
