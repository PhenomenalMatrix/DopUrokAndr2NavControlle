package com.example.dopurokandr2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.App;
import com.example.dopurokandr2.databinding.FragmentRoomBinding;
import com.example.model.User;

import java.util.List;

public class RoomFragment extends Fragment {

    private FragmentRoomBinding binding;
    private RoomAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = FragmentRoomBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        roomInit();
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(requireActivity(),
                        R.id.fragment);
                navController.navigate(R.id.formFragment2);
            }
        });
    }

    private void roomInit() {
        App.getDataBase().userDao().getAll().observe(getViewLifecycleOwner(),
                new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> list) {
                initRecycler(list);
            }
        });
    }

    private void initRecycler(List<User> list) {
        adapter = new RoomAdapter();
        adapter.addItems(list);
        binding.roomRv.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.action_bar_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort_btn:
                sortRoom();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sortRoom() {
        App.getDataBase().userDao().sortByAsc().observe(getViewLifecycleOwner(),
                new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> list) {
                        adapter.notifyDataSetChanged();
                        initRecycler(list);
                    }
                });
    }
}