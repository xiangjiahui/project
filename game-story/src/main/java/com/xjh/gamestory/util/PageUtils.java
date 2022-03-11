package com.xjh.gamestory.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import com.xjh.gamestory.common.page.PageDomain;
import com.xjh.gamestory.common.page.TableSupport;

/**
 * 分页工具类
 *
 * @author 向嘉晖
 * @date 2022/3/1
 */
public class PageUtils extends PageHelper {

    /**
     * 设置请求分页数据
     */
    public static void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            Boolean reasonable = pageDomain.getReasonable();
            PageMethod.startPage(pageNum, pageSize).setReasonable(reasonable);
        }
    }
}
