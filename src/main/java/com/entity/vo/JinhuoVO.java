package com.entity.vo;

import com.entity.JinhuoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 进货
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jinhuo")
public class JinhuoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 进货编号
     */

    @TableField(value = "jinhuo_uuid_number")
    private String jinhuoUuidNumber;


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
     * 进货数量
     */

    @TableField(value = "jinhuo_shuliang_number")
    private Integer jinhuoShuliangNumber;


    /**
     * 备注
     */

    @TableField(value = "jinhuo_content")
    private String jinhuoContent;


    /**
     * 进货时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "jinhuo_time")
    private Date jinhuoTime;


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
	 * 设置：进货编号
	 */
    public String getJinhuoUuidNumber() {
        return jinhuoUuidNumber;
    }


    /**
	 * 获取：进货编号
	 */

    public void setJinhuoUuidNumber(String jinhuoUuidNumber) {
        this.jinhuoUuidNumber = jinhuoUuidNumber;
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
	 * 设置：进货数量
	 */
    public Integer getJinhuoShuliangNumber() {
        return jinhuoShuliangNumber;
    }


    /**
	 * 获取：进货数量
	 */

    public void setJinhuoShuliangNumber(Integer jinhuoShuliangNumber) {
        this.jinhuoShuliangNumber = jinhuoShuliangNumber;
    }
    /**
	 * 设置：备注
	 */
    public String getJinhuoContent() {
        return jinhuoContent;
    }


    /**
	 * 获取：备注
	 */

    public void setJinhuoContent(String jinhuoContent) {
        this.jinhuoContent = jinhuoContent;
    }
    /**
	 * 设置：进货时间
	 */
    public Date getJinhuoTime() {
        return jinhuoTime;
    }


    /**
	 * 获取：进货时间
	 */

    public void setJinhuoTime(Date jinhuoTime) {
        this.jinhuoTime = jinhuoTime;
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
