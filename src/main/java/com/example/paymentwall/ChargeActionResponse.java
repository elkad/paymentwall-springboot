package com.example.paymentwall;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
public class ChargeActionResponse {
    private boolean success;
    private JSONObject data;

    public ChargeActionResponse(boolean success, JSONObject publicData) {
        this.success = success;
        this.data = publicData;
    }
}
