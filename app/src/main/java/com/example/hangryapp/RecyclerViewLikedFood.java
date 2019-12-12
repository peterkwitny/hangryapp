package com.example.hangryapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecyclerViewLikedFood extends RecyclerView.Adapter<RecyclerViewLikedFood.ViewHolder> {
    private ArrayList<Meal> savedMeals = new ArrayList<Meal>(); //Creating a new arraylist of meals for the adapter to display
    private Context mContext;
    private StorageReference mStorageRef; //storage to link to firebase, need to import mstorage

    RecyclerViewLikedFood(ArrayList<Meal> savedMeals, Context mContext){ //RecyclerViewAdapter constructor, what creates the recyclerViewAdapter
        this.savedMeals = savedMeals;
        this.mContext = mContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.likedfood_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("User");

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String findEmail = user.getEmail();

        myRef.orderByChild("email").equalTo(findEmail).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String foundKey = dataSnapshot.getKey();

                myRef.child(foundKey).child("savedmeals").orderByKey().addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        holder.textViewSavedMeal.setText(savedMeals.get(position).name);
                        holder.textViewSavedRestaurant.setText(savedMeals.get(position).restaurant);
                        holder.textViewSavedPrice.setText(savedMeals.get(position).price);

                        StorageReference picRef = mStorageRef.child(savedMeals.get(position).picReference); //reference for foodName
                        final File localFile;
                        try {
                            localFile = File.createTempFile("image", "jpg");
                            picRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                    try {
                                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.fromFile(localFile));
                                        holder.imageViewSavedFood.setImageBitmap(bitmap);
                                    }
                                    catch(IOException e){

                                    }
                                }
                            });

                        }
                        catch(IOException e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return savedMeals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewSavedFood;
        TextView textViewSavedMeal, textViewSavedRestaurant, textViewSavedPrice;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewSavedFood = itemView.findViewById(R.id.imageViewSavedFood);
            textViewSavedMeal = itemView.findViewById(R.id.textViewSavedMeal);
            textViewSavedRestaurant = itemView.findViewById(R.id.textViewSavedRestaurant);
            textViewSavedPrice = itemView.findViewById(R.id.textViewSavedPrice);

            parentLayout = itemView.findViewById(R.id.parent_layout_likedfood);
        }
    }
}