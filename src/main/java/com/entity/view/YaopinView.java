package com.entity.view;

import com.entity.YaopinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 药品
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("yaopin")
public class YaopinView extends YaopinEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 药品类型的值
		*/
		private String yaopinValue;



		//级联表 gongyingshang
			/**
			* 供应商名称
			*/
			private String gongyingshangName;
			/**
			* 供应商类型
			*/
			private Integer gongyingshangTypes;
				/**
				* 供应商类型的值
				*/
				private String gongyingshangValue;
			/**
			* 信用等级
			*/
			private Integer gongyingshangXinyongdengjiTypes;
				/**
				* 信用等级的值
				*/
				private String gongyingshangXinyongdengjiValue;
			/**
			* 供应商详情
			*/
			private String gongyingshangContent;

	public YaopinView() {

	}

	public YaopinView(YaopinEntity yaopinEntity) {
		try {
			BeanUtils.copyProperties(this, yaopinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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






				//级联表的get和set gongyingshang
					/**
					* 获取： 供应商名称
					*/
					public String getGongyingshangName() {
						return gongyingshangName;
					}
					/**
					* 设置： 供应商名称
					*/
					public void setGongyingshangName(String gongyingshangName) {
						this.gongyingshangName = gongyingshangName;
					}
					/**
					* 获取： 供应商类型
					*/
					public Integer getGongyingshangTypes() {
						return gongyingshangTypes;
					}
					/**
					* 设置： 供应商类型
					*/
					public void setGongyingshangTypes(Integer gongyingshangTypes) {
						this.gongyingshangTypes = gongyingshangTypes;
					}


						/**
						* 获取： 供应商类型的值
						*/
						public String getGongyingshangValue() {
							return gongyingshangValue;
						}
						/**
						* 设置： 供应商类型的值
						*/
						public void setGongyingshangValue(String gongyingshangValue) {
							this.gongyingshangValue = gongyingshangValue;
						}
					/**
					* 获取： 信用等级
					*/
					public Integer getGongyingshangXinyongdengjiTypes() {
						return gongyingshangXinyongdengjiTypes;
					}
					/**
					* 设置： 信用等级
					*/
					public void setGongyingshangXinyongdengjiTypes(Integer gongyingshangXinyongdengjiTypes) {
						this.gongyingshangXinyongdengjiTypes = gongyingshangXinyongdengjiTypes;
					}


						/**
						* 获取： 信用等级的值
						*/
						public String getGongyingshangXinyongdengjiValue() {
							return gongyingshangXinyongdengjiValue;
						}
						/**
						* 设置： 信用等级的值
						*/
						public void setGongyingshangXinyongdengjiValue(String gongyingshangXinyongdengjiValue) {
							this.gongyingshangXinyongdengjiValue = gongyingshangXinyongdengjiValue;
						}
					/**
					* 获取： 供应商详情
					*/
					public String getGongyingshangContent() {
						return gongyingshangContent;
					}
					/**
					* 设置： 供应商详情
					*/
					public void setGongyingshangContent(String gongyingshangContent) {
						this.gongyingshangContent = gongyingshangContent;
					}










}
