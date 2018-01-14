package com.hyperknob.fintech.coin.trader.controller;

import com.hyperknob.fintech.coin.trader.service.OkexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/market")
public class MarketController {
    @Autowired
    private OkexService okexService;

    /**
     * 首页
     * POST
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("currencyPairList");
        return "market/list";
    }
}
