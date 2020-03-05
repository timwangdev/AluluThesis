package dev.timwang.alulu.thesis;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.Arrays;

import dev.timwang.alulu.thesis.ui.ContentFragment;
import dev.timwang.alulu.thesis.ui.FullPageScrollFragment;
import dev.timwang.alulu.thesis.ui.PageFragment;
import dev.timwang.alulu.thesis.ui.PartialScrollPageFragment;
import dev.timwang.alulu.thesis.ui.TitlePageScrollFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        Log.i("MainPagerAdapter", String.valueOf(i));
        if (i == 4) return new ContentFragment();
        if (Arrays.asList(5, 38, 52, 53, 54).contains(i)) {
            return new FullPageScrollFragment(i);
        }
        if (Arrays.asList(9, 14, 16, 31, 33, 45).contains(i)) {
            return new PartialScrollPageFragment(i);
        }
        if (Arrays.asList(39, 40, 41).contains(i)) {
            return new TitlePageScrollFragment(i);
        }
        return new PageFragment(i);
    }

    @Override
    public int getCount() {
        return 55;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "PAGE " + (position + 1) + "/" + getCount();
    }

}
