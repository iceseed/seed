package com.ice.seed.common.cache;

import com.ice.seed.common.utils.ProtoStuffSerializerUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.dao.DataAccessException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Redis工具类
 * @author : IceSeed
 * @version : v0.0.1
 * @since : 2018/11/2
 */
public class RedisCache {
    /* spring redis 模版 */
    @Setter
    @Getter
    private RedisTemplate<String, String> redisTemplate;

    /* redis key值 操作前缀 */
    @Setter
    @Getter
    private String prefixname = "cache_";


    /**
     * 存入单个对象
     *
     * @param key
     * @param obj
     * @return
     * @since : 2016年11月17日
     * @author : IceSeed
     */
    public <T> boolean putCache(String key, T obj) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bvalue = ProtoStuffSerializerUtil.serialize(obj);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(bkey, bvalue);
            }
        });
        return result;
    }

    /**
     * 存入单个对象 没有默认前缀
     *
     * @param key
     * @param obj
     * @return
     * @author : IceSeed
     * @since : 2017年7月16日
     */
    public <T> boolean putCacheNotPrefix(String key, T obj) {
        final byte[] bkey = key.getBytes();
        final byte[] bvalue = ProtoStuffSerializerUtil.serialize(obj);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(bkey, bvalue);
            }
        });
        return result;
    }

    /**
     * 存入单个对象并设定 有效时间
     *
     * @param key
     * @param obj
     * @param expireTime
     * @since : 2016年11月17日
     * @author : IceSeed
     */
    public <T> void putCacheWithExpireTime(String key, T obj, final long expireTime) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bvalue = ProtoStuffSerializerUtil.serialize(obj);
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(bkey, expireTime, bvalue);
                return true;
            }
        });
    }



    /**
     * 存入一个List集合
     *
     * @param key
     * @param objList
     * @return
     * @since : 2016年11月17日
     * @author : IceSeed
     */
    public <T> boolean putListCache(String key, List<T> objList) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(bkey, bvalue);
            }
        });
        return result;
    }

    /**
     * 存入一个List集合并设定 有效时间
     *
     * @param key
     * @param objList
     * @param expireTime
     * @return
     * @since : 2016年11月17日
     * @author : IceSeed
     */
    public <T> boolean putListCacheWithExpireTime(String key, List<T> objList, final long expireTime) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(bkey, expireTime, bvalue);
                return true;
            }
        });
        return result;
    }

    /**
     * 获取单个对象
     *
     * @param key
     * @param targetClass
     * @return
     * @since : 2016年11月17日
     * @author : IceSeed
     */
    public <T> T getCache(final String key, Class<T> targetClass) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get((prefixname + key).getBytes());
            }
        });
        if (result == null) {
            return null;
        }
        return ProtoStuffSerializerUtil.deserialize(result, targetClass);
    }

    /**
     * 获取单个对象 不带默认前缀
     *
     * @param key
     * @param targetClass
     * @return
     * @since : 2016年11月17日
     * @author : IceSeed
     */
    public <T> T getCacheNotPrefix(final String key, Class<T> targetClass) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key.getBytes());
            }
        });
        if (result == null) {
            return null;
        }
        return ProtoStuffSerializerUtil.deserialize(result, targetClass);
    }

    /**
     * 获取单个值
     *
     * @param key
     * @return
     * @author : IceSeed
     * @since : 2017年3月30日
     */
    public String getCache(final String key) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get((prefixname + key).getBytes());
            }
        });
        if (result == null) {
            return null;
        }
        return new String(result);
    }

    /**
     * 获取单个值 没有默认前缀
     *
     * @param key
     * @return
     * @author : IceSeed
     * @since : 2017年7月16日
     */
    public String getCacheNotPrefix(final String key) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key.getBytes());
            }
        });
        if (result == null) {
            return null;
        }
        return new String(result);
    }

    /**
     * 获取一个集合
     *
     * @param key
     * @param targetClass
     * @return
     * @since : 2016年11月17日
     * @author : IceSeed
     */
    public <T> List<T> getListCache(final String key, Class<T> targetClass) {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get((prefixname + key).getBytes());
            }
        });
        if (result == null) {
            return null;
        }
        return ProtoStuffSerializerUtil.deserializeList(result, targetClass);
    }

    /**
     * 精确删除key
     *
     * @param key
     * @since : 2016年11月17日
     * @author : IceSeed
     */
    public void deleteCache(String key) {
        redisTemplate.delete((prefixname + key));
    }

    /**
     * 精确删除key 没有默认前缀
     *
     * @param key
     * @author : IceSeed
     * @since : 2017年7月16日
     */
    public void delCacheNotPrefix(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 模糊删除key
     *
     * @param pattern
     * @since : 2016年11月17日
     * @author : IceSeed
     */
    public void deleteCacheWithPattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }

    /**
     * 清空所有缓存
     *
     * @since : 2016年11月17日
     * @author : IceSeed
     */
    public void clearCache() {
        deleteCacheWithPattern(prefixname + "|*");
    }

    /**
     * List-新增一个元素
     *
     * @param key
     * @param values
     * @return List中元素的个数
     * @author : IceSeed
     * @since : 2017年3月24日
     */
    public Long lPush(String key, String values) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bvalue = values.getBytes();
        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.lPush(bkey, bvalue);
            }
        });
        return result;
    }

    /**
     * List-索引首位置插入一个元素
     *
     * @param key
     * @param values
     * @return List中元素的个数
     * @author : IceSeed
     * @since : 2017年3月24日
     */
    public Long lPushX(String key, String values) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bvalue = values.getBytes();
        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.lPushX(bkey, bvalue);
            }
        });
        return result;
    }

    /**
     * List-索引尾位置插入一个元素
     *
     * @param key
     * @param values
     * @return List中元素的个数
     * @author : IceSeed
     * @since : 2017年3月24日
     */
    public Long rPushX(String key, String values) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bvalue = values.getBytes();
        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.rPushX(bkey, bvalue);
            }
        });
        return result;
    }

    /**
     * List-查询索引尾位置的元素
     *
     * @param key
     * @param index
     * @return 元素内容
     * @author : IceSeed
     * @since : 2017年3月24日
     */
    public String lIndex(String key, long index) {
        final byte[] bkey = (prefixname + key).getBytes();
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.lIndex(bkey, index);
            }
        });
        return new String(result);
    }

    /**
     * List-查询元素的个数
     *
     * @param key
     * @return 查询元素的个数
     * @author : IceSeed
     * @since : 2017年3月24日
     */
    public Long lLen(String key) {
        final byte[] bkey = (prefixname + key).getBytes();
        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.lLen(bkey);
            }
        });
        if (result == null) {
            result = 0L;
        }
        return result;
    }

    /**
     * List-移除指定位索引位置的元素
     *
     * @param key
     * @return
     * @author : IceSeed
     * @since : 2017年3月24日
     */
    public String lPop(String key) {
        final byte[] bkey = (prefixname + key).getBytes();
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return new String(connection.lPop(bkey));
            }
        });
        return result;
    }

    /**
     * List-修改指定位索引位置的元素
     *
     * @param key
     * @param index
     * @param values
     * @return
     * @author : IceSeed
     * @since : 2017年9月15日
     */
    public String lset(String key, long index, String values) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bvalue = values.getBytes();
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.lSet(bkey, index, bvalue);
                return "ok";
            }
        });
        return result;
    }

    /**
     * List-设置有效时间
     *
     * @param key
     * @param expireTime
     * @return
     * @author : IceSeed
     * @since : 2017年10月9日
     */
    public String expire(String key, final long expireTime) {
        final byte[] bkey = (prefixname + key).getBytes();
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.expire(bkey, expireTime);
                return "ok";
            }
        });
        return result;
    }

    /**
     * 存入一个Map
     *
     * @param key
     * @param objMap
     * @return
     */
    public <T> boolean putMapCache(String key, Map<String, T> objMap) {
        final byte[] bkey = (prefixname + key).getBytes();
        final Map<byte[], byte[]> bmap = mapToByte(objMap);
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.hMSet(bkey, bmap);
                return true;
            }
        });
        return result;
    }

    /**
     * 通过key获取整个Map
     *
     * @param key
     * @param targetClass
     * @return
     */
    public <T> Map<String, T> getMapCache(String key, Class<T> targetClass) {
        final byte[] bkey = (prefixname + key).getBytes();

        Map<byte[], byte[]> result = redisTemplate.execute(new RedisCallback<Map<byte[], byte[]>>() {
            @Override
            public Map<byte[], byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hGetAll(bkey);
            }
        });
        return byteToMap(result, targetClass);
    }

    /**
     * 获取Map中指定值
     *
     * @param key
     * @param targetClass
     * @return
     */
    public <T> T getMapCache(String key, Class<T> targetClass, String mkey) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bmkey = mkey.getBytes();
        if(hExists(key, mkey)){
            byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
                @Override
                public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.hGet(bkey, bmkey);
                }
            });
            return ProtoStuffSerializerUtil.deserialize(result, targetClass);
        }else {
            return null;
        }

    }


    /**
     * 获取Map中指定个数的Map mkey值必须存在
     * @param key
     * @param targetClass
     * @param mkey
     * @return
     */
    public <T> Map<String, T> getMapsCache(String key, Class<T> targetClass, String... mkey) {
        final byte[] bkey = (prefixname + key).getBytes();
        List<byte[]> bmkeys = new ArrayList<>();

        Map<String, T> map = new HashMap<>();
        for (int i = 0; i < mkey.length; i++) {
            if(hExists(key, mkey[i])) {
                byte[] bmkey = mkey[i].getBytes();
                bmkeys.add(bmkey);
            }
        }

        byte[][] z = new byte[bmkeys.size()][];

        for (int i = 0; i < z.length; i++) {

            byte[] m = bmkeys.get(i);
            z[i] = new byte[m.length];

            for (int j = 0; j < m.length; j++) {
                z[i][j] = m[j];
            }
        }


        List<byte[]> result = redisTemplate.execute(new RedisCallback<List<byte[]>>() {
            @Override
            public List<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hMGet(bkey, z);
            }
        });

        for (int i = 0; i < result.size(); i++) {
            T value = ProtoStuffSerializerUtil.deserialize(result.get(i), targetClass);
            if(value!=null)
                map.put(new String(bmkeys.get(i)), value);
        }


        return map;
    }


    /**
     * 删除指定的mapKey
     * @param key
     * @param mkey
     * @return
     */
    public Long  delMapkey(String key, String mkey) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bmkey = mkey.getBytes();

        Long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hDel(bkey, bmkey);
            }
        });

        return result;
    }


    /**
     * 返回mapkey是否存在
     * @param key
     * @param mkey
     * @return
     */
    public Boolean  hExists(String key, String mkey) {
        final byte[] bkey = (prefixname + key).getBytes();
        final byte[] bmkey = mkey.getBytes();

        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hExists(bkey, bmkey);
            }
        });

        return result;
    }



    /**
     * map转字节码
     *
     * @param map
     * @return
     */
    public <T> Map<byte[], byte[]> mapToByte(Map<String, T> map) {
        Map<byte[], byte[]> bmap = new HashMap<byte[], byte[]>();
        for (Entry<String, T> entry : map.entrySet()) {
            byte[] bkey = entry.getKey().getBytes();
            byte[] bvalue = ProtoStuffSerializerUtil.serialize(entry.getValue());
            bmap.put(bkey, bvalue);
        }
        return bmap;
    }

    /**
     * 字节码Map转Map
     *
     * @param bmap
     * @param targetClass
     * @return
     */
    public <T> Map<String, T> byteToMap(Map<byte[], byte[]> bmap, Class<T> targetClass) {
        Map<String, T> map = new HashMap<String, T>();
        for (Entry<byte[], byte[]> entry : bmap.entrySet()) {
            String key = new String(entry.getKey());
            T value = ProtoStuffSerializerUtil.deserialize(entry.getValue(), targetClass);
            map.put(key, value);
        }
        return map;
    }

}
