package sb.jpa.criteria;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UuidTest {

    @Test void testEquals(){
        UUID uuid1 = UUID.fromString("e8ae610f-b887-4faa-950e-28d05090f8cb");
        UUID uuid2 = UUID.fromString("e8ae610f-b887-4faa-950e-28d05090f8cb");
        Assertions.assertThat(uuid1).isEqualTo(uuid2);
    }
}
