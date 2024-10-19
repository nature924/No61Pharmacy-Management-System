
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
 * 进货
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jinhuo")
public class JinhuoController {
    private static final Logger logger = LoggerFactory.getLogger(JinhuoController.class);

    @Autowired
    private JinhuoService jinhuoService;


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
        PageUtils page = jinhuoService.queryPage(params);

        //字典表数据转换
        List<JinhuoView> list =(List<JinhuoView>)page.getList();
        for(JinhuoView c:list){
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
        JinhuoEntity jinhuo = jinhuoService.selectById(id);
        if(jinhuo !=null){
            //entity转view
            JinhuoView view = new JinhuoView();
            BeanUtils.copyProperties( jinhuo , view );//把实体数据重构到view中

                //级联表
                YaopinEntity yaopin = yaopinService.selectById(jinhuo.getYaopinId());
                if(yaopin != null){
                    BeanUtils.copyProperties( yaopin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYaopinId(yaopin.getId());
                }
                //级联表
                YuangongEntity yuangong = yuangongService.selectById(jinhuo.getYuangongId());
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
    public R save(@RequestBody JinhuoEntity jinhuo, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jinhuo:{}",this.getClass().getName(),jinhuo.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("员工".equals(role))
            jinhuo.setYuangongId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        YaopinEntity yaopinEntity = yaopinService.selectById(jinhuo.getYaopinId());
        if(yaopinEntity == null)
            return R.error("查不到药品");
        else if(yaopinEntity.getYaopinKucunNumber() == null)
            return R.error("查不到该药品的库存");
        yaopinEntity.setYaopinKucunNumber(yaopinEntity.getYaopinKucunNumber()+jinhuo.getJinhuoShuliangNumber());
        yaopinService.updateById(yaopinEntity);


        jinhuo.setInsertTime(new Date());
            jinhuo.setCreateTime(new Date());
            jinhuoService.insert(jinhuo);
            return R.ok();

    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JinhuoEntity jinhuo, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jinhuo:{}",this.getClass().getName(),jinhuo.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("员工".equals(role))
//            jinhuo.setYuangongId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JinhuoEntity> queryWrapper = new EntityWrapper<JinhuoEntity>()
            .notIn("id",jinhuo.getId())
            .andNew()
            .eq("jinhuo_uuid_number", jinhuo.getJinhuoUuidNumber())
            .eq("yaopin_id", jinhuo.getYaopinId())
            .eq("yuangong_id", jinhuo.getYuangongId())
            .eq("jinhuo_shuliang_number", jinhuo.getJinhuoShuliangNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JinhuoEntity jinhuoEntity = jinhuoService.selectOne(queryWrapper);
        if(jinhuoEntity==null){
            jinhuoService.updateById(jinhuo);//根据id更新
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
        jinhuoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JinhuoEntity> jinhuoList = new ArrayList<>();//上传的东西
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
                            JinhuoEntity jinhuoEntity = new JinhuoEntity();
//                            jinhuoEntity.setJinhuoUuidNumber(data.get(0));                    //进货编号 要改的
//                            jinhuoEntity.setYaopinId(Integer.valueOf(data.get(0)));   //药品 要改的
//                            jinhuoEntity.setYuangongId(Integer.valueOf(data.get(0)));   //员工 要改的
//                            jinhuoEntity.setJinhuoShuliangNumber(Integer.valueOf(data.get(0)));   //进货数量 要改的
//                            jinhuoEntity.setJinhuoContent("");//照片
//                            jinhuoEntity.setJinhuoTime(new Date(data.get(0)));          //进货时间 要改的
//                            jinhuoEntity.setInsertTime(date);//时间
//                            jinhuoEntity.setCreateTime(date);//时间
                            jinhuoList.add(jinhuoEntity);


                            //把要查询是否重复的字段放入map中
                                //进货编号
                                if(seachFields.containsKey("jinhuoUuidNumber")){
                                    List<String> jinhuoUuidNumber = seachFields.get("jinhuoUuidNumber");
                                    jinhuoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jinhuoUuidNumber = new ArrayList<>();
                                    jinhuoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jinhuoUuidNumber",jinhuoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //进货编号
                        List<JinhuoEntity> jinhuoEntities_jinhuoUuidNumber = jinhuoService.selectList(new EntityWrapper<JinhuoEntity>().in("jinhuo_uuid_number", seachFields.get("jinhuoUuidNumber")));
                        if(jinhuoEntities_jinhuoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JinhuoEntity s:jinhuoEntities_jinhuoUuidNumber){
                                repeatFields.add(s.getJinhuoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [进货编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jinhuoService.insertBatch(jinhuoList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
