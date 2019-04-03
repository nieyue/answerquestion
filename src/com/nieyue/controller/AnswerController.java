package com.nieyue.controller;

import com.nieyue.bean.Answer;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.AnswerService;
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
 * 回答控制类
 * @author yy
 *
 */
@Api(tags={"answer"},value="回答",description="回答管理")
@RestController
@RequestMapping("/answer")
public class AnswerController {
	@Resource
	private AnswerService answerService;
	
	/**
	 * 回答分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "回答列表", notes = "回答分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="problemId",value="问题id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Answer>> list(
			@RequestParam(value="problemId",required=false)Integer problemId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Answer> list = new ArrayList<Answer>();
			list= answerService.list(problemId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 回答修改
	 * @return
	 */
	@ApiOperation(value = "回答修改", notes = "回答修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Answer>> update(@ModelAttribute Answer answer,HttpSession session)  {
		boolean um = answerService.update(answer);
		if(um){
			List<Answer> list = new ArrayList<>();
			list.add(answer);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 回答增加
	 * @return 
	 */
	@ApiOperation(value = "回答增加", notes = "回答增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Answer>> add(@ModelAttribute Answer answer, HttpSession session) {
		boolean am = answerService.add(answer);
		if(am){
			List<Answer> list = new ArrayList<>();
			list.add(answer);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 回答删除
	 * @return
	 */
	@ApiOperation(value = "回答删除", notes = "回答删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="answerId",value="回答ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Answer>> delete(@RequestParam("answerId") Integer answerId,HttpSession session)  {
		Answer answer = answerService.load(answerId);
		boolean dm = answerService.delete(answerId);
		if(dm){
			List<Answer> list = new ArrayList<>();
			list.add(answer);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 回答浏览数量
	 * @return
	 */
	@ApiOperation(value = "回答数量", notes = "回答数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="problemId",value="问题id外键",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="problemId",required=false)Integer problemId,
			HttpSession session)  {
		Integer count = answerService.count(problemId);
			List<Integer> list = new ArrayList<Integer>();
			list.add(count);
			return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 回答单个加载
	 * @return
	 */
	@ApiOperation(value = "回答单个加载", notes = "回答单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="answerId",value="回答ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Answer>> load(@RequestParam("answerId") Integer answerId,HttpSession session)  {
		List<Answer> list = new ArrayList<Answer>();
		Answer answer = answerService.load(answerId);
			if(answer!=null &&!answer.equals("")){
				list.add(answer);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("回答");//不存在
			}
	}
	
}
