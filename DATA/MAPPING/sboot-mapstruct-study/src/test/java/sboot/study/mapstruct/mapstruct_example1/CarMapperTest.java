package sboot.study.mapstruct.mapstruct_example1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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