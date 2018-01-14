package com.hyperknob.fintech.coin.trader.controller;

import com.hyperknob.fintech.coin.trader.bean.vo.ApiKey;
import com.hyperknob.fintech.coin.trader.bean.MessageRes;
import com.hyperknob.fintech.coin.trader.cache.ApiKeyPairCache;
import com.hyperknob.fintech.coin.trader.constant.Constants;
import com.hyperknob.fintech.coin.trader.service.OkexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomePageController {
    @Autowired
    private OkexService okexService;

    @PostMapping(value = "/addApiKey")
    @ResponseBody
    public MessageRes addApiKey(ApiKey apiKey) {
        if (null != apiKey &&
                !StringUtils.isEmpty(apiKey.getKeyName()) &&
                !StringUtils.isEmpty(apiKey.getKey()) &&
                !StringUtils.isEmpty(apiKey.getSecret())) {
            ApiKeyPairCache.addKeyPair(apiKey.getKeyName(), apiKey);
            if (Constants.OKEX_API_KEYPAIR_NAME.equals(apiKey.getKeyName())) {
                okexService.activateApiKey();
                return new MessageRes(200, "");
            } else {
                return new MessageRes(400, "错误的交易所");
            }
        } else {
            return new MessageRes(400, "输入有空值");
        }
    }

    /**
     * 首页
     * GET
     */
    @RequestMapping("/index")
    public String viewIndex(HttpServletRequest request, Model model) {
        return "index";
    }
}