package com.nieyue.controller;

import com.nieyue.bean.Major;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.MajorService;
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
 * 专业控制类
 * @author yy
 *
 */
@Api(tags={"major"},value="专业",description="专业管理")
@RestController
@RequestMapping("/major")
public class MajorController {
	@Resource
	private MajorService majorService;
	
	/**
	 * 专业分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "专业列表", notes = "专业分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="academyId",value="学院id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Major>> list(
			@RequestParam(value="academyId",required=false)Integer academyId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Major> list = new ArrayList<Major>();
			list= majorService.list(academyId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 专业修改
	 * @return
	 */
	@ApiOperation(value = "专业修改", notes = "专业修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Major>> update(@ModelAttribute Major major,HttpSession session)  {
		boolean um = majorService.update(major);
		if(um){
			List<Major> list = new ArrayList<>();
			list.add(major);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 专业增加
	 * @return 
	 */
	@ApiOperation(value = "专业增加", notes = "专业增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Major>> add(@ModelAttribute Major major, HttpSession session) {
		boolean am = majorService.add(major);
		if(am){
			List<Major> list = new ArrayList<>();
			list.add(major);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 专业删除
	 * @return
	 */
	@ApiOperation(value = "专业删除", notes = "专业删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="majorId",value="专业ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Major>> delete(@RequestParam("majorId") Integer majorId,HttpSession session)  {
		Major Major = majorService.load(majorId);
		boolean dm = majorService.delete(majorId);
		if(dm){
			List<Major> list = new ArrayList<>();
			list.add(Major);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 专业浏览数量
	 * @return
	 */
	@ApiOperation(value = "专业数量", notes = "专业数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="academyId",value="学院id外键",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="academyId",required=false)Integer academyId,
			HttpSession session)  {
		Integer count = majorService.count(academyId);
			List<Integer> list = new ArrayList<Integer>();
			list.add(count);
			return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 专业单个加载
	 * @return
	 */
	@ApiOperation(value = "专业单个加载", notes = "专业单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="majorId",value="专业ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Major>> load(@RequestParam("majorId") Integer majorId,HttpSession session)  {
		List<Major> list = new ArrayList<Major>();
		Major Major = majorService.load(majorId);
			if(Major!=null &&!Major.equals("")){
				list.add(Major);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("专业");//不存在
			}
	}
	
}
