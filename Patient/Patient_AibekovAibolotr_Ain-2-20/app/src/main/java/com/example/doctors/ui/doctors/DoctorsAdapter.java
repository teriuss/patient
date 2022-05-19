package com.example.doctors.ui.doctors;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctors.R;
import com.example.doctors.databinding.ItemLayoutBinding;
import com.example.doctors.models.Person;

import java.util.ArrayList;
import java.util.List;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.ViewHolder> {
    private List<Person> doctors = new ArrayList<>();

    public void setList(List<Person> doctors){
        this.doctors = doctors;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding binding = ItemLayoutBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Person person = doctors.get(position);
        holder.binding.name.setText(person.getName());
        holder.binding.room.setText("Кабинет: " + person.getRoom());
        holder.binding.position.setText("Должность: " + person.getPosition());
        holder.binding.image.setImageDrawable(ContextCompat.getDrawable(holder.binding.getRoot().getContext(), person.getImage()));
        holder.binding.dropdownMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(holder.binding.getRoot().getContext(), holder.binding.dropdownMenu);
                popup.getMenuInflater().inflate(R.menu.card_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getTitle().toString()){
                            case "Позвонить в регистратуру":
                                if(ContextCompat.checkSelfPermission(
                                        holder.binding.getRoot().getContext(),android.Manifest.permission.CALL_PHONE) !=
                                        PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions((Activity) holder.binding.getRoot().getContext(), new
                                            String[]{android.Manifest.permission.CALL_PHONE}, 0);
                                } else {
                                    holder.binding.getRoot().getContext().startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "123456789")));
                                }
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLayoutBinding binding;

        public ViewHolder(@NonNull ItemLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

    }
}
