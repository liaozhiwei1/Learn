package MySpring.Test;

import MySpring.Spring.LzwApplicationContext;

import java.io.IOException;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-30 13:33:30
 **/
public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        LzwApplicationContext lzwApplicationContext = new LzwApplicationContext(MyConfig.class);
    }
}
