package com.sen.myshop.web.ui.api;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.utils.HttpClientUtils;
import com.sen.myshop.commons.utils.MapperUtils;
import com.sen.myshop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户登录应用程序接口
 * @Auther: Sen
 * @Date: 2019/8/12 16:36
 * @Description:
 */
public class UserApi {
    public static TbUser loginApi(TbUser tbUser) throws Exception {
        //设置参数
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", tbUser.getUsername()));
        params.add(new BasicNameValuePair("password", tbUser.getPassword()));
        String json = HttpClientUtils.doPost(Api.API_USER_LOGIN, null, params);
        TbUser user = MapperUtils.json2pojoByTree(json, "data", TbUser.class);
        return user;
    }
}
