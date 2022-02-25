package com.xjh.gamestory.controller;

import com.xjh.gamestory.exception.CartNotFoundException;
import com.xjh.gamestory.exception.DeleteException;
import com.xjh.gamestory.exception.InsertException;
import com.xjh.gamestory.exception.UpdateException;
import com.xjh.gamestory.service.CartService;
import com.xjh.gamestory.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 向嘉晖
 * @date 2022/2/25
 */
@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

    @Resource(name = "cartService")
    private CartService cartService;


    @PostMapping("/add_to_cart")
    @ResponseBody
    public JsonResult addCart(Integer pid, Integer amount, HttpSession session) {
        try {
            cartService.addToCart(getUidFromSession(session), pid, amount);
            return JsonResult.success();
        } catch (InsertException e) {
            return JsonResult.error("插入数据失败");
        }
    }

    @GetMapping("/cart_list")
    @ResponseBody
    public JsonResult getCarts(HttpSession session) {
        return JsonResult.success(cartService.getAllCart(getUidFromSession(session)));
    }


    @PostMapping("/num/add/{cid}")
    @ResponseBody
    public JsonResult addCartNumber(@PathVariable(name = "cid") Integer cid, HttpSession session) {
        try {
            Integer number = cartService.addNumber(cid, getUidFromSession(session), getUsernameFromSession(session));
            return JsonResult.success(number);
        } catch (CartNotFoundException e) {
            return JsonResult.failed();
        } catch (UpdateException e) {
            return JsonResult.error();
        }
    }


    @PostMapping("/num/reduce/{cid}")
    @ResponseBody
    public JsonResult reduceCartNumber(@PathVariable(name = "cid") Integer cid, HttpSession session) {
        try {
            Integer number = cartService.reduceNumber(cid, getUidFromSession(session), getUsernameFromSession(session));
            return JsonResult.success(number);
        } catch (CartNotFoundException e) {
            return JsonResult.failed();
        } catch (DeleteException e) {
            return JsonResult.error("商品数量最少为1");
        }
    }
}
