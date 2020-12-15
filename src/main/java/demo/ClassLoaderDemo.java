package demo;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        //AppClassLoader: 应用程序的加载器
        ClassLoader cl1 = ClassLoader.getSystemClassLoader();
        System.out.println(cl1);

        //ExtClassLoader :扩展类加载器。JKD1.9之后为PlatformClassLoader：平台类加载器
        //在JDK1.8及以前的版本里面提供的加载器为"ExtClassLoader",
        //因为在JDK的安装目录里面提供有一个ext目录,开发者可以将*.jar文件拷贝到此目录里面,
        //这样就可以直接执行了,但是这样的处理并不安全.
        //最初的时候也是不提倡使用的.所以在JDK9开始就将这样的操作彻底废除了,
        //同时为了与系统类加载器和应用类加载器之间保持设计的平衡,提供有平台类加载器.
        ClassLoader cl2 = cl1.getParent();
        System.out.println(cl2);

        //BootStrapClassLoader: 根平台加载器,系统类加载器.引导类加载器，又称启动类加载器
        //是最顶层的类加载器，主要用来加载Java核心类，如rt.jar、resources.jar、charsets.jar等
        //BootStrapClassLoader 是一个纯的C++实现，没有对应的Java类。
        // 所以在Java中是取不到的。如果一个类的classloader是null。已经足可以证明他就是由BootStrapClassLoader 加载的
        ClassLoader cl3 = cl2.getParent();
        System.out.println(cl3); //输出null
    }
}
