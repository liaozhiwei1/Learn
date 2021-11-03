package IO.RPCClient.BIO;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: ClassName
 * @package: IO.RPCClient.BIO
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/11/3 5:06 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassName {

    public String value();
}
