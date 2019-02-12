package com.hosttheweb.khabo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.hosttheweb.khabo.R;
import com.hosttheweb.khabo.activities.OrderActivity;
import com.hosttheweb.khabo.adapters.MenuAdapter;
import com.hosttheweb.khabo.adapters.OfflineMenuAdapter;
import com.hosttheweb.khabo.api.ApiClient;
import com.hosttheweb.khabo.api.ApiInterface;
import com.hosttheweb.khabo.model.Menu;
import com.hosttheweb.khabo.model.OfflineMenu;
import com.hosttheweb.khabo.others.RecyclerTouchListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    private Button orderButton;
    private RecyclerView recyclerView;
    private List<OfflineMenu> menuList;
    private ListView listView;
    private OfflineMenuAdapter menuAdapter;
    private static ApiInterface apiInterface;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_menu, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        orderButton = v.findViewById(R.id.orderConfirmationButton);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        menuList = new ArrayList<>();

        String todaysDate = showDate();

        offLineGetMenus(todaysDate);

        OfflineMenuAdapter adapter = new OfflineMenuAdapter(getContext(),menuList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                orderButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return v;
    }

    private void offLineGetMenus(String date) {

        if(date.equals("Sunday") || date.equals("Tuesday")){
            menuList.add(
                    new OfflineMenu(
                            1,
                            "vat_dim_dal_salad",
                            R.drawable.vat_dim_dal_salad
                    )
            );
            menuList.add(
                    new OfflineMenu(
                            2,
                            "vat_murgi_dal_salad",
                            R.drawable.vat_murgi_dal_salad
                    )
            );
            menuList.add(
                    new OfflineMenu(
                            3,
                            "vat_rui_dal_salad",
                            R.drawable.vat_rui_dal_salad
                    )
            );
            menuList.add(
                    new OfflineMenu(
                            1,
                            "khichuri_murgi",
                            R.drawable.khichuri_murgi
                    )
            );
        }else {
            menuList.add(
                    new OfflineMenu(
                            1,
                            "khichuri_murgi",
                            R.drawable.khichuri_murgi
                    )
            );
        }

    }

//    private void getMenus() {
//        Call<List<Menu>> call = apiInterface.getMenus();
//
//        call.enqueue(new Callback<List<Menu>>() {
//            @Override
//            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
//                menuList = response.body();
//                int size = response.body().size();
//                for (int i = 0; i < size; i++) {
//                    int id = menuList.get(i).getId();
//                    String menuname = menuList.get(i).getMenuName();
//                    String menuimage = menuList.get(i).getMenuImage();
//                    Menu menulist = new Menu(id,menuname,menuimage);
//                    menuList.add(menulist);
//                }
//                menuAdapter = new MenuAdapter(getContext(), menuList);
//                recyclerView.setAdapter(menuAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Menu>> call, Throwable t) {
//
//            }
//        });
//    }

    private String showDate(){
        Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        return simpleDateformat.format(now);
    }
}
