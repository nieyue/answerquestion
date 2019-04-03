package com.nieyue.controller;

import com.nieyue.util.MyQRcode;
import com.nieyue.verification.VerificationCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.util.Date;


/**
 * 工具控制类
 * @author yy
 *
 */
@Api(tags={"tool"},value="工具",description="工具接口管理")
@RestController
@RequestMapping("/tool")
public class ToolController {
	@Resource
	VerificationCode verificationCode;

	
	/**
	 * 验证码
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "验证码", notes = "验证码")
	@RequestMapping(value = "/getVerificationCode", method = {RequestMethod.GET, RequestMethod.POST})
	public void getVerificationCode(
            HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
			ByteArrayOutputStream vc = verificationCode.execute(session);
			response.getOutputStream().write(vc.toByteArray());
		return ;
	}
	/**
	 * 生成二维码
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "生成二维码", notes = "生成二维码")
	@RequestMapping(value = "/getQrCode", method = {RequestMethod.GET, RequestMethod.POST})
	@ApiImplicitParams({
			@ApiImplicitParam(name="url",value="url链接",dataType="string", paramType = "query"),
	})
	public void getQrCode(
			@RequestParam("url") String url,
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		MyQRcode.createQrcode(url, response.getOutputStream());
		return ;
	}
	@RequestMapping(value = "/getSession", method = {RequestMethod.GET,RequestMethod.POST})
	public String getSession(
			@RequestParam("createDate") Date createDate,
			HttpSession	 session
	){
		System.err.println(session.getAttribute("acount"));
		System.err.println(session.getAttribute("role"));
		System.err.println(session.getAttribute("finance"));
		//this.logger.error("\ngetId：\n{} ",session.getId());
		System.err.println(createDate);
		return session.getId();

	}
}
