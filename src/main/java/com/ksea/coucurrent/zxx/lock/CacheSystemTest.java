package com.ksea.coucurrent.zxx.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ksea on 2017/8/19.
 * 设计一个缓存系统，在高并发下的情况下，实现其线程安全
 */
public class CacheSystemTest {

    //数据缓存容器
    private Map cache = new HashMap<String, Object>();

    //读写锁互斥锁
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //获取缓存系统中的数据
    public Object get(String key) {

        Object value = null;

        try {

            //加读锁
            readWriteLock.readLock().lock();

            value = cache.get(key);

            if (null == value) {

                //释放读锁
                readWriteLock.readLock().unlock();
                //为此时写入数据加上写锁
                readWriteLock.writeLock().lock();


                //再次判定其数据是否为空

                if (null == value) {

                    //如果缓存系统不存在，就去数据库中查询，并将数据放到缓存中,否者直接还回结果
                    value = "从数据库查询的结果";
                    cache.put(key, value);
                }


                //写完数据之后释放写锁，并重新加上读锁
                readWriteLock.writeLock().unlock();
                readWriteLock.readLock().lock();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放读锁
            readWriteLock.readLock().unlock();
        }


        return value;
    }

}
