package com.nieyue.controller;

import com.nieyue.bean.Academy;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.AcademyService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResultList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * 学院控制类
 * @author yy
 *
 */
@Api(tags={"academy"},value="学院",description="学院管理")
@RestController
@RequestMapping("/academy")
public class AcademyController {
	@Resource
	private AcademyService academyService;
	
	/**
	 * 学院分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "学院列表", notes = "学院分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Academy>> list(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Academy> list = new ArrayList<Academy>();
			list= academyService.list(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 学院修改
	 * @return
	 */
	@ApiOperation(value = "学院修改", notes = "学院修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Academy>> update(@ModelAttribute Academy academy,HttpSession session)  {
		boolean um = academyService.update(academy);
		if(um){
			List<Academy> list = new ArrayList<>();
			list.add(academy);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 学院增加
	 * @return 
	 */
	@ApiOperation(value = "学院增加", notes = "学院增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Academy>> add(@ModelAttribute Academy academy, HttpSession session) {
		boolean am = academyService.add(academy);
		if(am){
			List<Academy> list = new ArrayList<>();
			list.add(academy);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 学院删除
	 * @return
	 */
	@ApiOperation(value = "学院删除", notes = "学院删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="academyId",value="学院ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Academy>> delete(@RequestParam("academyId") Integer academyId,HttpSession session)  {
		Academy academy = academyService.load(academyId);
		boolean dm = academyService.delete(academyId);
		if(dm){
			List<Academy> list = new ArrayList<>();
			list.add(academy);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 学院浏览数量
	 * @return
	 */
	@ApiOperation(value = "学院数量", notes = "学院数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(HttpSession session)  {
		Integer count = academyService.count();
			List<Integer> list = new ArrayList<Integer>();
			list.add(count);
			return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 学院单个加载
	 * @return
	 */
	@ApiOperation(value = "学院单个加载", notes = "学院单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="academyId",value="学院ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Academy>> load(@RequestParam("academyId") Integer academyId,HttpSession session)  {
		List<Academy> list = new ArrayList<Academy>();
		Academy academy = academyService.load(academyId);
			if(academy!=null &&!academy.equals("")){
				list.add(academy);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("学院");//不存在
			}
	}
	
}
