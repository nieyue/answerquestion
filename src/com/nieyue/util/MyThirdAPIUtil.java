package com.nieyue.util;

import net.sf.json.JSONObject;

/**
 * 第三方公共api接口
 * @author Administrator
 *
 */
public class MyThirdAPIUtil {
	/**
	 * 根据地名获取新浪天气
	 * @author Administrator
	 * @param cityAddress 如：常德
	 *
	 */
	public static String getXinLangTianQi(String cityAddress) throws Exception{
		String xinlangtianqi="http://php.weather.sina.com.cn/iframe/index/w_cl.php?code=js&day=0&city="+cityAddress+"&dfc=1&charset=utf-8";
		String result = HttpClientUtil.doGet(xinlangtianqi);
		
		return result;
	}
	/**
	 * 根据地名编号获取气象局天气
	 * @author Administrator
	 * @param cityAddressNumber 如：101010100
	 */
	public static String getQiXiangJuTianQi(String cityAddressNumber) throws Exception{
		String xinlangtianqi="http://www.weather.com.cn/data/cityinfo/"+cityAddressNumber+".html";
		String result = HttpClientUtil.doGet(xinlangtianqi);
		
		return result;
	}
	/**
	 * 根据地名获取CDN天气
	 * @author Administrator
	 * @param cityAddress 如：常德
	 */
	public static String getCDNTianQi(String cityAddress) throws Exception{
		String xinlangtianqi="http://wthrcdn.etouch.cn/weather_mini?city="+cityAddress;
		String result = HttpClientUtil.doGet(xinlangtianqi);
		return result;
	}
	/**
	 * 百度api,根据ip地址获取城市地名
	 * @author Administrator
	 * @param ip 如：110.52.6.0
	 * @return city 如 ：常德
	 */
	public static String getCity(String ip) throws Exception{
		String ak="U3unpKKLGNvoOO10vkkUfPd7Y5bnRpAV";
		String url="http://api.map.baidu.com/location/ip?ip="+ip+"&ak="+ak;
		String result = HttpClientUtil.doGet(url);
		Object content = JSONObject.fromObject(result).get("content");
		Object address_detail = JSONObject.fromObject(content).get("address_detail");
		String city = JSONObject.fromObject(address_detail).get("city").toString();
		if(city.indexOf("市")>-1){
			city=city.substring(0,city.indexOf("市"));
		}
		return city;
	}
}
