package com.nieyue.controller;

import com.nieyue.bean.Problem;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.ProblemService;
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
 * 问题控制类
 * @author yy
 *
 */
@Api(tags={"problem"},value="问题",description="问题管理")
@RestController
@RequestMapping("/problem")
public class ProblemController {
	@Resource
	private ProblemService problemService;
	
	/**
	 * 问题分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "问题列表", notes = "问题分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="studentCourseId",value="学生课程id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="answerId",value="采纳回答id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Problem>> list(
			@RequestParam(value="studentCourseId",required=false)Integer studentCourseId,
			@RequestParam(value="answerId",required=false)Integer answerId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Problem> list = new ArrayList<Problem>();
			list= problemService.list(studentCourseId,answerId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 问题修改
	 * @return
	 */
	@ApiOperation(value = "问题修改", notes = "问题修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Problem>> update(@ModelAttribute Problem problem,HttpSession session)  {
		boolean um = problemService.update(problem);
		if(um){
			List<Problem> list = new ArrayList<>();
			list.add(problem);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 问题增加
	 * @return 
	 */
	@ApiOperation(value = "问题增加", notes = "问题增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Problem>> add(@ModelAttribute Problem problem, HttpSession session) {
		boolean am = problemService.add(problem);
		if(am){
			List<Problem> list = new ArrayList<>();
			list.add(problem);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 问题删除
	 * @return
	 */
	@ApiOperation(value = "问题删除", notes = "问题删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="problemId",value="问题ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Problem>> delete(@RequestParam("problemId") Integer problemId,HttpSession session)  {
		Problem problem = problemService.load(problemId);
		boolean dm = problemService.delete(problemId);
		if(dm){
			List<Problem> list = new ArrayList<>();
			list.add(problem);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 问题浏览数量
	 * @return
	 */
	@ApiOperation(value = "问题数量", notes = "问题数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="studentCourseId",value="学生课程id外键",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="answerId",value="采纳回答id外键",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="studentCourseId",required=false)Integer studentCourseId,
			@RequestParam(value="answerId",required=false)Integer answerId,
			HttpSession session)  {
		Integer count = problemService.count(studentCourseId,answerId);
			List<Integer> list = new ArrayList<Integer>();
			list.add(count);
			return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 问题单个加载
	 * @return
	 */
	@ApiOperation(value = "问题单个加载", notes = "问题单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="problemId",value="问题ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Problem>> load(@RequestParam("problemId") Integer problemId,HttpSession session)  {
		List<Problem> list = new ArrayList<Problem>();
		Problem problem = problemService.load(problemId);
			if(problem!=null &&!problem.equals("")){
				list.add(problem);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("问题");//不存在
			}
	}
	
}
