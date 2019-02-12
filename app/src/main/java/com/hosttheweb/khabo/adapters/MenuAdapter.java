package com.hosttheweb.khabo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hosttheweb.khabo.R;
import com.hosttheweb.khabo.model.Menu;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

    private Context mCtx;
    private List<Menu> menuList;

    public MenuAdapter(Context mCtx, List<Menu> menuList) {
        this.mCtx = mCtx;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View v =  inflater.inflate(R.layout.menu_item,viewGroup,false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i) {

        Menu menu = menuList.get(i);

        menuViewHolder.menuName.setText(menu.getMenuName());
        Picasso.with(mCtx)
                .load(menu.getMenuImage())
                .placeholder(R.drawable.ic_food)
                .error(R.drawable.ic_error)
                .into(menuViewHolder.menuImageView);

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        ImageView menuImageView;
        TextView menuName;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            menuImageView = itemView.findViewById(R.id.menuImageView);
            menuName = itemView.findViewById(R.id.menuName);
        }
    }

}
