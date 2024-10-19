package com.entity.model;

import com.entity.YaopinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 药品
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YaopinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 供应商
     */
    private Integer gongyingshangId;


    /**
     * 药品编号
     */
    private String yaopinUuidNumber;


    /**
     * 药品名称
     */
    private String yaopinName;


    /**
     * 药品功效
     */
    private String yaopinGongxiao;


    /**
     * 所治疾病
     */
    private String yaopinJibing;


    /**
     * 药品照片
     */
    private String yaopinPhoto;


    /**
     * 药品类型
     */
    private Integer yaopinTypes;


    /**
     * 药品库存
     */
    private Integer yaopinKucunNumber;


    /**
     * 进价
     */
    private Double yaopinJinjiaMoney;


    /**
     * 售价
     */
    private Double yaopinXiaoshouMoney;


    /**
     * 药品介绍
     */
    private String yaopinContent;


    /**
     * 逻辑删除
     */
    private Integer yaopinDelete;


    /**
     * 创建时间 show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：供应商
	 */
    public Integer getGongyingshangId() {
        return gongyingshangId;
    }


    /**
	 * 设置：供应商
	 */
    public void setGongyingshangId(Integer gongyingshangId) {
        this.gongyingshangId = gongyingshangId;
    }
    /**
	 * 获取：药品编号
	 */
    public String getYaopinUuidNumber() {
        return yaopinUuidNumber;
    }


    /**
	 * 设置：药品编号
	 */
    public void setYaopinUuidNumber(String yaopinUuidNumber) {
        this.yaopinUuidNumber = yaopinUuidNumber;
    }
    /**
	 * 获取：药品名称
	 */
    public String getYaopinName() {
        return yaopinName;
    }


    /**
	 * 设置：药品名称
	 */
    public void setYaopinName(String yaopinName) {
        this.yaopinName = yaopinName;
    }
    /**
	 * 获取：药品功效
	 */
    public String getYaopinGongxiao() {
        return yaopinGongxiao;
    }


    /**
	 * 设置：药品功效
	 */
    public void setYaopinGongxiao(String yaopinGongxiao) {
        this.yaopinGongxiao = yaopinGongxiao;
    }
    /**
	 * 获取：所治疾病
	 */
    public String getYaopinJibing() {
        return yaopinJibing;
    }


    /**
	 * 设置：所治疾病
	 */
    public void setYaopinJibing(String yaopinJibing) {
        this.yaopinJibing = yaopinJibing;
    }
    /**
	 * 获取：药品照片
	 */
    public String getYaopinPhoto() {
        return yaopinPhoto;
    }


    /**
	 * 设置：药品照片
	 */
    public void setYaopinPhoto(String yaopinPhoto) {
        this.yaopinPhoto = yaopinPhoto;
    }
    /**
	 * 获取：药品类型
	 */
    public Integer getYaopinTypes() {
        return yaopinTypes;
    }


    /**
	 * 设置：药品类型
	 */
    public void setYaopinTypes(Integer yaopinTypes) {
        this.yaopinTypes = yaopinTypes;
    }
    /**
	 * 获取：药品库存
	 */
    public Integer getYaopinKucunNumber() {
        return yaopinKucunNumber;
    }


    /**
	 * 设置：药品库存
	 */
    public void setYaopinKucunNumber(Integer yaopinKucunNumber) {
        this.yaopinKucunNumber = yaopinKucunNumber;
    }
    /**
	 * 获取：进价
	 */
    public Double getYaopinJinjiaMoney() {
        return yaopinJinjiaMoney;
    }


    /**
	 * 设置：进价
	 */
    public void setYaopinJinjiaMoney(Double yaopinJinjiaMoney) {
        this.yaopinJinjiaMoney = yaopinJinjiaMoney;
    }
    /**
	 * 获取：售价
	 */
    public Double getYaopinXiaoshouMoney() {
        return yaopinXiaoshouMoney;
    }


    /**
	 * 设置：售价
	 */
    public void setYaopinXiaoshouMoney(Double yaopinXiaoshouMoney) {
        this.yaopinXiaoshouMoney = yaopinXiaoshouMoney;
    }
    /**
	 * 获取：药品介绍
	 */
    public String getYaopinContent() {
        return yaopinContent;
    }


    /**
	 * 设置：药品介绍
	 */
    public void setYaopinContent(String yaopinContent) {
        this.yaopinContent = yaopinContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getYaopinDelete() {
        return yaopinDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setYaopinDelete(Integer yaopinDelete) {
        this.yaopinDelete = yaopinDelete;
    }
    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
