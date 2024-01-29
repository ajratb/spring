package sboot.study.mapstruct.example1;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused")
@Component
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SourceTargetMapper {
    @Mapping(target = "targetValue", source = "sourceValue")
    @Mapping(target = "stringFromLongValue", source = "longForStringValue", defaultExpression  = "java(Long.toString())")
    SimpleTarget sourceToTarget(SimpleSource source);
    SimpleSource targetToSource(SimpleTarget destination);
}
