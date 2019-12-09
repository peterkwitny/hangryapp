/*

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

    private ArrayList<Meal> foodItem = new ArrayList<Meal>(); //Creating a new arraylist of meals for the adapter to display
    private Context mContext;

    private StorageReference mStorageRef; //storage to link to firebase, need to import mstorage

    RecyclerViewAdapter(ArrayList<Meal> foodItem, Context mContext){ //RecyclerViewAdapter constructor, what creates the recyclerViewAdapter
        this.foodItem = foodItem;
        this.mContext = mContext;

        mStorageRef = FirebaseStorage.getInstance().getReference(); //get the storage reference from firebase
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //The code that determines how each view is created
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //Set the text of the viewholder from the previous function, onCreateViewHolder, to the name of the
        //specific contact by accessing the specific position for that contact. //the textView is from the listitem_item xml

        holder.textViewFoodItem.setText(foodItem.get(position).name);
        holder.textViewRestaurant.setText(foodItem.get(position).restaurant);
        holder.textViewPrice.setText(foodItem.get(position).price);


        final File localFile = foodItem.get(position).foodPic; //getting the foodpic file from Meal
        //with respect to the specific contact you want. Since position is passed into this function
        // to access Food Item for the position, do foodItem.get(position)

        StorageReference foodRef = mStorageRef.child(foodItem.get(position).foodName); //reference for foodName

        foodRef.getFile(localFile)  //accessing the file from firebase storage
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...
                        try{ //see if this works without an exception

                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.fromFile(localFile)); //turn it into an image
                            holder.imageViewFood.setImageBitmap(bitmap); //Setting the imageview in user_item.xml to the picture obtained

                        }

                        catch(IOException e){ //Catching some exception, making it so it doesn't just crash your app



                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
                Toast.makeText(mContext, exception.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }




    @Override
    public int getItemCount() {
        return foodItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewFood;
        TextView textViewFoodItem, textViewRestaurant, textViewPrice;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFood = itemView.findViewById(R.id.imageViewFood);
            textViewFoodItem = itemView.findViewById(R.id.textViewFoodItem);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewRestaurant = itemView.findViewById(R.id.textViewRestaurant);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}

 */
