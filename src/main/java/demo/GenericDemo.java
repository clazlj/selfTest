package demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Java的泛型是伪泛型。
 * Java在编译期间，所有的泛型信息都会被擦掉。
 * 这就是通常所说的泛型擦除。
 * 可以理解为，Java集合中的泛型，是用于防止错误类型元素输入的。
 */
public class GenericDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        compareClass();

        addElement();

        getSuperClass();

        unBoundedWildCardType();
    }

    private static void compareClass() {
        List<Integer> intList = new ArrayList<Integer>();
        List<String> strList = new ArrayList<String>();

        Class<? extends List> intListClass = intList.getClass();
        Class<? extends List> strListClass = strList.getClass();

        System.out.println("intListClass与strListClass" + (intListClass == strListClass ? "相等" : "不相等"));
    }

    private static void addElement() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
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

    /**
     * 在父类是泛型类型的情况下，编译器就必须把类型T(该例中的Integer)保存到子类的class文件中，不然编译器就不知道子类(该例中的SubArrayList)只能存取T这种类型
     * 在继承了泛型类型的情况下，子类可以获取父类的泛型类型
     */
    private static void getSuperClass() {
        //除非指定SubArrayList<String>，否则编译报错
        /*SubArrayList subArrayList = new SubArrayList();
        subArrayList.add("55");*/

        Class<SubArrayList> clazz = SubArrayList.class;

        Type t = clazz.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;
            //可能有多个泛型类型
            Type[] types = pt.getActualTypeArguments();
            Type firstType = types[0];
            Class<?> typeClass = (Class<?>) firstType;
            System.out.println(typeClass);
        }
    }

    public static class SubArrayList extends ArrayList<Integer> {


    }

    /**
     * C<?>是C<T>的超类，如List<?>是List<Integer>的超类
     */
    private static void unBoundedWildCardType() {
        List<Integer> intList = new ArrayList<>();
        intList.add(52);
        intList.add(36);

        List<?> wildCardList = intList;

        for (Object o : wildCardList) {
            System.out.println(o);
        }
    }

}
