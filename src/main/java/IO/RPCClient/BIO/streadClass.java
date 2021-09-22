package IO.RPCClient.BIO;

import IO.RPCClient.BusinessService;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: streadClass
 * @package: IO.RPCClient
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/9/17 11:49 上午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class streadClass {

    public static void main(String[] args){
        BusinessService remoteProxyObj = RPCClient.getRemoteProxyObj(BusinessService.class, "127.0.0.1", 8080);
        remoteProxyObj.doSomething("廖");
    }
}
