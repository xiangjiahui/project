package com.xjh.gamestory.controller;

import com.xjh.gamestory.domain.Address;
import com.xjh.gamestory.exception.*;
import com.xjh.gamestory.service.AddressService;
import com.xjh.gamestory.common.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 向嘉晖
 * @date 2022/2/23
 */
@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {

    @Resource(name = "addressService")
    private AddressService addressService;

    @PostMapping("/add_new_address")
    @ResponseBody
    public JsonResult addAddress(Address address, HttpSession session) {

        try {
            addressService.addAddress(getUidFromSession(session), getUsernameFromSession(session)
                    , address);
            return JsonResult.success();
        } catch (AddressCountLimitException e) {
            return JsonResult.failed("收获地址数量已达上限");
        } catch (InsertException e) {
            return JsonResult.error();
        }
    }


    @GetMapping("/get_all_address")
    @ResponseBody
    public JsonResult getAddressList(HttpSession session) {
        try {
            List<Address> addressList = addressService.getAllAddress(getUidFromSession(session));
            return JsonResult.success(addressList);
        } catch (AddressNotFoundException e) {
            return JsonResult.error();
        }
    }


    @PostMapping("/delete/{aid}")
    @ResponseBody
    public JsonResult deleteAddress(@PathVariable(name = "aid") Integer aid, HttpSession session) {
        try {
            addressService.deleteAddress(getUidFromSession(session), aid);
            return JsonResult.success("删除成功!");
        } catch (AddressNotFoundException e) {
            return JsonResult.error();
        }
    }


    @PostMapping("/set_default/{aid}")
    @ResponseBody
    public JsonResult setDefaultAddress(@PathVariable(name = "aid") Integer aid, HttpSession session) {
        try {
            addressService.setDefault(getUidFromSession(session), aid, getUsernameFromSession(session));
            return JsonResult.success("设置默认收获地址成功!");
        } catch (AccessDeniedException e) {
            return JsonResult.failed("非法访问");
        } catch (UpdateException e) {
            return JsonResult.error("设置默认收货地址时出现未知错误");
        }
    }
}
