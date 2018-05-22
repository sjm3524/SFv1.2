package com.example.steve.sfv12;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends StringRequest {

    private static String SEARCH_URL = "http://dev-space.net/sf/search.php";
    private Map<String, String> params;

    public SearchRequest(String username, Response.Listener<String> listener){
        super(Method.POST, SEARCH_URL, listener, null);
        params = new HashMap<>();


        params.put("username", username);



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
