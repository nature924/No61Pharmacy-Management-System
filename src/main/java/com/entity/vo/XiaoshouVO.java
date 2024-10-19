package com.entity.vo;

import com.entity.XiaoshouEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 销售
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xiaoshou")
public class XiaoshouVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 销售编号
     */

    @TableField(value = "xiaoshou_uuid_number")
    private String xiaoshouUuidNumber;


    /**
     * 药品
     */

    @TableField(value = "yaopin_id")
    private Integer yaopinId;


    /**
     * 员工
     */

    @TableField(value = "yuangong_id")
    private Integer yuangongId;


    /**
     * 销售数量
     */

    @TableField(value = "xiaoshou_shuliang_number")
    private Integer xiaoshouShuliangNumber;


    /**
     * 备注
     */

    @TableField(value = "xiaoshou_content")
    private String xiaoshouContent;


    /**
     * 销售时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "xiaoshou_time")
    private Date xiaoshouTime;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
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
	 * 设置：销售编号
	 */
    public String getXiaoshouUuidNumber() {
        return xiaoshouUuidNumber;
    }


    /**
	 * 获取：销售编号
	 */

    public void setXiaoshouUuidNumber(String xiaoshouUuidNumber) {
        this.xiaoshouUuidNumber = xiaoshouUuidNumber;
    }
    /**
	 * 设置：药品
	 */
    public Integer getYaopinId() {
        return yaopinId;
    }


    /**
	 * 获取：药品
	 */

    public void setYaopinId(Integer yaopinId) {
        this.yaopinId = yaopinId;
    }
    /**
	 * 设置：员工
	 */
    public Integer getYuangongId() {
        return yuangongId;
    }


    /**
	 * 获取：员工
	 */

    public void setYuangongId(Integer yuangongId) {
        this.yuangongId = yuangongId;
    }
    /**
	 * 设置：销售数量
	 */
    public Integer getXiaoshouShuliangNumber() {
        return xiaoshouShuliangNumber;
    }


    /**
	 * 获取：销售数量
	 */

    public void setXiaoshouShuliangNumber(Integer xiaoshouShuliangNumber) {
        this.xiaoshouShuliangNumber = xiaoshouShuliangNumber;
    }
    /**
	 * 设置：备注
	 */
    public String getXiaoshouContent() {
        return xiaoshouContent;
    }


    /**
	 * 获取：备注
	 */

    public void setXiaoshouContent(String xiaoshouContent) {
        this.xiaoshouContent = xiaoshouContent;
    }
    /**
	 * 设置：销售时间
	 */
    public Date getXiaoshouTime() {
        return xiaoshouTime;
    }


    /**
	 * 获取：销售时间
	 */

    public void setXiaoshouTime(Date xiaoshouTime) {
        this.xiaoshouTime = xiaoshouTime;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
