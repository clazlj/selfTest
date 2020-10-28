package tricks;

/**
 * 一种单例模式
 * 懒汉式
 */
public final class SingletonClass {
    private SingletonClass() {
    }

    /**
     * 静态内部类
     */
    private static class LazyHolder {
        /**
         * jvm保证线程安全
         */
        static SingletonClass instance = new SingletonClass();
    }

    public static SingletonClass getInstance() {
        return LazyHolder.instance;
    }
}
