package com.example.myapplication.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Item;
import com.example.myapplication.R;
import com.example.myapplication.SQL.DatabaseHelper;

import static com.example.myapplication.MainActivity.items;


public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private OnItemListener mItemListener;
    private Context mContext;
    private Cursor mCursor;
    DatabaseHelper myDB;

    public ItemListAdapter(OnItemListener mItemListener) {
        this.mItemListener = mItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        //LayoutInflater inflater = LayoutInflater.from(mContext);
        return new ViewHolder(v, mItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Item i =items.get(position);
    //Item i = myDB.getListContents()
    holder.tvName.setText(i.getName());
    holder.tvQuantity.setText("Quantity: "+i.getQuantity());
    holder.tvDescription.setText(i.getDescription());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void onActivityResult(int requestCode, int resultCode) {
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName, tvDescription, tvQuantity;
        OnItemListener onItemListener;
        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvQuantity = itemView.findViewById(R.id.tv_quantity);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onItemListener.onItemClick(getAdapterPosition());

        }
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }
}
