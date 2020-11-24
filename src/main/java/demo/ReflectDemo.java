package demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        newByClass();

        newByConstructor();
    }

    private static void newByConstructor() throws ClassNotFoundException, NoSuchMethodException {
        Class<?> targetClass = Class.forName("demo.TargetObject");
        Constructor<?> constructor = targetClass.getDeclaredConstructor(String.class);
        try {
            TargetObject targetObject = (TargetObject) constructor.newInstance("fg");
            System.out.println(targetObject);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void newByClass() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        //获取类的Class对象并创建其实例
        Class<?> targetClass = Class.forName("demo.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.newInstance();

        //获取类中所有定义的方法
        Method[] methods = targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        //获取指定方法并调用
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject, "freedom");

        //获取指定参数并对参数进行修改
        Field field = targetClass.getDeclaredField("value");
        //取消安全检查：为了对类中的参数进行修改
        field.setAccessible(true);
        field.set(targetObject, "yami");

        //private方法
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        //取消安全检查：为了调用private方法
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);
    }
}

class TargetObject{
    private String value;

    public TargetObject() {
        this.value = "JavaGuide";
    }

    public TargetObject(String value) {
        this.value = value;
    }

    public void publicMethod(String str) {
        System.out.println("I love " + str);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }

    @Override
    public String toString() {
        return "TargetObject{" +
                "value='" + value + '\'' +
                '}';
    }
}
