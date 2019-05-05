package com.example.tablayout;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    List<ItemModel> itemList;
    Context context;

    public ItemAdapter(List<ItemModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_row_recycler_view,viewGroup,false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        final ItemModel ItemModel=itemList.get(i);
        itemViewHolder.tv_heading.setText(ItemModel.getItemName());
        String imageName="@drawable/"+itemList.get(i).getItemImageName();
        final int imageResource= context.getResources().getIdentifier(imageName,null, context.getPackageName());
        itemViewHolder.img.setImageResource(imageResource);

        itemViewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,EachItem.class);
                intent.putExtra("itemName",ItemModel.getItemName());
                intent.putExtra("itemPrice",ItemModel.getItemPrice());
                intent.putExtra("itemImageName",imageResource);
                intent.putExtra("itemDescription",ItemModel.getItemDescription());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_heading;
        public ImageView img;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_heading=itemView.findViewById(R.id.sample_heading);
            img=itemView.findViewById(R.id.sample_image);
        }
    }

}
