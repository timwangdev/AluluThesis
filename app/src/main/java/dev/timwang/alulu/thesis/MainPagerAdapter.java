package dev.timwang.alulu.thesis;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import dev.timwang.alulu.thesis.ui.ExampleFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        Log.w("view-pager", String.valueOf(i));
        switch (i) {
//            case 6:
//            case 7:
//            case 8:
//                return new FullPageScrollFragment(i);
            default:
                return new ExampleFragment(i);
        }
    }

    @Override
    public int getCount() {
        return 46;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "PAGE " + (position + 1) + "/45";
    }

}
