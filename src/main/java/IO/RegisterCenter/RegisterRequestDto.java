package IO.RegisterCenter;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: RegisterRequestDto
 * @package: IO.RegisterCenter
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/11/3 2:10 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class RegisterRequestDto {

    private String methodName;

    private String serviceName;

    private String ip;

    private int prod;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getProd() {
        return prod;
    }

    public void setProd(int prod) {
        this.prod = prod;
    }


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public RegisterRequestDto(String methodName, String serviceName) {
        this.methodName = methodName;
        this.serviceName = serviceName;
    }

    public RegisterRequestDto(String methodName, String serviceName, String ip, int prod) {
        this.methodName = methodName;
        this.serviceName = serviceName;
        this.ip = ip;
        this.prod = prod;
    }
}
