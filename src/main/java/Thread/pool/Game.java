package Thread.pool;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: Game
 * @package: Thread.pool
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/7/7 2:44 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class Game {

    public void playGame() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("玩到游戏了！");
    }
}
