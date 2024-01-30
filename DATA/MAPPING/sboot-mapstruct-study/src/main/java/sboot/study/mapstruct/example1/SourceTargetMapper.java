package sboot.study.mapstruct.example1;

import org.mapstruct.*;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SourceTargetMapper {
    @Mapping(target = "targetValue", source = "sourceValue")
    @Mapping(target = "stringFromLongValue", source = "longForStringValue", defaultExpression = "java(Long.toString())")
    SimpleTarget sourceToTarget(SimpleSource source);

    @InheritInverseConfiguration
    SimpleSource targetToSource(SimpleTarget destination);

//    @Mapping(target = "targetValue", source = "sourceValue")
//    @Mapping(target = "stringFromLongValue", source = "longForStringValue", defaultExpression = "java(Long.toString())")
//@InheritInverseConfiguration
//@Mapping(target = "targetValue", ignore = true)
//@Mapping(target = "stringFromLongValue", ignore = true)
//    void updateTargetFromSource( SimpleSource source,
//                                 @MappingTarget SimpleTarget target);
}
