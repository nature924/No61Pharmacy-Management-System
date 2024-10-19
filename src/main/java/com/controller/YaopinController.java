
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 药品
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yaopin")
public class YaopinController {
    private static final Logger logger = LoggerFactory.getLogger(YaopinController.class);

    @Autowired
    private YaopinService yaopinService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private GongyingshangService gongyingshangService;

    @Autowired
    private YuangongService yuangongService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("员工".equals(role))
            params.put("yuangongId",request.getSession().getAttribute("userId"));
        params.put("yaopinDeleteStart",1);params.put("yaopinDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = yaopinService.queryPage(params);

        //字典表数据转换
        List<YaopinView> list =(List<YaopinView>)page.getList();
        for(YaopinView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YaopinEntity yaopin = yaopinService.selectById(id);
        if(yaopin !=null){
            //entity转view
            YaopinView view = new YaopinView();
            BeanUtils.copyProperties( yaopin , view );//把实体数据重构到view中

                //级联表
                GongyingshangEntity gongyingshang = gongyingshangService.selectById(yaopin.getGongyingshangId());
                if(gongyingshang != null){
                    BeanUtils.copyProperties( gongyingshang , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setGongyingshangId(gongyingshang.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YaopinEntity yaopin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yaopin:{}",this.getClass().getName(),yaopin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<YaopinEntity> queryWrapper = new EntityWrapper<YaopinEntity>()
            .eq("gongyingshang_id", yaopin.getGongyingshangId())
            .eq("yaopin_uuid_number", yaopin.getYaopinUuidNumber())
            .eq("yaopin_name", yaopin.getYaopinName())
            .eq("yaopin_gongxiao", yaopin.getYaopinGongxiao())
            .eq("yaopin_jibing", yaopin.getYaopinJibing())
            .eq("yaopin_types", yaopin.getYaopinTypes())
            .eq("yaopin_kucun_number", yaopin.getYaopinKucunNumber())
            .eq("yaopin_delete", yaopin.getYaopinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YaopinEntity yaopinEntity = yaopinService.selectOne(queryWrapper);
        if(yaopinEntity==null){
            yaopin.setYaopinDelete(1);
            yaopin.setCreateTime(new Date());
            yaopinService.insert(yaopin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YaopinEntity yaopin, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yaopin:{}",this.getClass().getName(),yaopin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<YaopinEntity> queryWrapper = new EntityWrapper<YaopinEntity>()
            .notIn("id",yaopin.getId())
            .andNew()
            .eq("gongyingshang_id", yaopin.getGongyingshangId())
            .eq("yaopin_uuid_number", yaopin.getYaopinUuidNumber())
            .eq("yaopin_name", yaopin.getYaopinName())
            .eq("yaopin_gongxiao", yaopin.getYaopinGongxiao())
            .eq("yaopin_jibing", yaopin.getYaopinJibing())
            .eq("yaopin_types", yaopin.getYaopinTypes())
            .eq("yaopin_kucun_number", yaopin.getYaopinKucunNumber())
            .eq("yaopin_delete", yaopin.getYaopinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YaopinEntity yaopinEntity = yaopinService.selectOne(queryWrapper);
        if("".equals(yaopin.getYaopinPhoto()) || "null".equals(yaopin.getYaopinPhoto())){
                yaopin.setYaopinPhoto(null);
        }
        if(yaopinEntity==null){
            yaopinService.updateById(yaopin);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<YaopinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            YaopinEntity yaopinEntity = new YaopinEntity();
            yaopinEntity.setId(id);
            yaopinEntity.setYaopinDelete(2);
            list.add(yaopinEntity);
        }
        if(list != null && list.size() >0){
            yaopinService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<YaopinEntity> yaopinList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YaopinEntity yaopinEntity = new YaopinEntity();
//                            yaopinEntity.setGongyingshangId(Integer.valueOf(data.get(0)));   //供应商 要改的
//                            yaopinEntity.setYaopinUuidNumber(data.get(0));                    //药品编号 要改的
//                            yaopinEntity.setYaopinName(data.get(0));                    //药品名称 要改的
//                            yaopinEntity.setYaopinGongxiao(data.get(0));                    //药品功效 要改的
//                            yaopinEntity.setYaopinJibing(data.get(0));                    //所治疾病 要改的
//                            yaopinEntity.setYaopinPhoto("");//照片
//                            yaopinEntity.setYaopinTypes(Integer.valueOf(data.get(0)));   //药品类型 要改的
//                            yaopinEntity.setYaopinKucunNumber(Integer.valueOf(data.get(0)));   //药品库存 要改的
//                            yaopinEntity.setYaopinJinjiaMoney(data.get(0));                    //进价 要改的
//                            yaopinEntity.setYaopinXiaoshouMoney(data.get(0));                    //售价 要改的
//                            yaopinEntity.setYaopinContent("");//照片
//                            yaopinEntity.setYaopinDelete(1);//逻辑删除字段
//                            yaopinEntity.setCreateTime(date);//时间
                            yaopinList.add(yaopinEntity);


                            //把要查询是否重复的字段放入map中
                                //药品编号
                                if(seachFields.containsKey("yaopinUuidNumber")){
                                    List<String> yaopinUuidNumber = seachFields.get("yaopinUuidNumber");
                                    yaopinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yaopinUuidNumber = new ArrayList<>();
                                    yaopinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yaopinUuidNumber",yaopinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //药品编号
                        List<YaopinEntity> yaopinEntities_yaopinUuidNumber = yaopinService.selectList(new EntityWrapper<YaopinEntity>().in("yaopin_uuid_number", seachFields.get("yaopinUuidNumber")).eq("yaopin_delete", 1));
                        if(yaopinEntities_yaopinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YaopinEntity s:yaopinEntities_yaopinUuidNumber){
                                repeatFields.add(s.getYaopinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [药品编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yaopinService.insertBatch(yaopinList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
