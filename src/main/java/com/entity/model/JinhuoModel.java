package com.entity.model;

import com.entity.JinhuoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 进货
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JinhuoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 进货编号
     */
    private String jinhuoUuidNumber;


    /**
     * 药品
     */
    private Integer yaopinId;


    /**
     * 员工
     */
    private Integer yuangongId;


    /**
     * 进货数量
     */
    private Integer jinhuoShuliangNumber;


    /**
     * 备注
     */
    private String jinhuoContent;


    /**
     * 进货时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date jinhuoTime;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：进货编号
	 */
    public String getJinhuoUuidNumber() {
        return jinhuoUuidNumber;
    }


    /**
	 * 设置：进货编号
	 */
    public void setJinhuoUuidNumber(String jinhuoUuidNumber) {
        this.jinhuoUuidNumber = jinhuoUuidNumber;
    }
    /**
	 * 获取：药品
	 */
    public Integer getYaopinId() {
        return yaopinId;
    }


    /**
	 * 设置：药品
	 */
    public void setYaopinId(Integer yaopinId) {
        this.yaopinId = yaopinId;
    }
    /**
	 * 获取：员工
	 */
    public Integer getYuangongId() {
        return yuangongId;
    }


    /**
	 * 设置：员工
	 */
    public void setYuangongId(Integer yuangongId) {
        this.yuangongId = yuangongId;
    }
    /**
	 * 获取：进货数量
	 */
    public Integer getJinhuoShuliangNumber() {
        return jinhuoShuliangNumber;
    }


    /**
	 * 设置：进货数量
	 */
    public void setJinhuoShuliangNumber(Integer jinhuoShuliangNumber) {
        this.jinhuoShuliangNumber = jinhuoShuliangNumber;
    }
    /**
	 * 获取：备注
	 */
    public String getJinhuoContent() {
        return jinhuoContent;
    }


    /**
	 * 设置：备注
	 */
    public void setJinhuoContent(String jinhuoContent) {
        this.jinhuoContent = jinhuoContent;
    }
    /**
	 * 获取：进货时间
	 */
    public Date getJinhuoTime() {
        return jinhuoTime;
    }


    /**
	 * 设置：进货时间
	 */
    public void setJinhuoTime(Date jinhuoTime) {
        this.jinhuoTime = jinhuoTime;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
