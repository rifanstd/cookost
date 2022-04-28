package com.example.cookost;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;

public class FragmentSetting extends Fragment {
    TableLayout tableLayout1, tableLayout2;
    ImageView arrowback;
  
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tableLayout1 = view.findViewById(R.id.tableLayout1);
        tableLayout2 = view.findViewById(R.id.tableLayout2);
        arrowback = view.findViewById(R.id.backFromSetting);

        tableLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Account.class);
                getActivity().startActivity(intent);
            }
        });

        tableLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Login.class);
                getActivity().startActivity(intent);
            }
        });

        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Home.class);
                getActivity().startActivity(intent);
            }
        });
    }
}