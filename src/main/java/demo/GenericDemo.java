package demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Java的泛型是伪泛型。
 * Java在编译期间，所有的泛型信息都会被擦掉。
 * 这就是通常所说的泛型擦除。
 */
public class GenericDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList<>();
        list.add(3);

        //直接添加报错
        // list.add("5");

        //通过反射添加
        Class<? extends List> clazz = list.getClass();
        Method add = clazz.getDeclaredMethod("add", Object.class);
        add.invoke(list, "5");

        System.out.println(list);
    }
}
