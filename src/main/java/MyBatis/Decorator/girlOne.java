package MyBatis.Decorator;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: beasGirl
 * @package: MyBatis.Decorator
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/10/11 5:09 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class girlOne implements girl {

    private girl girl;

    public girlOne() {
    }

    public girlOne(MyBatis.Decorator.girl girl) {
        this.girl = girl;
    }

    @Override
    public void dance() {
        if (girl!=null){
            girl.dance();
        }
        System.out.println("girlOne");
    }
}
