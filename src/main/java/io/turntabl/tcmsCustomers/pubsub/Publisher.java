package io.turntabl.tcmsCustomers.pubsub;

import redis.clients.jedis.Jedis;

public class Publisher {
    public static void publis(String channel, String message){
        Jedis jedis = new Jedis(System.getenv("REDIS_URL"));

        jedis.publish(channel, message);
        jedis.ping();
        jedis.close();
    }
}
