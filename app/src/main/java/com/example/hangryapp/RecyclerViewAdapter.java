

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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Meal> foodItem = new ArrayList<Meal>(); //Creating a new arraylist of meals for the adapter to display
    private Context mContext;
    private StorageReference mStorageRef; //storage to link to firebase, need to import mstorage

    RecyclerViewAdapter(ArrayList<Meal> foodItem, Context mContext){ //RecyclerViewAdapter constructor, what creates the recyclerViewAdapter
        this.foodItem = foodItem;
        this.mContext = mContext;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //The code that determines how each view is created
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Meal");
        final DatabaseReference myRef2 = database.getReference("User");


        holder.textViewFoodItem.setText(foodItem.get(position).name);
        holder.textViewRestaurant.setText(foodItem.get(position).restaurant);
        holder.textViewPrice.setText(foodItem.get(position).price);
        holder.buttonSaveFood.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(view == holder.buttonSaveFood){
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String findEmail = user.getEmail();

                    myRef2.orderByChild("email").equalTo(findEmail).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            String editKey = dataSnapshot.getKey();
                            myRef2.child(editKey).child("savedmeals").push().setValue( foodItem.get(position));

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

            }
        });

        StorageReference picRef = mStorageRef.child(foodItem.get(position).picReference); //reference for foodName
        final File localFile;
        try {
            localFile = File.createTempFile("image", "jpg");
            picRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.fromFile(localFile));
                        holder.imageViewFood.setImageBitmap(bitmap);
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
    public int getItemCount() {
        return foodItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView imageViewFood;
        TextView textViewFoodItem, textViewRestaurant, textViewPrice;
        Button buttonSaveFood;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFood = itemView.findViewById(R.id.imageViewSavedFood);
            textViewFoodItem = itemView.findViewById(R.id.textViewSavedMeal);
            textViewPrice = itemView.findViewById(R.id.textViewSavedPrice);
            textViewRestaurant = itemView.findViewById(R.id.textViewSavedRestaurant);

            buttonSaveFood = itemView.findViewById(R.id.buttonSaveFood);

            parentLayout = itemView.findViewById(R.id.parent_layout_likedfood);


        }


    }
}


