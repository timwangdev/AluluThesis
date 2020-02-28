package dev.timwang.alulu.thesis;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import dev.timwang.alulu.thesis.ui.FullPageScrollFragment;
import dev.timwang.alulu.thesis.ui.HomeFragment;
import dev.timwang.alulu.thesis.ui.PageFragment;
import dev.timwang.alulu.thesis.ui.PartialScrollTwoFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        Log.w("view-pager", String.valueOf(i));
        switch (i) {
            case 1:
            case 2:
            case 3:
                return new FullPageScrollFragment(i);
            case 4:
                return new PageFragment(i);
            case 5:
                return new PartialScrollTwoFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 46;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "PAGE " + position + "/45";
    }

}
