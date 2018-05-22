package com.example.steve.sfv12;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{

    private static String REGISTER_URL = "http://dev-space.net/sf/register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, String email, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_URL, listener, null);
        params = new HashMap<>();

        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
