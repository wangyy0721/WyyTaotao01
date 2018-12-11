package com.taotao.rest.dao;

import java.util.Set;

public interface JedisClient {
        String get(String key);
        String set(String key,String value);
        String hget(String hkey,String key);
        long hset(String hkey,String key,String value);
        long incr(String key);//自增长
        long expire(String key,int second);//设置过期时间
        long ttl(String key);//查看是否过期 -1 正常，-2过期
        long del(String key);//清空缓存
		long hdel(String hkey, String key);
		
		Set<String> keys(String string);//批量获取keys的模糊值
		
}
