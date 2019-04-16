package com.example.paymentwall;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class PingbackController {

    @GetMapping("/PingBack")
    @ResponseBody
    String pingback(@RequestParam Map<String,String> allRequestParams) {
        log.info(allRequestParams.toString());
        return "OK";
    }

}
