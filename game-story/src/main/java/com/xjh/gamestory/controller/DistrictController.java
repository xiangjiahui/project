package com.xjh.gamestory.controller;

import com.xjh.gamestory.domain.District;
import com.xjh.gamestory.service.DistrictService;
import com.xjh.gamestory.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 向嘉晖
 * @date 2022/2/23
 */
@Controller
@RequestMapping("/district")
public class DistrictController extends BaseController{

    @Resource(name = "districtService")
    private DistrictService districtService;


    @GetMapping("/getDistrict")
    @ResponseBody
    public JsonResult getDistrict(String parent){
        List<District> list = districtService.getDistrictByParent(parent);
        return JsonResult.success(list);
    }

}
