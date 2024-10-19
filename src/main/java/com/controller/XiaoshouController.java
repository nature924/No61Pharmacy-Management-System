
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
 * 销售
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xiaoshou")
public class XiaoshouController {
    private static final Logger logger = LoggerFactory.getLogger(XiaoshouController.class);

    @Autowired
    private XiaoshouService xiaoshouService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YaopinService yaopinService;
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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = xiaoshouService.queryPage(params);

        //字典表数据转换
        List<XiaoshouView> list =(List<XiaoshouView>)page.getList();
        for(XiaoshouView c:list){
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
        XiaoshouEntity xiaoshou = xiaoshouService.selectById(id);
        if(xiaoshou !=null){
            //entity转view
            XiaoshouView view = new XiaoshouView();
            BeanUtils.copyProperties( xiaoshou , view );//把实体数据重构到view中

                //级联表
                YaopinEntity yaopin = yaopinService.selectById(xiaoshou.getYaopinId());
                if(yaopin != null){
                    BeanUtils.copyProperties( yaopin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYaopinId(yaopin.getId());
                }
                //级联表
                YuangongEntity yuangong = yuangongService.selectById(xiaoshou.getYuangongId());
                if(yuangong != null){
                    BeanUtils.copyProperties( yuangong , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYuangongId(yuangong.getId());
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
    public R save(@RequestBody XiaoshouEntity xiaoshou, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xiaoshou:{}",this.getClass().getName(),xiaoshou.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("员工".equals(role))
            xiaoshou.setYuangongId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));



        YaopinEntity yaopinEntity = yaopinService.selectById(xiaoshou.getYaopinId());
        if(yaopinEntity == null)
            return R.error("查不到药品");
        else if(yaopinEntity.getYaopinKucunNumber() == null)
            return R.error("查不到该药品的库存");
        else if((yaopinEntity.getYaopinKucunNumber()-xiaoshou.getXiaoshouShuliangNumber()) <0)
            return R.error("销售数量不能大于库存数量");
        yaopinEntity.setYaopinKucunNumber(yaopinEntity.getYaopinKucunNumber()-xiaoshou.getXiaoshouShuliangNumber());
        yaopinService.updateById(yaopinEntity);

            xiaoshou.setInsertTime(new Date());
            xiaoshou.setCreateTime(new Date());
            xiaoshouService.insert(xiaoshou);
            return R.ok();

    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XiaoshouEntity xiaoshou, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xiaoshou:{}",this.getClass().getName(),xiaoshou.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("员工".equals(role))
//            xiaoshou.setYuangongId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<XiaoshouEntity> queryWrapper = new EntityWrapper<XiaoshouEntity>()
            .notIn("id",xiaoshou.getId())
            .andNew()
            .eq("xiaoshou_uuid_number", xiaoshou.getXiaoshouUuidNumber())
            .eq("yaopin_id", xiaoshou.getYaopinId())
            .eq("yuangong_id", xiaoshou.getYuangongId())
            .eq("xiaoshou_shuliang_number", xiaoshou.getXiaoshouShuliangNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiaoshouEntity xiaoshouEntity = xiaoshouService.selectOne(queryWrapper);
        if(xiaoshouEntity==null){
            xiaoshouService.updateById(xiaoshou);//根据id更新
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
        xiaoshouService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<XiaoshouEntity> xiaoshouList = new ArrayList<>();//上传的东西
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
                            XiaoshouEntity xiaoshouEntity = new XiaoshouEntity();
//                            xiaoshouEntity.setXiaoshouUuidNumber(data.get(0));                    //销售编号 要改的
//                            xiaoshouEntity.setYaopinId(Integer.valueOf(data.get(0)));   //药品 要改的
//                            xiaoshouEntity.setYuangongId(Integer.valueOf(data.get(0)));   //员工 要改的
//                            xiaoshouEntity.setXiaoshouShuliangNumber(Integer.valueOf(data.get(0)));   //销售数量 要改的
//                            xiaoshouEntity.setXiaoshouContent("");//照片
//                            xiaoshouEntity.setXiaoshouTime(new Date(data.get(0)));          //销售时间 要改的
//                            xiaoshouEntity.setInsertTime(date);//时间
//                            xiaoshouEntity.setCreateTime(date);//时间
                            xiaoshouList.add(xiaoshouEntity);


                            //把要查询是否重复的字段放入map中
                                //销售编号
                                if(seachFields.containsKey("xiaoshouUuidNumber")){
                                    List<String> xiaoshouUuidNumber = seachFields.get("xiaoshouUuidNumber");
                                    xiaoshouUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xiaoshouUuidNumber = new ArrayList<>();
                                    xiaoshouUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("xiaoshouUuidNumber",xiaoshouUuidNumber);
                                }
                        }

                        //查询是否重复
                         //销售编号
                        List<XiaoshouEntity> xiaoshouEntities_xiaoshouUuidNumber = xiaoshouService.selectList(new EntityWrapper<XiaoshouEntity>().in("xiaoshou_uuid_number", seachFields.get("xiaoshouUuidNumber")));
                        if(xiaoshouEntities_xiaoshouUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XiaoshouEntity s:xiaoshouEntities_xiaoshouUuidNumber){
                                repeatFields.add(s.getXiaoshouUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [销售编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xiaoshouService.insertBatch(xiaoshouList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
