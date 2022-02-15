package com.moringaschool.cocktails.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.cocktails.Constants;
import com.moringaschool.cocktails.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

//    private ValueEventListener mSearchedCocktailsReferenceListener;
//    private DatabaseReference mSearchedCocktailsReference;

    @BindView(R.id.findCocktailsButton) Button mFindCocktailsButton;
//    @BindView(R.id.cocktailsEditText) EditText mCocktailsEditText;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
    @BindView(R.id.savedCocktailsButton) Button mSavedCocktailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        mSearchedCocktailsReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_SEARCHED_COCKTAIL);
//
//        mSearchedCocktailsReferenceListener  = mSearchedCocktailsReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
//                for (DataSnapshot cocktailsSnapshot : dataSnapshot.getChildren()) {
//                    String cocktails = cocktailsSnapshot.getValue().toString();
//                    Log.d("cocktails updated", "cocktails: " + cocktails); //log
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
//
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mFindCocktailsButton.setOnClickListener(this);
        mSavedCocktailsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFindCocktailsButton) {
//            String cocktails = mCocktailsEditText.getText().toString();
//
//            saveLocationToFirebase(cocktails);

//            if(!(cocktails).equals("")) {
//                addToSharedPreferences(cocktails);
//            }

            Intent intent = new Intent(AboutActivity.this, CocktailListActivity.class);
//            intent.putExtra("cocktails", cocktails);
            startActivity(intent);
//                    Toast.makeText(AboutActivity.this, name, Toast.LENGTH_LONG).show();
        }
        if (v == mSavedCocktailsButton) {
            Intent intent = new Intent(AboutActivity.this, SavedCocktailListActivity.class);
            startActivity(intent);
        }
    }
//    public void saveLocationToFirebase(String location) {
//        mSearchedCocktailsReference.setValue(location);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedCocktailsReference.removeEventListener(mSearchedCocktailsReferenceListener);
//    }
}