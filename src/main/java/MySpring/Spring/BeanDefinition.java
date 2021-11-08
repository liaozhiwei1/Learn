package MySpring.Spring;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: BeanDefintion
 * @package: MySpring.Spring
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/11/8 4:12 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class BeanDefinition {

    private Class type;
    private String scope;
    private boolean lzay;
    private String beanName;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isLzay() {
        return lzay;
    }

    public void setLzay(boolean lzay) {
        this.lzay = lzay;
    }
}
