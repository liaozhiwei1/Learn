package Dto;

/**
 * @version: 1.0
 * @description:
 * @author: zhiwei.liao
 * @create: 2020-12-18 17:12:11
 **/
public class UserDto {

    private String userName;

    private Integer old;

    public UserDto(String userName, Integer old) {
        this.userName = userName;
        this.old = old;
    }

    public UserDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }
}
