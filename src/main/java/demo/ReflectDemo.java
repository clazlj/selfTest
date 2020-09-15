package demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
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

    public void publicMethod(String str) {
        System.out.println("I love " + str);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }
}
