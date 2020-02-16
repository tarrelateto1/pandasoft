package service;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import strcture.Item;
import strcture.Page1;

public class ListService extends AsyncTask<String,Long,Boolean> {

    String url;

    @Override
    protected Boolean doInBackground(String... url) {
        System.out.println(url);
        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
    }
//    private Page1 getListNew(String url){
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
////                            System.out.println("test : "+response.toString());
//                            JSONArray jsonArray = response.getJSONArray("data");
//                            Gson gson = new Gson();
//                            Page1 page = gson.fromJson(response.toString(), Page1.class);
//
//                            System.out.println(page.status);
//                            for (Item item : page.data) {
//                                System.out.println("ID : " + item.id);
//                                System.out.println("Title : " + item.title);
//                            }
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject employee = jsonArray.getJSONObject(i);
//
//                                String firstName = employee.getString("title");
//                                int age = employee.getInt("id");
//                                String mail = employee.getString("uuid");
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//    }
}
