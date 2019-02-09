package com.hosttheweb.khabo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hosttheweb.khabo.R;
import com.hosttheweb.khabo.adapters.MenuAdapter;
import com.hosttheweb.khabo.api.ApiClient;
import com.hosttheweb.khabo.api.ApiInterface;
import com.hosttheweb.khabo.model.Menu;
import com.hosttheweb.khabo.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Menu> menuList;
    private ListView listView;
    private MenuAdapter menuAdapter;
    private static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = findViewById(R.id.menuRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuList = new ArrayList<>();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        getMenus();
    }
    public void getMenus(){
        Call<List<Menu>> call = apiInterface.getMenus();

        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                try {
                    JSONArray menus = new JSONArray(response);
                    for (int i = 0; i < menus.length(); i++) {
                        JSONObject menuObject = menus.getJSONObject(i);
                        int id = menuObject.getInt("id");
                        String menuitem = menuObject.getString("menuitem");
                        String image = menuObject.getString("image");
                        Menu menulist = new Menu(id, menuitem, image);
                        Log.d("Problem",image);
                        menuList.add(menulist);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                menuAdapter = new MenuAdapter(OrderActivity.this, menuList);
                recyclerView.setAdapter(menuAdapter);

            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {

            }
        });
    }
}
