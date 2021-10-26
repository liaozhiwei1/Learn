package MyBatis;

import java.util.Date;
import java.util.Objects;

/**
 * All rights Reserved, Designed By www.baozun.com
 *
 * @title: dto
 * @package: MyBatis
 * @description: (用一句话描述该文件做什么)
 * @author: xugejun(giorno.xu @ baozun.com)
 * @date: 2021/10/18 5:01 下午
 * @version: v1.0
 * @copyright: 2021 www.baozun.com Inc. All rights reserved.
 * 注意：本内容仅限于上海宝尊电商内部传阅，禁止外泄以及用于其他的商业目的
 */
public class dto {

    private Integer id;
    private String 	templateName;
    private String  type;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
    private String brand;

    public String getBrand(){
        return templateName.contains("-") ? templateName.substring(0,templateName.indexOf('-')): null ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, templateName, type, createTime, updateTime, createBy, updateBy, brand);
    }
}
