package com.demo.tdd.repository;

import com.demo.tdd.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findByName_returnsCar() throws Exception {
        Car savedCar = entityManager.persist(new Car("prius", "hybrid"));
        Car car = repository.findByName("prius");


        assertThat(car.getName()).isEqualTo(savedCar.getName());
        assertThat(car.getType()).isEqualTo(savedCar.getType());

        car.setType("second");
        savedCar = entityManager.persistFlushFind(car);
        car = repository.findByName("prius");

        System.out.println("Type: " + car.getType());

        assertThat(car.getType()).isEqualTo(savedCar.getType());

    }

}