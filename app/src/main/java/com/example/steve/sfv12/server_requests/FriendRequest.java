package com.example.steve.sfv12.server_requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FriendRequest extends StringRequest{
    private static String REGISTER_URL = "http://dev-space.net/sf/friend_request.php";
    private Map<String, String> params;

    public FriendRequest(String user, String friend, Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_URL, listener, null);
        params = new HashMap<>();


        params.put("user", user);
        params.put("friend", friend);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
