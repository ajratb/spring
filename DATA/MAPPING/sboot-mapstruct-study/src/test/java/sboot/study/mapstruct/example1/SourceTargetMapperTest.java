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
    void testSourceToTarget() {
        SimpleSource source = new SimpleSource(
                "sourceName", "sourceDesc", "sourceValue", 23L);
        SimpleTarget target = simpleSourceTargetMapper.sourceToTarget(source);
        assertThat(target.getName()).isEqualTo(source.getName());
        assertThat(target.getDescription()).isEqualTo(source.getDescription());
        assertThat(target.getTargetValue()).isEqualTo(source.getSourceValue());
        assertThat(target.getStringFromLongValue()).isEqualTo(String.valueOf(source.getLongForStringValue()));
    }

    @Test
    void testTargetToSource() {
        SimpleTarget target = new SimpleTarget(
                "targetName", "targetDesc", "targetValue", "23");
        SimpleSource source = simpleSourceTargetMapper.targetToSource(target);
        assertThat(source.getName()).isEqualTo(target.getName());
        assertThat(source.getDescription()).isEqualTo(target.getDescription());
        assertThat(source.getSourceValue()).isEqualTo(target.getTargetValue());
        assertThat(source.getLongForStringValue()).isEqualTo(Long.parseLong(target.getStringFromLongValue()));
    }

//    @Test
//    void testUpdateTargetFromSource(){
//        SimpleSource source = new SimpleSource(
//                "sourceName", "sourceDesc", "sourceValue", 23L);
//        SimpleTarget target = new SimpleTarget(
//                "targetName", "targetDesc", "targetValue", "23");
//        SimpleTarget newTarget = new SimpleTarget(
//                "newName", "newDesc", "newValue", "32");
//        simpleSourceTargetMapper.updateTargetFromSource(source, target);
//        assertThat(target.getName()).isEqualTo(source.getName());
//        assertThat(target.getDescription()).isEqualTo(source.getDescription());
//        assertThat(target.getTargetValue()).isEqualTo(source.getSourceValue());
//        assertThat(target.getStringFromLongValue()).isEqualTo(String.valueOf(source.getLongForStringValue()));
//    }
}