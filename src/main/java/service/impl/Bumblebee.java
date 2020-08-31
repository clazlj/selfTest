package service.impl;

import service.Robot;

public class Bumblebee implements Robot {
    @Override
    public void sayHello() {
        System.out.println("Hello,I am Bumblebee");
    }
}
