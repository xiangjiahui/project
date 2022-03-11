package com.xjh.gamestory.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.xjh.gamestory.common.JsonResult;
import com.xjh.gamestory.domain.District;
import com.xjh.gamestory.service.DistrictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 向嘉晖
 * @date 2022/3/1
 */
@Controller
public class PageController {

    @Resource(name = "districtService")
    private DistrictService districtService;

    @GetMapping("/page")
    @ResponseBody
    public JsonResult list() {
        PageMethod.startPage(1,10);
        List<District> districts = districtService.getDistricts();
        PageInfo<District> pageInfo = new PageInfo<>(districts);
        return JsonResult.success(pageInfo);
    }


    @GetMapping("/pageTest")
    public String page(){
        return "page";
    }

}
