package com.moringaschool.cocktails.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.cocktails.Constants;
import com.moringaschool.cocktails.R;
import com.moringaschool.cocktails.models.Drink;
import com.moringaschool.cocktails.models.DrinksResponse;
import com.moringaschool.cocktails.ui.CocktailDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseCocktailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseCocktailViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bidCocktail(Drink cocktail) {
        ImageView cocktailImageView = (ImageView) mView.findViewById(R.id.cocktailImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.cocktailNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView tailTextView = (TextView) mView.findViewById(R.id.tailTextView);

        Picasso.get().load(cocktail.getStrDrinkThumb()).into(cocktailImageView);

        nameTextView.setText(cocktail.getStrDrink());
        categoryTextView.setText(cocktail.getStrCategory());
        tailTextView.setText(cocktail.getStrAlcoholic());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Drink> cocktails = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_COCKTAILS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cocktails.add(snapshot.getValue(Drink.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, CocktailDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("cocktails", Parcels.wrap(cocktails));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

