package com.nieyue.controller;

import com.nieyue.bean.Datum;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.DatumService;
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
 * 资料控制类
 * @author yy
 *
 */
@Api(tags={"datum"},value="资料",description="资料管理")
@RestController
@RequestMapping("/datum")
public class DatumController {
	@Resource
	private DatumService datumService;
	
	/**
	 * 资料分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "资料列表", notes = "资料分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Datum>> list(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Datum> list = new ArrayList<Datum>();
			list= datumService.list(accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 资料修改
	 * @return
	 */
	@ApiOperation(value = "资料修改", notes = "资料修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Datum>> update(@ModelAttribute Datum datum,HttpSession session)  {
		boolean um = datumService.update(datum);
		if(um){
			List<Datum> list = new ArrayList<>();
			list.add(datum);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 资料增加
	 * @return 
	 */
	@ApiOperation(value = "资料增加", notes = "资料增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Datum>> add(@ModelAttribute Datum datum, HttpSession session) {
		boolean am = datumService.add(datum);
		if(am){
			List<Datum> list = new ArrayList<>();
			list.add(datum);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 资料删除
	 * @return
	 */
	@ApiOperation(value = "资料删除", notes = "资料删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="datumId",value="资料ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Datum>> delete(@RequestParam("datumId") Integer datumId,HttpSession session)  {
		Datum datum = datumService.load(datumId);
		boolean dm = datumService.delete(datumId);
		if(dm){
			List<Datum> list = new ArrayList<>();
			list.add(datum);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 资料浏览数量
	 * @return
	 */
	@ApiOperation(value = "资料数量", notes = "资料数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="accountId",value="账户id外键",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		Integer count = datumService.count(accountId);
			List<Integer> list = new ArrayList<Integer>();
			list.add(count);
			return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 资料单个加载
	 * @return
	 */
	@ApiOperation(value = "资料单个加载", notes = "资料单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="datumId",value="资料ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Datum>> load(@RequestParam("datumId") Integer datumId,HttpSession session)  {
		List<Datum> list = new ArrayList<Datum>();
		Datum datum = datumService.load(datumId);
			if(datum!=null &&!datum.equals("")){
				list.add(datum);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("资料");//不存在
			}
	}
	
}
