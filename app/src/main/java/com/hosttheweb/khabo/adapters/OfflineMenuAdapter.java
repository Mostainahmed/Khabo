package com.hosttheweb.khabo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hosttheweb.khabo.R;
import com.hosttheweb.khabo.model.Menu;
import com.hosttheweb.khabo.model.OfflineMenu;

import java.util.List;
import java.util.zip.Inflater;

public class OfflineMenuAdapter extends RecyclerView.Adapter<OfflineMenuAdapter.OfflineMenuViewHolder>{

    private Context mCtx;
    List<OfflineMenu> offLineMenuList;
    private int lastCheckedPosition = -1;

    public OfflineMenuAdapter(Context mCtx, List<OfflineMenu> offLineMenuList) {
        this.mCtx = mCtx;
        this.offLineMenuList = offLineMenuList;
    }

    @NonNull
    @Override
    public OfflineMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View v = inflater.inflate(R.layout.menu_item,null);
        return new OfflineMenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OfflineMenuViewHolder offlineMenuViewHolder, int i) {
        OfflineMenu menu = offLineMenuList.get(i);
        offlineMenuViewHolder.menuName.setText(menu.getMenuName());
        offlineMenuViewHolder.menuImageView.setImageDrawable(mCtx.getResources().getDrawable(menu.getMenuImage()));
        if(i == lastCheckedPosition){
            offlineMenuViewHolder.relative.setBackgroundColor(Color.parseColor("#47A025"));
        }else offlineMenuViewHolder.relative.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    @Override
    public int getItemCount() {
        return offLineMenuList.size();
    }

    class OfflineMenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView menuImageView;
        TextView menuName;
        RelativeLayout relative;
        LinearLayout linearLayout;

        public OfflineMenuViewHolder(@NonNull View itemView) {
            super(itemView);

            menuImageView = itemView.findViewById(R.id.menuImageView);
            menuName = itemView.findViewById(R.id.menuName);

            relative = itemView.findViewById(R.id.relativeCard);
            linearLayout = itemView.findViewById(R.id.linearCard);
            relative.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.relativeCard:
                    lastCheckedPosition = getAdapterPosition();
//                    SharedPreferences sharedPref = mCtx.getSharedPreferences("lastOrder", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPref.edit();
//                    editor.putInt("lastorder", lastCheckedPosition);
//                    editor.commit();
//                    editor.apply();
                    notifyDataSetChanged();
                    break;
                default:
                    break;

            }
        }
    }

}
