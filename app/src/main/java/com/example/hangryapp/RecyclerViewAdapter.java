package com.example.hangryapp;


import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Meal> foodItem = new ArrayList<>();
    private Context mContext;

    private StorageReference mStorageRef;
    RecyclerViewAdapter(ArrayList<Meal> foodItem, Context mContext){
        this.foodItem = foodItem;
        this.mContext = mContext;

        mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.textViewFoodItem.setText(foodItem.get(position).name);
        holder.textViewRestaurant.setText(foodItem.get(position).restaurant);
        holder.textViewPrice.setText(foodItem.get(position).price);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, foodItem.get(position).restaurant, Toast.LENGTH_SHORT).show();
            }
        });

        try {
            final File localFile = File.createTempFile("images", "jpg");
            StorageReference mealPicRef = mStorageRef.child(foodItem.get(position).imageUri); //need to create String imageUri
            mealPicRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Successfully downloaded data to local file
                            // ...
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.fromFile(localFile));
                                holder.imageViewFood.setImageBitmap(bitmap);
                            }   catch (IOException exc){

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle failed download
                    // ...
                }
            });
        } catch (IOException exc){

        }



    }



    @Override
    public int getItemCount() {
        return foodItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewFood;
        TextView textViewFoodItem, textViewRestaurant, textViewPrice;
        Button buttonChooseFood;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFood = itemView.findViewById(R.id.imageViewFood);
            textViewFoodItem = itemView.findViewById(R.id.textViewFoodItem);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewRestaurant = itemView.findViewById(R.id.textViewRestaurant);
            buttonChooseFood = itemView.findViewById(R.id.buttonChooseFood);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
