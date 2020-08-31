package org.self.test;

import org.junit.Test;
import service.Robot;

import java.util.ServiceLoader;

public class JavaSPITest {

    @Test
    public void sayHello() {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}
