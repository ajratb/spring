package sboot.study.mapstruct.baeldung_child_beans;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING//,
        //unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EmployeeMapper {
    @Mapping(target = "employeeId", source = "entity.id")
    @Mapping(target = "employeeName", source = "entity.name")
    EmployeeDto employeeToEmployeeDto(Employee entity);

    @Mapping(target = "id", source = "dto.employeeId")
    @Mapping(target = "name", source = "dto.employeeName")
    Employee employeeDTOtoEmployee(EmployeeDto dto);
}
