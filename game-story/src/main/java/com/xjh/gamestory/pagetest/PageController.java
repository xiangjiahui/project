package com.xjh.gamestory.pagetest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjh.gamestory.common.JsonResult;
import com.xjh.gamestory.common.page.PageResult;
import com.xjh.gamestory.domain.District;
import com.xjh.gamestory.service.DistrictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 向嘉晖
 * @date 2022/3/28
 */
@Controller
public class PageController {

    @Resource(name = "districtService")
    private DistrictService districtService;

    private final Logger logger = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/page/{current}/{limit}")
    @ResponseBody
    public JsonResult pageTest(@PathVariable("current") long current,
                               @PathVariable("limit") long limit){
        logger.info("分页测试开始");
        Page<District> districtPage = new Page<>(current, limit);

        districtService.page(districtPage);

        //总记录数
        long total = districtPage.getTotal();

        //数据集合
        List<District> records = districtPage.getRecords();

        PageResult<District> pageResult = new PageResult<>(records, (int) total, (int) current);

        logger.info("分页测试结束");
        return JsonResult.success(pageResult);
    }
}
