package yhtTestNG02.yhtTestNG02;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yonyou.yht.entity.OutsideUser;
import com.yonyou.yht.sdk.SDKUtils;
import com.yonyou.yht.sdk.UserCenter;
import com.yonyou.yht.sdkutils.YhtClientPropertyUtil;

import net.sf.json.JSONObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import yhtTestNG02.yhtTestNG02.ExcelData;

//参考地址：https://www.cnblogs.com/longronglang/p/9972811.html

public class UserTestNG {
	static ObjectMapper mapper = new ObjectMapper();
  @Test(dataProvider = "testData")
//  public void f(Integer n, Map<String, String> s) {
//	  s = (Map<String, String>) ExcelData.readExcelData("/Users/juandu/Documents/code/excelData.xls");
//	  System.out.println("第一个参数是"+n+",第二个参数是"+s);
//  }
  public void testmethod1(Map<?, ?> param){
	  System.out.println("hahah"+param);
      System.out.println(param.get("usercode")+"\t"+param.get("usermobile")+"\t"+param.get("useremail"));
  }
  @DataProvider(name = "testData" )
  public Object[][] dp() {
//    return new Object[][] {
//      new Object[] { 1, "a" },
//      new Object[] { 2, "b" },
//    }
	  List<Map<String, String>> result = ReadExcelUtil2.getExcuteList("/Users/juandu/Documents/code/excelData.xls");
//	  List<Map<String, String>> result = ExcelData.readExcelData("/Users/juandu/Documents/code/excelData.xls");
      Object[][] files = new Object[result.size()][];
      for(int i=0; i<result.size(); i++){
          files[i] = new Object[]{
        		  result.get(i)
        		 };
      }        
      return files;
  }
//    public  Map<String, String> readExcelData(){
//	  Map<String, String> map = ExcelData.readExcelData("/Users/juandu/Documents/code/excelData.xls");
//	  System.out.println(map);
//		return map;
//    	
//    	
//    }
  @Test(dataProvider = "testData")
 	/*
 	 * isUserExist
 	 * usercode	String	用户帐号（编码）
      userMobile	String	用户手机号（userMobile,userEmail不能同时为空）
      userEmail	String	用户邮箱
 	 * 正常情况的测试 
 	 */
 	public void isUserExistTest(Map<?, ?> param) throws JsonProcessingException, IOException {
// 				        String str1= UserCenter.isUserExist("",null,null);
	        String str1= UserCenter.isUserExist(param.get("usercode").toString(),param.get("usermobile").toString(),param.get("useremail").toString());
 						System.out.println("手机号和邮箱不能同时为空:0");
 						System.out.println(str1);
// 						JsonNode node1 = mapper.readTree(str1);
// 						Assert.assertTrue(node1.get("status").asInt()==0);


 	}
//  private void readExcelData() {
//	// TODO Auto-generated method stub
//	
//}
@Test
	public void getThirdLoginCodeTest() {
		OutsideUser user = new OutsideUser();
      user.setThirdUcId("thirdUcId");//thirdUcId，唯一的
      user.setUserId("1001");
//      user.setMobile("17801123456");
//      user.setEmail("test0514dd@test1988.com");
      user.setUserCode("qqe");
//      user.setUserName("weqeq");//username还是按照新用户处理
		String msg = UserCenter.getThirdLoginCode(user);
		System.out.println(msg); 
		
	}
  @Test
	public void simpleAddUsersTest() throws JsonProcessingException, IOException {
		String userStr = UserCenter.getUserByCode("");
		String  jsonStr = "{\"users\":[{\"userCode\":\"ptest1\",\"userPassword\":\"qwaszx12\",\"userMobile\":\"17802888888\",\"userName\":\"dd01\",\"userEmail\":\"test041101@yonyou.com\"},{\"userMobile\":\"13412343432\",\"userName\":\"aasaw\",\"userEmail\":\"ptest2@yonyou.com\"}],\"sysId\":\"ipu\"}";
		String sysid = "ipu";      

		String msg = UserCenter.simpleAddUsers(jsonStr, sysid);
		System.out.println(msg);
//		JSONObject resultJson = JSONObject.fromObject(msg);
//		System.	out.println(resultJson);
//		String data = resultJson.getString("data");
//		JSONObject dataJson = JSONObject.fromObject(data);
//		String yhtAccessToken = dataJson.getString("access_token");
		
	}
  @Test
	/*
	 * 根据密码获取accesstoken 正常情况的测试 文档里有链接的
	 */
	public void generateAccessTokenTest() throws JsonProcessingException, IOException, InterruptedException {

		String username = "15210142172";//测试环境 ：17601888888 预发环境：19901888888
		String password = "yonyou@1988";
		String md5Password = SDKUtils.encodeUsingMD5(password);
		String shaPassword = SDKUtils.encodeUsingSHA(password);
		//		System.out.println(md5Password);
		//		System.out.println(shaPassword);
		String msg = UserCenter.generateAccessToken(username, password, true);
		//		String msg1 = UserCenter.createAccessToken(arg0, arg1, arg2, arg3)

		System.out.println(msg);
		JsonNode node = mapper.readTree(msg);
		//		Assert.assertTrue(node.get("status").asInt() == 1);
	}
  @Test
	/*
	 * 根据accesstoken获取用户信息 正常情况的测试
	 */
	public void getUserByTokenTestdd() throws JsonProcessingException, IOException, InterruptedException {


		// 获取accesstoken
		//String msg1 = UserCenter.genAccessTokenBySSOToken(ssotoken);
		//		String username = "16703888888";
		//		String Password = "yonyou@1988";
		//		 String tenantId = "ypmi9gbr";
		//		   String orgId = "6666";
		//		String msg = UserCenter.getU8cSystemAccessToken(tenantId, orgId);
		//		System.out.println(msg);
		//		JsonNode node1 = mapper.readTree(msg);
		//		//String accessToken = node1.get("accesstoken").asText();
		//		String accessToken = node1.get("data").get("access_token").asText();
		//		System.out.println(accessToken);
		// 本测试方法的代码
//		String accessToken = "bttekNPQjVkM1QyWHJnbjVXbGVxUHkxRDJMUkltZk0yUVdsa244YWpuQUJJYkdNRTArdHlGbWN5RmJWY3Q5SCtiUStJeGlmWjJHczNlcTRkRTh0blhNM3BxNmxPZGN5RTBqMW4za2toM1Q1RGs9Xzk5ZWE3NjU1LTAwYTItNGJkYS1iMjNjLTE5YWRlMzdlYTU3NF9pZHRlc3QueXl1YXAuY29tXzE1NzU5NDMwMDI5ODY";
		String accessToken = "2dad9e01-1850-4dca-aebf-354559563e04";
		String version_accessToken = "2dad9e01-1850-4dca-aebf-354559563e04__9da765949a7f59686b0bc5ef1388f48c_1587625581732";
		String msg2 = UserCenter.getUserByToken(version_accessToken);
		System.out.println(" 根据accesstoken获取用户信息 "+msg2);
		JsonNode node2 = mapper.readTree(msg2);
//		Assert.assertTrue(node2.get("status").asInt() == 1);
	}
  @Test
  //(invocationCount = 50) -用例循环执行多少次
	/*
	 * 根据用户ID获取用户信息 正常流程测试
	 *敏感用户用户名正常显示，不显示 *****
	 */
	public  void getUserByIdTest() throws JsonProcessingException, IOException {
		//String userName = "15210142172";//用户名就是姓名、昵称
		
//		String userId = "722b78ba-53fc-4200-bfdf-682f97c31e3a";// idtest 15210142172
		String userId = "677088bf-d486-4c46-b88b-8a7ac1f4b58b";//test0518bb@test1988.com      ff77629a-eefd-46be-9164-7cf92a660371 
		String msg = UserCenter.getUserById(userId);
		System.out.println("根据用户ID获取用户信息"+msg);
//		JsonNode node = mapper.readTree(msg);
//				Assert.assertTrue(node.get("status").asInt() == 1);
		JSONObject jsonObject = JSONObject.fromObject(msg);
		String IdString = jsonObject.getJSONObject("user").getString("userId");
		System.out.println(IdString);
    	String userString = "{\"name\":\"shily\",\"sex\":\"女\",\"age\":\"23\"}";
    	String userName = 	jsonObject.getJSONObject("user").getString("userName");
    	System.out.println(userName);
//    	JSONObject actual = JSONObject.fromObject(userString);
//		Assert.assertEquals(jsonObject.getString("user"), actual);
    	
//    	Assert.assertTrue(userName == "bb");
    	AssertJUnit.assertEquals(IdString, "677088bf-d486-4c46-b88b-8a7ac1f4b58b");
    	AssertJUnit.assertEquals(userName, "bb");

	}
  @Test
	/*
	 * 根据密码和域获取accessToken
	 * getUserByTokenTestdd()-根据AccessToken获取用户
	 * scopecode
	 * String是登录名（即为域用户表中scope_code 字段）
   password	String	是	明文密码
    multiLogin	boolean	是	是否支持多端登录
   scope	String	是	域的名称
	 * 
	 * */
	public void generateAccessTokenWithScopeTest() throws JsonProcessingException,IOException{
//		
		String scope = "22";
		String scopecode = "22"; 
		String password = "yonyou@1988";
		boolean multiLogin = false;
		String msg = UserCenter.generateAccessTokenWithScope(scopecode, password, multiLogin, scope);
		System.out.println(msg); 
		
	}
	@Test
	/*
	 * 根据accessToken获取logintoken
	 * 在根据logintoken登录
	 * https://idtest.yyuap.com/cas/login?token=ea0e9ceb1f838284c2caaaf0c1efbae17f6b907628f33a2838f3a50ddb85f452
	 * 
	 * */
	public void getLoginTokenByAccessTokenTest() throws JsonProcessingException,IOException{
//		
		String accessToken = "edd6b8d2-2243-4424-98a2-d7bb88c443f0";
		String msg = UserCenter.getLoginTokenByAccessToken(accessToken);
		System.out.println(msg); 
		
	}
 
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("------------开始测试------------");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("------------结束测试------------");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("------------hahah开始测试------------");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("------------ahha结束测试------------");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("------------ahh222开始测试------------");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("------------ahh222结束测试------------");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("------------ahh333开始测试------------");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("------------ahh333结束测试------------");
  }

}
