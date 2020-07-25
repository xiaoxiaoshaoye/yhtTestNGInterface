package yhtTestNG02.yhtTestNG02;
import org.testng.annotations.Test;
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
import org.testng.annotations.Test;
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
//import org.testng.annotations.Ignore;


public class baomaInterface {
	//添加域用户
		/**
		 * 
		 * 
		 * scope  String 是域的名称
	     scopeCode	String	是	在该域下的账号名，注意：scope+scopeCode 组成了唯一索引，不可重复
	     scopeMobile	String	否	手机号
	     scopeEmail	String	否	邮箱
	     userName	String	否	用户昵称
	     userPassword	String	否	用户密码，注意：如果不填，则使用友户通的默认密码，一般情况下，默认密码为：yonyou@1988

		 */
			@Test
			public void addScopeUserTest() throws JsonProcessingException,IOException{
//				
				String scope = "36";
				String scopeCode = "36";
				String scopeMobile = "15210142172";
				String scopeEmail = "";
				String userName = "";
				String userPassword = "";
				String msg = UserCenter.addScopeUser(scope, scopeCode, scopeMobile, scopeEmail, userName, userPassword);
				System.out.println(msg); 
				JSONObject resultJson = JSONObject.fromObject(msg);
				System.	out.println(resultJson);
				String data = resultJson.getString("data");
				JSONObject dataJson = JSONObject.fromObject(data);
				String scope1 = dataJson.getString("scope");
				String scopeCode1 = dataJson.getString("scopeCode");
				Assert.assertEquals(scope1, "36");
				Assert.assertEquals(scopeCode1, "36");
				JSONObject msgJson = JSONObject.fromObject(msg);
				String msg1 = msgJson.getString("msg");
				Assert.assertEquals(msg1, "添加域用户成功！");
				
//				Assert.assertEquals(actual, expected);
				
			}
			@Test
			/*
			 * 修改域用户
			 * 
			 * */
			public void modifyScopeUserTest() throws JsonProcessingException,IOException{
//				
				String scope = "33";
				String scopeCode = "";  
				String scopeMobile = "";
				String scopeEmail = "";
				String userName = "";
				String userPassword = "qwaszx12";
				String msg = UserCenter. modifyScopeUser(scopeCode, scope, scopeMobile, scopeEmail, userName, userPassword);
				System.out.println(msg); 
				
			}
			@Test
			/*
			 * 通过域和该域下的账号，获取域用户信息
			 * scope String是域的名称

	            scopeCode	String	是	在该域下的账号名，注意：scope+scopeCode 组成了唯一索引，不可重复
			 * */
			public void getScopeUserInfoTest() throws JsonProcessingException,IOException{
//				
				String scope = "33";
				String scopeCode = "33";       
				String msg = UserCenter.getScopeUserInfo(scope, scopeCode);
				System.out.println(msg);
				System.out.println("被依赖");
//				return msg; 
				
			}
			//根据密码和域获取accessToken
			
			@Test(dependsOnMethods = {"getScopeUserInfoTest"}, alwaysRun=true)
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
//				String result = getScopeUserInfoTest();
//				System.out.println(result); 
//				JSONObject resultJson = JSONObject.fromObject(result);
//				System.	out.println(resultJson);
//				String data = resultJson.getString("user");
//				JSONObject dataJson = JSONObject.fromObject(data);
//				String scope = dataJson.getString("scope");
				String scope = "33";
				String scopecode = "33"; 
				String password = "qwaszx12";
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
				String accessToken = "e645b900-5b78-4b14-ba9a-8f16ed252493";
				String msg = UserCenter.getLoginTokenByAccessToken(accessToken);
				System.out.println(msg); 
				
			}
			
			@Test
			/*
			 * 根据密码和域获取loginToken
			 * 
			 * */
			public void genLoginTokenWithScopeTest() throws JsonProcessingException,IOException{
//				
				String scope = "33";
				String scopeCode = "33"; 
				String password = "qwaszx12";
				String msg = UserCenter.genLoginTokenWithScope(scopeCode, password, scope);
				System.out.println(msg); 
				
			}
		

}
