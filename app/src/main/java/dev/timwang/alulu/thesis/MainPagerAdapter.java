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
import dev.timwang.alulu.thesis.ui.PartialScrollPage14Fragment;
import dev.timwang.alulu.thesis.ui.PartialScrollPage16Fragment;
import dev.timwang.alulu.thesis.ui.PartialScrollPage31Fragment;
import dev.timwang.alulu.thesis.ui.PartialScrollPage33Fragment;
import dev.timwang.alulu.thesis.ui.PartialScrollPage9Fragment;
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
        if (Arrays.asList(5, 38, 53, 54, 55).contains(i)) {
            return new FullPageScrollFragment(i);
        }
        if (i == 9) return new PartialScrollPage9Fragment();
        if (i == 14) return new PartialScrollPage14Fragment();
        if (i == 16) return new PartialScrollPage16Fragment();
        if (i == 31) return new PartialScrollPage31Fragment();
        if (i == 33) return new PartialScrollPage33Fragment();
        if (Arrays.asList(39, 40, 41).contains(i)) {
            return new TitlePageScrollFragment(i);
        }
        return new PageFragment(i);
    }

    @Override
    public int getCount() {
        return 56;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "PAGE " + (position + 1) + "/49";
    }

}
