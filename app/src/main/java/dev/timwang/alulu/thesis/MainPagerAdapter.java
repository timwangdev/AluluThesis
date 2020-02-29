package dev.timwang.alulu.thesis;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import dev.timwang.alulu.thesis.ui.ExampleFragment;
import dev.timwang.alulu.thesis.ui.FullPageScrollFragment;
import dev.timwang.alulu.thesis.ui.InfoFragment;
import dev.timwang.alulu.thesis.ui.PageFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        Log.w("view-pager", String.valueOf(i));
        if (i < 6 || i == 9) {
            return new ExampleFragment(i);
        }
        if (i < 9) {
            return new FullPageScrollFragment(i);
        }
        if (i == 20) {
            return new InfoFragment();
        }
        return new PageFragment(i);
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
