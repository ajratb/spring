package sboot.study.mapstruct.example4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarMapperTest {

    @Autowired CarMapper carMapper;

    @Test
    void testUpdate(){
        Car car = new Car();
        car.setName("Car name");
        CarDto carDto = new CarDto();
        carDto.setName("Dto Car Name");
        carMapper.updateTargetFromSource(carDto, car);
        assertThat(car.getName()).isEqualTo(carDto.getName());
    }


}