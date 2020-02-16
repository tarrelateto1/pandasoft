package test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import strcture.ListItem;
import strcture.ListData;


public class MainActivity extends AppCompatActivity {

    Button butttonLogin;
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        butttonLogin = (Button) findViewById(R.id.buttonLogin);

        butttonLogin.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, ListNews.class);
                startActivity(i);
            }
        });
//        jsonParse();


    }

    private void jsonParse() {

        String url = "https://5c065a3fc16e1200139479cc.mockapi.io/api/v1/news";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
//                            System.out.println("test : "+response.toString());
                            JSONArray jsonArray = response.getJSONArray("data");
                            Gson gson = new Gson();
                            ListData page = gson.fromJson(response.toString(), ListData.class);
                            System.out.println(page.status);
                            for (ListItem item : page.data) {
                                System.out.println("ID : " + item.id);
                                System.out.println("ID : " + item.title);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

    }


}
