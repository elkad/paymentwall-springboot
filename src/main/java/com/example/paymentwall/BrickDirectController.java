package com.example.paymentwall;

import com.paymentwall.java.Charge;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class BrickDirectController {

    @Value("${brick.public.key}")
    private String publicKey;

    private final BrickDirectService brickDirectService;

    public BrickDirectController(BrickDirectService brickDirectService) {
        this.brickDirectService = brickDirectService;
    }

    @GetMapping("/payment/default")
    ModelAndView brickForm() {
        Map<String, Object> params = new HashMap<>();
        params.put("public_key", publicKey);

        return new ModelAndView("brickForm", params);
    }

    @GetMapping("/payment/custom")
    ModelAndView yourOwnForm() {
        Map<String, Object> params = new HashMap<>();
        params.put("public_key", publicKey);

        return new ModelAndView("yourOwnForm", params);
    }

    @PostMapping("/brick-action")
    @ResponseBody
    ChargeActionResponse brickChargeAction(@RequestParam Map<String,String> allRequestParams) {
        Charge charge = brickDirectService.chargeAction(allRequestParams);
        if(charge.isSuccessful()) {
            return new ChargeActionResponse(true, charge.getPublicData());
        }

        return new ChargeActionResponse(false, charge.getPublicData());
    }

    @PostMapping("/form-action")
    String formChargeAction(@RequestParam Map<String,String> allRequestParams) {
        Charge charge = brickDirectService.chargeAction(allRequestParams);

        if (charge.isSuccessful()){
            if (charge.isCaptured()){
                // redirect your customer to payment success page
                return "chargeComplete";
            } else if (charge.isUnderReview()){
                // redirect your customer to payment under review page
                return "chargeComplete";
            } else {
                // redirect your customer to payment failure page
                return "chargeFailed";
            }
        }

        return "chargeFailed";
    }

}
