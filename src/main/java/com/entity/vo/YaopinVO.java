package com.entity.vo;

import com.entity.YaopinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 药品
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yaopin")
public class YaopinVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 供应商
     */

    @TableField(value = "gongyingshang_id")
    private Integer gongyingshangId;


    /**
     * 药品编号
     */

    @TableField(value = "yaopin_uuid_number")
    private String yaopinUuidNumber;


    /**
     * 药品名称
     */

    @TableField(value = "yaopin_name")
    private String yaopinName;


    /**
     * 药品功效
     */

    @TableField(value = "yaopin_gongxiao")
    private String yaopinGongxiao;


    /**
     * 所治疾病
     */

    @TableField(value = "yaopin_jibing")
    private String yaopinJibing;


    /**
     * 药品照片
     */

    @TableField(value = "yaopin_photo")
    private String yaopinPhoto;


    /**
     * 药品类型
     */

    @TableField(value = "yaopin_types")
    private Integer yaopinTypes;


    /**
     * 药品库存
     */

    @TableField(value = "yaopin_kucun_number")
    private Integer yaopinKucunNumber;


    /**
     * 进价
     */

    @TableField(value = "yaopin_jinjia_money")
    private Double yaopinJinjiaMoney;


    /**
     * 售价
     */

    @TableField(value = "yaopin_xiaoshou_money")
    private Double yaopinXiaoshouMoney;


    /**
     * 药品介绍
     */

    @TableField(value = "yaopin_content")
    private String yaopinContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "yaopin_delete")
    private Integer yaopinDelete;


    /**
     * 创建时间 show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：供应商
	 */
    public Integer getGongyingshangId() {
        return gongyingshangId;
    }


    /**
	 * 获取：供应商
	 */

    public void setGongyingshangId(Integer gongyingshangId) {
        this.gongyingshangId = gongyingshangId;
    }
    /**
	 * 设置：药品编号
	 */
    public String getYaopinUuidNumber() {
        return yaopinUuidNumber;
    }


    /**
	 * 获取：药品编号
	 */

    public void setYaopinUuidNumber(String yaopinUuidNumber) {
        this.yaopinUuidNumber = yaopinUuidNumber;
    }
    /**
	 * 设置：药品名称
	 */
    public String getYaopinName() {
        return yaopinName;
    }


    /**
	 * 获取：药品名称
	 */

    public void setYaopinName(String yaopinName) {
        this.yaopinName = yaopinName;
    }
    /**
	 * 设置：药品功效
	 */
    public String getYaopinGongxiao() {
        return yaopinGongxiao;
    }


    /**
	 * 获取：药品功效
	 */

    public void setYaopinGongxiao(String yaopinGongxiao) {
        this.yaopinGongxiao = yaopinGongxiao;
    }
    /**
	 * 设置：所治疾病
	 */
    public String getYaopinJibing() {
        return yaopinJibing;
    }


    /**
	 * 获取：所治疾病
	 */

    public void setYaopinJibing(String yaopinJibing) {
        this.yaopinJibing = yaopinJibing;
    }
    /**
	 * 设置：药品照片
	 */
    public String getYaopinPhoto() {
        return yaopinPhoto;
    }


    /**
	 * 获取：药品照片
	 */

    public void setYaopinPhoto(String yaopinPhoto) {
        this.yaopinPhoto = yaopinPhoto;
    }
    /**
	 * 设置：药品类型
	 */
    public Integer getYaopinTypes() {
        return yaopinTypes;
    }


    /**
	 * 获取：药品类型
	 */

    public void setYaopinTypes(Integer yaopinTypes) {
        this.yaopinTypes = yaopinTypes;
    }
    /**
	 * 设置：药品库存
	 */
    public Integer getYaopinKucunNumber() {
        return yaopinKucunNumber;
    }


    /**
	 * 获取：药品库存
	 */

    public void setYaopinKucunNumber(Integer yaopinKucunNumber) {
        this.yaopinKucunNumber = yaopinKucunNumber;
    }
    /**
	 * 设置：进价
	 */
    public Double getYaopinJinjiaMoney() {
        return yaopinJinjiaMoney;
    }


    /**
	 * 获取：进价
	 */

    public void setYaopinJinjiaMoney(Double yaopinJinjiaMoney) {
        this.yaopinJinjiaMoney = yaopinJinjiaMoney;
    }
    /**
	 * 设置：售价
	 */
    public Double getYaopinXiaoshouMoney() {
        return yaopinXiaoshouMoney;
    }


    /**
	 * 获取：售价
	 */

    public void setYaopinXiaoshouMoney(Double yaopinXiaoshouMoney) {
        this.yaopinXiaoshouMoney = yaopinXiaoshouMoney;
    }
    /**
	 * 设置：药品介绍
	 */
    public String getYaopinContent() {
        return yaopinContent;
    }


    /**
	 * 获取：药品介绍
	 */

    public void setYaopinContent(String yaopinContent) {
        this.yaopinContent = yaopinContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getYaopinDelete() {
        return yaopinDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setYaopinDelete(Integer yaopinDelete) {
        this.yaopinDelete = yaopinDelete;
    }
    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
