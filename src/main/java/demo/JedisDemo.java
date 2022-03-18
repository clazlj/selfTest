package demo;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * redis客户端之jedis
 * @author cl
 * @date 2022-03-18 20:36:54
 */
public class JedisDemo {
    public static void main(String[] args) {
//        jedisPool();

        jedisPooled();

        jedisCluster();
    }

    /**
     * 为每个命令使用带资源的try块可能会很麻烦
     */
    private static void jedisPool() {
        //等效于new JedisPool("127.0.0.1", 6379);
        JedisPool pool = new JedisPool();

        //Using a try-with-resources block
        try (Jedis jedis = pool.getResource()) {
            jedis.set("clientName", "Jedis");
        }
    }

    /**
     * 避免JedisPool每次都需要try-with-resources，可以考虑使用JedisPooled
     */
    private static void jedisPooled() {
        JedisPooled jedis = new JedisPooled();

        jedis.sadd("planets", "Venus");
    }

    /**
     * 连接到Redis Clusters
     */
    private static void jedisCluster() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();

        //公司测试环境
        //172.16.250.194:6427
        //172.16.250.194:6426
        //172.16.251.94:6422
        jedisClusterNodes.add(new HostAndPort("172.16.250.194", 6427));
        jedisClusterNodes.add(new HostAndPort("172.16.250.194", 6426));
        jedisClusterNodes.add(new HostAndPort("172.16.250.94", 6422));


        JedisCluster jedis = new JedisCluster(jedisClusterNodes);

        jedis.sadd("planets", "Mars");
    }

}
