package com.nieyue.controller;

import com.nieyue.bean.Course;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.CourseService;
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
 * 课程控制类
 * @author yy
 *
 */
@Api(tags={"course"},value="课程",description="课程管理")
@RestController
@RequestMapping("/course")
public class CourseController {
	@Resource
	private CourseService courseService;
	
	/**
	 * 课程分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "课程列表", notes = "课程分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="majorId",value="学院id外键",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="update_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Course>> list(
			@RequestParam(value="majorId",required=false)Integer majorId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Course> list = new ArrayList<Course>();
			list= courseService.list(majorId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 课程修改
	 * @return
	 */
	@ApiOperation(value = "课程修改", notes = "课程修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Course>> update(@ModelAttribute Course course,HttpSession session)  {
		boolean um = courseService.update(course);
		if(um){
			List<Course> list = new ArrayList<>();
			list.add(course);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 课程增加
	 * @return 
	 */
	@ApiOperation(value = "课程增加", notes = "课程增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Course>> add(@ModelAttribute Course course, HttpSession session) {
		boolean am = courseService.add(course);
		if(am){
			List<Course> list = new ArrayList<>();
			list.add(course);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 课程删除
	 * @return
	 */
	@ApiOperation(value = "课程删除", notes = "课程删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="courseId",value="课程ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Course>> delete(@RequestParam("courseId") Integer courseId,HttpSession session)  {
		Course Course = courseService.load(courseId);
		boolean dm = courseService.delete(courseId);
		if(dm){
			List<Course> list = new ArrayList<>();
			list.add(Course);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 课程浏览数量
	 * @return
	 */
	@ApiOperation(value = "课程数量", notes = "课程数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="majorId",value="学院id外键",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="majorId",required=false)Integer majorId,
			HttpSession session)  {
		Integer count = courseService.count(majorId);
			List<Integer> list = new ArrayList<Integer>();
			list.add(count);
			return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 课程单个加载
	 * @return
	 */
	@ApiOperation(value = "课程单个加载", notes = "课程单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="courseId",value="课程ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Course>> load(@RequestParam("courseId") Integer courseId,HttpSession session)  {
		List<Course> list = new ArrayList<Course>();
		Course course = courseService.load(courseId);
			if(course!=null &&!course.equals("")){
				list.add(course);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("课程");//不存在
			}
	}
	
}
