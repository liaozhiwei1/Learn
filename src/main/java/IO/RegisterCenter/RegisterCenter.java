package IO.RegisterCenter;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: RegisterCenter
 * @package: IO.RegisterCenter
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/11/3 10:20 上午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class RegisterCenter {

    //保存服务信息
    private Map<String, Set<ServiceInfo>> serviceMap = new HashMap<>();

    /**
     * 注册服务
     */
    public synchronized void register(String serviceName,ServiceInfo serviceInfo){
        Set<ServiceInfo> orDefault = serviceMap.getOrDefault(serviceName, new HashSet<>());
        orDefault.add(serviceInfo);
        serviceMap.put(serviceName,orDefault);
        System.out.println("服务"+ serviceName+ "注册信息"+ JSONObject.toJSONString(serviceInfo));
    }


    /**
     * 获取服务信息
     * @return
     */
    public Set<ServiceInfo> getServiceInfo(String serviceName){
        return serviceMap.getOrDefault(serviceName,new HashSet<>());
    }


}
