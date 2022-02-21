package com.xjh.gamestory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xjh
 * @date 2022/2/17 14:00
 */
@Controller
public class WebController {

    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/web/index")
    public String toWebIndex(){
        return "/web/index";
    }


    @GetMapping("/web/404")
    public String to404(){
        return "/web/404";
    }

    @GetMapping("/web/500")
    public String to500(){
        return "/web/500";
    }

    @GetMapping("/web/addAddress")
    public String toAddAddress(){
        return "/web/addAddress";
    }

    @GetMapping("/web/address")
    public String toAddress(){
        return "/web/address";
    }

    @GetMapping("/web/cart")
    public String toCart(){
        return "/web/cart";
    }

    @GetMapping("/web/favorites")
    public String toFavorites(){
        return "/web/favorites";
    }

    @GetMapping("/web/footerTemplate")
    public String toFooterTemplate(){
        return "/web/footerTemplate";
    }

    @GetMapping("/web/leftTemplate")
    public String toLeftTemplate(){
        return "/web/leftTemplate";
    }

    @GetMapping("/web/login")
    public String toLogin(){
        return "/web/login";
    }

    @GetMapping("/web/orderConfirm")
    public String toOrderConfirm(){
        return "/web/orderConfirm";
    }

    @GetMapping("/web/orderInfo")
    public String toOrderInfo(){
        return "/web/orderInfo";
    }

    @GetMapping("/web/orders")
    public String toOrders(){
        return "/web/orders";
    }

    @GetMapping("/web/password")
    public String toPassword(){
        return "/web/password";
    }

    @GetMapping("/web/payFail")
    public String toPayFail(){
        return "/web/payFail";
    }

    @GetMapping("/web/payment")
    public String toPayment(){
        return "/web/payment";
    }

    @GetMapping("/web/paySuccess")
    public String toPaySuccess(){
        return "/web/paySuccess";
    }

    @GetMapping("/web/product")
    public String toProduct(){
        return "/web/product";
    }

    @GetMapping("/web/register")
    public String toRegister(){
        return "/web/register";
    }

    @GetMapping("/web/search")
    public String toSearch(){
        return "/web/search";
    }

    @GetMapping("/web/template")
    public String toTemplate(){
        return "/web/template";
    }

    @GetMapping("/web/topTemplate")
    public String toTopTemplate(){
        return "/web/topTemplate";
    }

    @GetMapping("/web/upload")
    public String toUpload(){
        return "/web/upload";
    }

    @GetMapping("/web/userdata")
    public String toUserData(){
        return "/web/userdata";
    }

}
