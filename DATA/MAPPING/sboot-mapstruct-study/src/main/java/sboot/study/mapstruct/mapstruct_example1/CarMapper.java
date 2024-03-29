package sboot.study.mapstruct.mapstruct_example1;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@SuppressWarnings("unused")
//@Component
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING//,
        //unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CarMapper {
    void updateTargetFromSource( CarDto source,
                                @MappingTarget Car target
                                 );
}
