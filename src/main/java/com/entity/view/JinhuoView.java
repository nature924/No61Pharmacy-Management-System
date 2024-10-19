package com.entity.view;

import com.entity.JinhuoEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 进货
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("jinhuo")
public class JinhuoView extends JinhuoEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 yaopin
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
				* 药品类型的值
				*/
				private String yaopinValue;
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

		//级联表 yuangong
			/**
			* 员工姓名
			*/
			private String yuangongName;
			/**
			* 员工手机号
			*/
			private String yuangongPhone;
			/**
			* 员工身份证号
			*/
			private String yuangongIdNumber;
			/**
			* 员工头像
			*/
			private String yuangongPhoto;
			/**
			* 电子邮箱
			*/
			private String yuangongEmail;

	public JinhuoView() {

	}

	public JinhuoView(JinhuoEntity jinhuoEntity) {
		try {
			BeanUtils.copyProperties(this, jinhuoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



















				//级联表的get和set yaopin

					/**
					* 获取： 药品编号
					*/
					public String getYaopinUuidNumber() {
						return yaopinUuidNumber;
					}
					/**
					* 设置： 药品编号
					*/
					public void setYaopinUuidNumber(String yaopinUuidNumber) {
						this.yaopinUuidNumber = yaopinUuidNumber;
					}
					/**
					* 获取： 药品名称
					*/
					public String getYaopinName() {
						return yaopinName;
					}
					/**
					* 设置： 药品名称
					*/
					public void setYaopinName(String yaopinName) {
						this.yaopinName = yaopinName;
					}
					/**
					* 获取： 药品功效
					*/
					public String getYaopinGongxiao() {
						return yaopinGongxiao;
					}
					/**
					* 设置： 药品功效
					*/
					public void setYaopinGongxiao(String yaopinGongxiao) {
						this.yaopinGongxiao = yaopinGongxiao;
					}
					/**
					* 获取： 所治疾病
					*/
					public String getYaopinJibing() {
						return yaopinJibing;
					}
					/**
					* 设置： 所治疾病
					*/
					public void setYaopinJibing(String yaopinJibing) {
						this.yaopinJibing = yaopinJibing;
					}
					/**
					* 获取： 药品照片
					*/
					public String getYaopinPhoto() {
						return yaopinPhoto;
					}
					/**
					* 设置： 药品照片
					*/
					public void setYaopinPhoto(String yaopinPhoto) {
						this.yaopinPhoto = yaopinPhoto;
					}
					/**
					* 获取： 药品类型
					*/
					public Integer getYaopinTypes() {
						return yaopinTypes;
					}
					/**
					* 设置： 药品类型
					*/
					public void setYaopinTypes(Integer yaopinTypes) {
						this.yaopinTypes = yaopinTypes;
					}


						/**
						* 获取： 药品类型的值
						*/
						public String getYaopinValue() {
							return yaopinValue;
						}
						/**
						* 设置： 药品类型的值
						*/
						public void setYaopinValue(String yaopinValue) {
							this.yaopinValue = yaopinValue;
						}
					/**
					* 获取： 药品库存
					*/
					public Integer getYaopinKucunNumber() {
						return yaopinKucunNumber;
					}
					/**
					* 设置： 药品库存
					*/
					public void setYaopinKucunNumber(Integer yaopinKucunNumber) {
						this.yaopinKucunNumber = yaopinKucunNumber;
					}
					/**
					* 获取： 进价
					*/
					public Double getYaopinJinjiaMoney() {
						return yaopinJinjiaMoney;
					}
					/**
					* 设置： 进价
					*/
					public void setYaopinJinjiaMoney(Double yaopinJinjiaMoney) {
						this.yaopinJinjiaMoney = yaopinJinjiaMoney;
					}
					/**
					* 获取： 售价
					*/
					public Double getYaopinXiaoshouMoney() {
						return yaopinXiaoshouMoney;
					}
					/**
					* 设置： 售价
					*/
					public void setYaopinXiaoshouMoney(Double yaopinXiaoshouMoney) {
						this.yaopinXiaoshouMoney = yaopinXiaoshouMoney;
					}
					/**
					* 获取： 药品介绍
					*/
					public String getYaopinContent() {
						return yaopinContent;
					}
					/**
					* 设置： 药品介绍
					*/
					public void setYaopinContent(String yaopinContent) {
						this.yaopinContent = yaopinContent;
					}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getYaopinDelete() {
						return yaopinDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setYaopinDelete(Integer yaopinDelete) {
						this.yaopinDelete = yaopinDelete;
					}




				//级联表的get和set yuangong
					/**
					* 获取： 员工姓名
					*/
					public String getYuangongName() {
						return yuangongName;
					}
					/**
					* 设置： 员工姓名
					*/
					public void setYuangongName(String yuangongName) {
						this.yuangongName = yuangongName;
					}
					/**
					* 获取： 员工手机号
					*/
					public String getYuangongPhone() {
						return yuangongPhone;
					}
					/**
					* 设置： 员工手机号
					*/
					public void setYuangongPhone(String yuangongPhone) {
						this.yuangongPhone = yuangongPhone;
					}
					/**
					* 获取： 员工身份证号
					*/
					public String getYuangongIdNumber() {
						return yuangongIdNumber;
					}
					/**
					* 设置： 员工身份证号
					*/
					public void setYuangongIdNumber(String yuangongIdNumber) {
						this.yuangongIdNumber = yuangongIdNumber;
					}
					/**
					* 获取： 员工头像
					*/
					public String getYuangongPhoto() {
						return yuangongPhoto;
					}
					/**
					* 设置： 员工头像
					*/
					public void setYuangongPhoto(String yuangongPhoto) {
						this.yuangongPhoto = yuangongPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getYuangongEmail() {
						return yuangongEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYuangongEmail(String yuangongEmail) {
						this.yuangongEmail = yuangongEmail;
					}



}
