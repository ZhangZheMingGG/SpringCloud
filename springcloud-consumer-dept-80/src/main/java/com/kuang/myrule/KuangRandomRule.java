package com.kuang.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class KuangRandomRule extends AbstractLoadBalancerRule {

    //  每个服务，访问5次，换下一个服务（3 个）
    // total = 0,默认0，如果5，我们指向下一个服务节点
    // index=0，默认0如果total=5，index+1

    private int total = 0; // 被调用的次数
    private int currentIndex = 0;  // 当前是谁在提供服务

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            // 获得活着的服务
            List<Server> upList = lb.getReachableServers();
            // 获取所有的服务
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }

            // 生产区间随机数
//            int index = chooseRandomInt(serverCount);
//            // 从活着的服务中，随机获取一个
//            server = upList.get(index);
            //================================================================
            if (total <5){
                server = upList.get(currentIndex);
                total++ ;
            }else {
                total = 0;
                currentIndex++ ;
                if (currentIndex>upList.size()){
                    currentIndex = 0;
                }
                server = upList.get(total);
            }
            //================================================================
            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }
}
