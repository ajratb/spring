package sboot.study.mapstruct.example1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SourceTargetMapperTest {

    @Autowired
    SourceTargetMapper simpleSourceTargetMapper;

    @Test
    void testSourceToTarget(){
//        simpleSourceTargetMapper = new
        SimpleSource source = new SimpleSource(
                "sourceName", "sourceDesc", "sourceValue", 23L);
        SimpleTarget target = simpleSourceTargetMapper.sourceToTarget(source);
        assertThat(target.name()).isEqualTo(source.name());
    }


}