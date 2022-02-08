package com.moringaschool.cocktails.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.cocktails.CocktailDetailActivity;
import com.moringaschool.cocktails.CocktailDetailFragment;
import com.moringaschool.cocktails.R;
import com.moringaschool.cocktails.models.Drink;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CocktailListAdapter extends RecyclerView.Adapter<CocktailListAdapter.myHolder> implements View.OnClickListener{
    private List<Drink> mCocktails;
    private Context mContext;

    public CocktailListAdapter(Context context, List<Drink> cocktails) {
        mContext = context;
        mCocktails = cocktails;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cocktail_list_item, parent, false);
        myHolder viewHolder = new myHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.bindCocktail(mCocktails.get(position));
    }

    @Override
    public int getItemCount() {
        return mCocktails.size();
    }

    class myHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cocktailImageView) ImageView mCocktailImageView;
        @BindView(R.id.cocktailNameTextView) TextView mNameTextView;
        @BindView(R.id.typeTextView) TextView mTypeTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        private Context mContext;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindCocktail(Drink drink) {
            mNameTextView.setText(drink.getStrDrink());
           mTypeTextView.setText(drink.getStrAlcoholic());
            mCategoryTextView.setText(drink.getStrCategory());
            Picasso.get().load(drink.getStrDrinkThumb()).into(mCocktailImageView);
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, CocktailDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("restaurants", Parcels.wrap(mCocktails));
            mContext.startActivity(intent);
        }
    }
}




