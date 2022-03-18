package demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.concurrent.ExecutionException;

/**
 * https://github.com/lettuce-io/lettuce-core
 *
 * @author cl
 * @date 2022-03-18 21:17:12
 */
public class LettuceDemo {
    public static void main(String[] args) {
        basicUsage();
    }

    private static void basicUsage() {
        RedisClient client = RedisClient.create("redis://localhost");

        StatefulRedisConnection<String, String> connect = client.connect();
        RedisCommands<String, String> sync = connect.sync();

        String value = sync.get("clientName");
        System.out.println(value);
    }

    /**
     * ?
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void asynchronousApi() throws ExecutionException, InterruptedException {
        RedisClient client = RedisClient.create("redis:localhost");

        StatefulRedisConnection<String, String> connect = client.connect();
        RedisAsyncCommands<String, String> async = connect.async();

        RedisFuture<String> set = async.set("key", "value");
        RedisFuture<String> get = async.get("key");

        String s = set.get();
        String s1 = get.get();
    }


}
