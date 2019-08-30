package com.sen.myshop.web.ui.api;

import com.sen.myshop.commons.utils.HttpClientUtils;
import com.sen.myshop.commons.utils.MapperUtils;
import com.sen.myshop.web.ui.dto.Content;

import java.util.List;

/**
 * 获取幻灯片
 * @Auther: Sen
 * @Date: 2019/8/12 02:53
 * @Description:
 */
public class ConstantApi {
    public static List<Content> ppt() throws Exception {
        String json = HttpClientUtils.doGet(Api.API_CONTENTS_PPT);
        List<Content> data = null;
        if (json != null && json.length()> 0 ) {
            data = MapperUtils.json2listByTree(json, "data", Content.class);
        }
        return data;
    }
}
