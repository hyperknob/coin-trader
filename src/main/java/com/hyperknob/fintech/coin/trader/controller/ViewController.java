package com.hyperknob.fintech.coin.trader.controller;

import com.hyperknob.fintech.coin.trader.cache.ApiKeyPairCache;
import com.hyperknob.fintech.coin.trader.service.OkexService;
import com.hyperknob.fintech.coin.trader.service.UserMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {
    @Autowired
    private OkexService okexService;

    @Autowired
    private UserMarketService userMarketService;

    /**
     * 首页
     * GET
     */
    @GetMapping("/index")
    public String viewIndex(HttpServletRequest request, Model model) {
        return "index";
    }

    /**
     * 市场页
     * POST
     */
    @GetMapping("/market")
    public String list(Model model) {
        if (ApiKeyPairCache.isEmpty()) {
            return "index";
        } else {
            model.addAttribute("currencyPairList", userMarketService.getUserCurrencyPairbyUid(1));
            return "market/list";
        }
    }
}
