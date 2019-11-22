package io.turntabl.tcmsCustomers.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisConfig {
    public static void redisConfiguration() {
        Jedis jedis = new Jedis(System.getenv("REDIS_URL"));
        try {
            JedisPubSub jedisPubSub = new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    System.out.println("Channel: " + channel + " Message: " + message);
                }

                @Override
                public void onSubscribe(String channel, int subscribedSchannels) {
                    System.out.println("Subscribed to: " + channel);
                }

            };
            jedis.ping();
            jedis.subscribe(jedisPubSub, "customers", "customer", "new_customer", "update", "remove");
            jedis.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
