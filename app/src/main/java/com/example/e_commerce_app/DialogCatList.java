package com.example.e_commerce_app;

import static com.example.e_commerce_app.MainActivity.CALL_ACTIVITY;
import static com.example.e_commerce_app.MainActivity.CAT_LIST;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class DialogCatList extends DialogFragment {
    public interface GetCategorie{
        public void getCategorie(String categorie);
    }
    GetCategorie getCategorie;
    ImageView ClearIcon;
    ListView CatList;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View view=getActivity().getLayoutInflater().inflate(R.layout.categorie_list,null);
        builder.setView(view);
        initView(view);
        ClearIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        Bundle bundle=getArguments();
          if(null!=bundle){
              ArrayList<String> categories=bundle.getStringArrayList(CAT_LIST);
              String CallingActivity= bundle.getString(CALL_ACTIVITY);
              ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),
                      android.R.layout.simple_list_item_1,
                      categories
                      );
              CatList.setAdapter(adapter);
              CatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                         switch (CallingActivity){
                             case "Main_Activity":
                                 Intent intent=new Intent(getActivity(),Search_Activity.class);
                                 intent.putExtra("Cat",categories.get(i));
                                 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                 getActivity().startActivity(intent);
                                 break;
                             case "Search_Activity":
                                 try {
                                     getCategorie=(GetCategorie) getActivity();
                                     getCategorie.getCategorie(categories.get(i));
                                 }catch (ClassCastException c){
                                     c.printStackTrace();
                                 }
                                 break;
                             default:
                                 break;
                         }
                  }
              });
        }

        return builder.create();
    }
    public void initView(View view){
        ClearIcon=view.findViewById(R.id.clearIcon);
        CatList=view.findViewById(R.id.CategorieList);
    }
}
