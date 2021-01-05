package com.xiansi.provider;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.xiansi.dto.AccessTokenDTO;
import com.xiansi.dto.GithubUser;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 * 
 *
 * 使用OkHttp进行github登录
 * @author 弦思
 * @2021-1-3
 */
@Component
public class GithubProvider {
	public String getAccessToken(AccessTokenDTO accessTokenDTO) {
		MediaType mediaType = MediaType.get("application/json;charset=utf-8");
		OkHttpClient client = new OkHttpClient().newBuilder()
				.callTimeout(60000, TimeUnit.MILLISECONDS).readTimeout(60000,TimeUnit.MILLISECONDS)
				.retryOnConnectionFailure(true).build();
		RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
		Request request = new Request.Builder()
				.url("https://github.com/login/oauth/access_token")
				.post(body)
				.build();
			try(Response response = client.newCall(request).execute()){
				String string = response.body().string();
				//System.out.println(string);
				String token = string.split("&")[0].split("=")[1];
				//System.out.println(token);
				return token;
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			return null;	
	}
	public GithubUser getUser(String accessToken) {
		OkHttpClient client = new OkHttpClient().newBuilder().callTimeout(60000, TimeUnit.MILLISECONDS)
				.readTimeout(60000,TimeUnit.MILLISECONDS).retryOnConnectionFailure(true).build();
		Request request = new Request.Builder()
				.url("https://api.github.com/user")
				.header("Authorization", "token "+ accessToken)
				.build();
		try {
			Response response = client.newCall(request).execute();
			String string = response.body().string();
			GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
			return githubUser;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
}
