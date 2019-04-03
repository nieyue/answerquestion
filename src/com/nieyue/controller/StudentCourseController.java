package com.nieyue.controller;

import com.nieyue.bean.StudentCourse;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.StudentCourseService;
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
 * 学生课程控制类
 * @author yy
 *
 */
@Api(tags={"studentCourse"},value="学生课程",description="学生课程管理")
@RestController
@RequestMapping("/studentCourse")
public class StudentCourseController {
	@Resource
	private StudentCourseService studentCourseService;
	
	/**
	 * 学生课程分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "学生课程列表", notes = "学生课程分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="courseId",value="课程id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<StudentCourse>> list(
			@RequestParam(value="courseId",required=false)Integer courseId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<StudentCourse> list = new ArrayList<StudentCourse>();
			list= studentCourseService.list(courseId,accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 学生课程修改
	 * @return
	 */
	@ApiOperation(value = "学生课程修改", notes = "学生课程修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<StudentCourse>> update(@ModelAttribute StudentCourse studentCourse,HttpSession session)  {
		boolean um = studentCourseService.update(studentCourse);
		if(um){
			List<StudentCourse> list = new ArrayList<>();
			list.add(studentCourse);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 学生课程增加
	 * @return 
	 */
	@ApiOperation(value = "学生课程增加", notes = "学生课程增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<StudentCourse>> add(@ModelAttribute StudentCourse studentCourse, HttpSession session) {
		boolean am = studentCourseService.add(studentCourse);
		if(am){
			List<StudentCourse> list = new ArrayList<>();
			list.add(studentCourse);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 学生课程删除
	 * @return
	 */
	@ApiOperation(value = "学生课程删除", notes = "学生课程删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="studentCourseId",value="学生课程ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<StudentCourse>> delete(@RequestParam("studentCourseId") Integer studentCourseId,HttpSession session)  {
		StudentCourse studentCourse = studentCourseService.load(studentCourseId);
		boolean dm = studentCourseService.delete(studentCourseId);
		if(dm){
			List<StudentCourse> list = new ArrayList<>();
			list.add(studentCourse);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 学生课程浏览数量
	 * @return
	 */
	@ApiOperation(value = "学生课程数量", notes = "学生课程数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="courseId",value="课程id外键",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="accountId",value="账户id外键",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="courseId",required=false)Integer courseId,
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		Integer count = studentCourseService.count(courseId,accountId);
			List<Integer> list = new ArrayList<Integer>();
			list.add(count);
			return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 学生课程单个加载
	 * @return
	 */
	@ApiOperation(value = "学生课程单个加载", notes = "学生课程单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="StudentCourseId",value="学生课程ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<StudentCourse>> load(@RequestParam("studentCourseId") Integer studentCourseId,HttpSession session)  {
		List<StudentCourse> list = new ArrayList<StudentCourse>();
		StudentCourse studentCourse = studentCourseService.load(studentCourseId);
			if(studentCourse!=null &&!studentCourse.equals("")){
				list.add(studentCourse);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("学生课程");//不存在
			}
	}
	
}
