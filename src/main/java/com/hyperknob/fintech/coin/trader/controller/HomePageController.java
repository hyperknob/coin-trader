package com.hyperknob.fintech.coin.trader.controller;

import com.hyperknob.fintech.coin.trader.bean.ApiKey;
import com.hyperknob.fintech.coin.trader.cache.ApiKeyPairCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomePageController {
    @RequestMapping(value = "/addApiKey", method = RequestMethod.POST, consumes="application/json")
    public String addApiKey(ApiKey apiKey) {
        if (null != ApiKeyPairCache.addKeyPair(apiKey.getKeyName(),apiKey)) {
            return "redirect:/market";
        } else {
            return  null;
        }
    }

    /**
     * 首页
     * GET
     */
    @GetMapping("/index")
    public String pFrontIndex(HttpServletRequest request, Model model) {
        return "index";
    }

    /**
     * 首页
     * POST
     */
    @PostMapping("/index")
    public String pFrontIndexPost(HttpServletRequest request, Model model) {
        return "index";
    }
}
