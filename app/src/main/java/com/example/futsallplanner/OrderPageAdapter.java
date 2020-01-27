package com.example.futsallplanner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OrderPageAdapter extends FragmentStateAdapter {

    public OrderPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new MatchFragment();
            default:
                return new RankingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
