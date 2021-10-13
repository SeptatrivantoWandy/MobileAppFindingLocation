package com.example.mcslecproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.MyViewHolder> {
    private Context ctx;
    Vector<BookmarkData> bookmarks;

    BookmarkData object_bmData;
    String bmId;

    int userId;
    int attractionId;


    public BookmarkAdapter(Context ctx, int userId) {
        this.ctx = ctx;
        this.userId = userId;
    }

    public void setBookmarks(Vector<BookmarkData> bookmarks) {
        this.bookmarks = bookmarks;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.bookmark_form_component, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        final BookmarkData bm = bookmarks.get(position);

        bmId = bookmarks.get(position).getBookmarkId();

        attractionId = Integer.parseInt(bookmarks.get(position).getBookmarkAttractionId());
        String attractionName = HomeForm.locations.get(attractionId).getAttractionName();
        int attractionImage = HomeForm.locations.get(attractionId).getAttractionImage();

        holder.bmAttractionNameTV.setText(attractionName);
        holder.attractionImage.setImageResource(attractionImage);

        holder.userId = userId;
        holder.posCardV = position;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, BookmarkDetailForm.class);

                intent.putExtra("id", bookmarks.get(position).getBookmarkId());
                intent.putExtra("wisataid", bookmarks.get(position).getBookmarkAttractionId());


                intent.putExtra("userid", userId);

                ctx.startActivity(intent);
                ((BookmarkForm)ctx).finish();

            }
        });


    }

    @Override
    public int getItemCount() {

        return bookmarks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bmAttractionNameTV, emptyDataTV;
        ImageView attractionImage;

        int userId;
        int posCardV;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bmAttractionNameTV = itemView.findViewById(R.id.bmAttractionNameTV);
            attractionImage = itemView.findViewById(R.id.bmAttractionImage);

        }
    }
}
