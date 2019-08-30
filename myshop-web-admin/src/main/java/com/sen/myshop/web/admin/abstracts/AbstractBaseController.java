package com.sen.myshop.web.admin.abstracts;

import com.sen.myshop.commons.dto.BaseResult;
import com.sen.myshop.commons.dto.PageInfo;
import com.sen.myshop.commons.persistence.BaseEntity;
import com.sen.myshop.commons.persistence.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * 基本controller的抽象
 * @Auther: Sen
 * @Date: 2019/8/10 01:34
 * @Description:
 */
public abstract class AbstractBaseController<T extends BaseEntity,S extends BaseService<T>> {
    @Autowired
    protected S service;

    /**
     * 展示所有信息
     * @return
     */
    public abstract String list();

    /**
     * 跳转修改
     * @return
     */
    public abstract String form();

    /**
     * 增加或者修改
     * @param entity
     * @param redirectAttributes
     * @param model
     * @return
     */
    public abstract String save(T entity, RedirectAttributes redirectAttributes, Model model);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    protected BaseResult deleMutil(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNoneBlank(ids)) {
            String[] userids = ids.split(",");
            service.deleteMutilServic(userids);
            baseResult = BaseResult.success("批量删除操作成功");
        } else {
            baseResult = BaseResult.failed("批量删除操作失败");
        }
        return baseResult;
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<T> getPage(HttpServletRequest request, T entity) {
        String strdraw = request.getParameter("draw");
        String strstart = request.getParameter("start");
        String strlength = request.getParameter("length");
        int draw = strdraw == null ? 0 : Integer.parseInt(strdraw);
        int start = strstart == null ? 0 : Integer.parseInt(strstart);
        int length = strlength == null ? 0 : Integer.parseInt(strlength);
        PageInfo<T> pageInfo = service.findByPage(start, length, draw, entity);
        return pageInfo;
    }

    /**
     * 查看详情
     * @return
     */
    public abstract String detail();

}
