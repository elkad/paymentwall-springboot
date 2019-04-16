package com.example.paymentwall;

import com.paymentwall.java.Charge;
import com.paymentwall.java.Config;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BrickDirectService {

    @Value("${brick.public.key}")
    private String publicKey;

    @Value("${brick.private.key}")
    private String privateKey;

    Charge chargeAction(Map<String, String> requestParams) {
        log.info(requestParams.toString());

        Config.getInstance().setPublicKey(publicKey);
        Config.getInstance().setPrivateKey(privateKey);

        LinkedHashMap<String,String> chargemap = new LinkedHashMap<>();
        chargemap.put("token", requestParams.get("brick_token"));
        chargemap.put("email", "test@paymentwall.com");
        chargemap.put("currency", "USD");
        chargemap.put("amount", "9.99");
        chargemap.put("fingerprint", requestParams.get("brick_fingerprint"));
        chargemap.put("description", "description");
        chargemap.put("additional_parameter_name", "additonal_parameter_value");

        Charge charge = new Charge();
        try {
            charge = (Charge)charge.create(chargemap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charge;
    }

}
