package com.nieyue.controller;

import com.nieyue.bean.Account;
import com.nieyue.exception.*;
import com.nieyue.service.AccountService;
import com.nieyue.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 账户控制类
 * @author yy
 *
 */
@Api(tags={"account"},value="账户",description="账户管理")
@RestController
@RequestMapping("/account")
public class AccountController {
	@Resource
	private AccountService accountService;
	
	/**
	 * 账户分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "账户列表", notes = "账户分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="realname",value="姓名",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="roleId",value="角色id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态，默认0正常，1封禁，2异常",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> list(
			@RequestParam(value="realname",required=false)String realname,
			@RequestParam(value="roleId",required=false)Integer roleId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Account> list = new ArrayList<Account>();
			list= accountService.list(realname,roleId,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 账户修改
	 * @return
	 */
	@ApiOperation(value = "账户修改", notes = "账户修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> update(
			@ModelAttribute Account account,
			HttpSession session)  {
		Account oldAccount = accountService.load(account.getAccountId());
		if(oldAccount==null){
			throw new AccountIsNotExistException();//账户不存在
		}
		//账户已经存在
		if("学生".equals(oldAccount.getRole().getName())){
			if(//accountService.login(account.getPhone(), null,account.getAccountId())!=null||
					accountService.login(account.getSid(), null,account.getAccountId())!=null
			){
				throw new AccountIsExistException();//账户已经存在
			}
		}else{
			if(accountService.login(account.getPhone(), null,account.getAccountId())!=null
			){
				throw new AccountIsExistException();//账户已经存在
			}
		}
		if(!oldAccount.getPassword().equals(account.getPassword())){
			account.setPassword(MyDESutil.getMD5(account.getPassword()));
		}
		boolean um = accountService.update(account);
		if(um){
			List<Account> list = new ArrayList<Account>();
			list.add(account);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 账户修改密码
	 * @param accountId 账户id
	 * @param password  新密码
	 * @param oldPassword 旧密码
	 * @return
	 */
	@ApiOperation(value = "账户修改密码", notes = "账户修改密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query",required=true),
			@ApiImplicitParam(name="password",value="新密码",dataType="string", paramType = "query",required=true),
			@ApiImplicitParam(name="oldPassword",value="旧密码",dataType="string", paramType = "query",required=true)
	})
	@RequestMapping(value = "/updatePassword", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> updateAccountPassword(
			@RequestParam("accountId")Integer accountId,
			@RequestParam("password")String password,
			@RequestParam(value="oldPassword") String oldPassword,
			HttpSession session)  {
		//判断是否存在
		Account ac = accountService.load(accountId);
		if(ac==null || ac.getAccountId()==null){
			throw new AccountIsNotExistException();//账户不存在
		}
		if(oldPassword==null||!MyDESutil.getMD5(oldPassword).equals(ac.getPassword())){
			throw new CommonRollbackException("旧密码错误");//旧密码错误
		}
		if(password==null||password.length()<6||password.length()>18){
			throw new CommonRollbackException("密码长度6-18");//旧密码错误

		}
		ac.setPassword(MyDESutil.getMD5(password));
		boolean a = accountService.update(ac);
		List<Account> list = new ArrayList<Account>();
		list.add(ac);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 账户增加
	 * @return 
	 */
	@ApiOperation(value = "账户增加", notes = "账户增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> add(@ModelAttribute Account account, HttpSession session) {

		String password = account.getPassword();
		if(password==null||password.length()<6||password.length()>18){
			throw new CommonRollbackException("密码长度6-18");//旧密码错误

		}
		//账户已经存在
		/*if(accountService.login(account.getPhone(), null,null)!=null
				||accountService.login(account.getSid(), null,null)!=null
		){
			throw new AccountIsExistException();//账户已经存在
		}*/
		account.setPassword(MyDESutil.getMD5(password));
		account.setStatus(0);
		boolean am = accountService.add(account);
		//
		account.setSid(account.getAccountId().toString());
		accountService.update(account);
		if(am){
			List<Account> list = new ArrayList<Account>();
			list.add(account);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 账户删除
	 * @return
	 */
	@ApiOperation(value = "账户删除", notes = "账户删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> delete(@RequestParam("accountId") Integer accountId,HttpSession session)  {
		Account account = accountService.load(accountId);
		boolean dm = accountService.delete(accountId);
		if(dm){
			List<Account> list = new ArrayList<Account>();
			list.add(account);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 账户批量删除
	 * @return
	 */
	@ApiOperation(value = "账户批量删除", notes = "账户批量删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountIds",value="账户ID集合数组，\"22,33,44,53,3\"",dataType="string", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/deleteBatch", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> deleteBatchAccount(
			@RequestParam("accountIds") String accountIds,
			HttpSession session)  {
		String[] ads = accountIds.replace(" ","").split(",");
		boolean dm=false;
		for (int i = 0; i < ads.length; i++) {
			if(!NumberUtil.isNumeric(ads[i])){
				throw new CommonRollbackException("参数错误");
			}
		}
		for (int i = 0; i < ads.length; i++) {
			dm = accountService.delete(new Integer(ads[i]));
		}
		if(dm){
			List<Account> list = new ArrayList<Account>();
			list.add(new Account());
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 账户浏览数量
	 * @return
	 */
	@ApiOperation(value = "账户数量", notes = "账户数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="realname",value="姓名",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="roleId",value="角色id",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="status",value="状态，默认0正常，1封禁，2异常",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="realname",required=false)String realname,
			@RequestParam(value="roleId",required=false)Integer roleId,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		Integer count = accountService.count(realname,roleId,status);
			List<Integer> list = new ArrayList<>();
			list.add(count);
			return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 账户单个加载
	 * @return
	 */
	@ApiOperation(value = "账户单个加载", notes = "账户单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Account>> load(@RequestParam("accountId") Integer accountId,HttpSession session)  {
		List<Account> list = new ArrayList<Account>();
		Account account = accountService.load(accountId);
			if(account!=null &&!account.equals("")){
				list.add(account);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("账户");//不存在
			}
	}
	
	/**
	 * 登录
	 * @return
	 */
	@ApiOperation(value = "登录", notes = "登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name="adminName",value="账户",dataType="string", paramType = "query",required=true),
			@ApiImplicitParam(name="password",value="密码",dataType="string", paramType = "query",required=true),
			/*@ApiImplicitParam(name="verificationCode",value="图片验证码",dataType="string", paramType = "query",required=true)*/
	})
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Account>> login(
			@RequestParam(value="adminName") String adminName,
			@RequestParam(value="password") String password,
			/*@RequestParam(value="verificationCode") String verificationCode,*/
			HttpSession session)  {
		//1代验证码
		/*String ran= (String) session.getAttribute("verificationCode");
		if(ran==null||!ran.equals(verificationCode)){
			throw new VerifyCodeErrorException();//验证码
		}*/
		List<Account> list = new ArrayList<Account>();
		Account account = accountService.login(adminName, MyDESutil.getMD5(password),null);
		if (ObjectUtils.isEmpty(account)) {
			throw new AccountLoginException();//账户或密码错误
		}
		if(account.getStatus().equals(1)){
			throw new AccountLockException();//账户锁定
		}
			if(account!=null &&!account.equals("")){
				list.add(account);
				session.setAttribute("account",account);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new AccountLoginException();//登录异常
			}
	}
	/**
	 * 是否登录
	 * @return
	 */
	@ApiOperation(value = "是否登录", notes = "是否登录")
	@RequestMapping(value = "/islogin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> isLogin(
			HttpSession session)  {
		Account account = (Account) session.getAttribute("account");
		List<Account> list = new ArrayList<Account>();
		if(account!=null && !account.equals("")){
			account=accountService.load(account.getAccountId());
			session.setAttribute("account",account);
			list.add(account);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		throw new AccountIsNotLoginException();//没有登录
	}
	/**
	 * 登出
	 * @return
	 */
	@ApiOperation(value = "登出", notes = "登出")
	@ApiImplicitParams({
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query",required=true)
	})
	@RequestMapping(value = "/loginout", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> loginout(
			@RequestParam("accountId") Integer accountId,
			HttpSession session)  {
		session.invalidate();
		return ResultUtil.getSlefSRSuccessList(null);
	}

	/**
	 * 导入Excel
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "/importExcel", method = {RequestMethod.GET,RequestMethod.POST})
	public StateResultList<List<List<List<Object>>>> importExcel(
			@RequestParam("excel") MultipartFile multipartFile,
			HttpSession	 session
	) throws IllegalStateException, IOException {
		String name="";
		name="D:/nieyue"+"/uploaderPath/img/"+ SnowflakeIdWorker.getId().toString()+multipartFile.getOriginalFilename();
		File file = new File(name);
		multipartFile.transferTo(file);
		List<List<List<Object>>> lll = MyExcel.importData(file);
		for (int i = 0; i < lll.size(); i++) {
			List<List<Object>> ll = lll.get(i);
			//数据从1行开始，0是列名
			for (int j = 1; j < lll.size(); j++) {
				List<Object> l = ll.get(j);
				Account account = new Account();
				for (int z = 0; z < l.size(); z++) {
						//account.set
				}

			}
		}
		return ResultUtil.getSlefSRSuccessList(lll);
	}
}
