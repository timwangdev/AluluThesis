package dev.timwang.alulu.thesis;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import dev.timwang.alulu.thesis.ui.HomeFragment;
import dev.timwang.alulu.thesis.ui.P10Fragment;
import dev.timwang.alulu.thesis.ui.P11Fragment;
import dev.timwang.alulu.thesis.ui.P12Fragment;
import dev.timwang.alulu.thesis.ui.P13Fragment;
import dev.timwang.alulu.thesis.ui.P14Fragment;
import dev.timwang.alulu.thesis.ui.P15Fragment;
import dev.timwang.alulu.thesis.ui.P16Fragment;
import dev.timwang.alulu.thesis.ui.P17Fragment;
import dev.timwang.alulu.thesis.ui.P18Fragment;
import dev.timwang.alulu.thesis.ui.P19Fragment;
import dev.timwang.alulu.thesis.ui.P1Fragment;
import dev.timwang.alulu.thesis.ui.P20Fragment;
import dev.timwang.alulu.thesis.ui.P21Fragment;
import dev.timwang.alulu.thesis.ui.P22Fragment;
import dev.timwang.alulu.thesis.ui.P23Fragment;
import dev.timwang.alulu.thesis.ui.P24Fragment;
import dev.timwang.alulu.thesis.ui.P25Fragment;
import dev.timwang.alulu.thesis.ui.P26Fragment;
import dev.timwang.alulu.thesis.ui.P27Fragment;
import dev.timwang.alulu.thesis.ui.P28Fragment;
import dev.timwang.alulu.thesis.ui.P29Fragment;
import dev.timwang.alulu.thesis.ui.P2Fragment;
import dev.timwang.alulu.thesis.ui.P30Fragment;
import dev.timwang.alulu.thesis.ui.P31Fragment;
import dev.timwang.alulu.thesis.ui.P32Fragment;
import dev.timwang.alulu.thesis.ui.P33Fragment;
import dev.timwang.alulu.thesis.ui.P34Fragment;
import dev.timwang.alulu.thesis.ui.P35Fragment;
import dev.timwang.alulu.thesis.ui.P36Fragment;
import dev.timwang.alulu.thesis.ui.P37Fragment;
import dev.timwang.alulu.thesis.ui.P38Fragment;
import dev.timwang.alulu.thesis.ui.P39Fragment;
import dev.timwang.alulu.thesis.ui.P3Fragment;
import dev.timwang.alulu.thesis.ui.P40Fragment;
import dev.timwang.alulu.thesis.ui.P41Fragment;
import dev.timwang.alulu.thesis.ui.P42Fragment;
import dev.timwang.alulu.thesis.ui.P43Fragment;
import dev.timwang.alulu.thesis.ui.P44Fragment;
import dev.timwang.alulu.thesis.ui.P45Fragment;
import dev.timwang.alulu.thesis.ui.P4Fragment;
import dev.timwang.alulu.thesis.ui.P5Fragment;
import dev.timwang.alulu.thesis.ui.P6Fragment;
import dev.timwang.alulu.thesis.ui.P7Fragment;
import dev.timwang.alulu.thesis.ui.P8Fragment;
import dev.timwang.alulu.thesis.ui.P9Fragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Log.w("view-pager", String.valueOf(i));
        switch (i) {
            case 1:
                return new P1Fragment();
            case 2:
                return new P2Fragment();
            case 3:
                return new P3Fragment();
            case 4:
                return new P4Fragment();
            case 5:
                return new P5Fragment();
            case 6:
                return new P6Fragment();
            case 7:
                return new P7Fragment();
            case 8:
                return new P8Fragment();
            case 9:
                return new P9Fragment();
            case 10:
                return new P10Fragment();
            case 11:
                return new P11Fragment();
            case 12:
                return new P12Fragment();
            case 13:
                return new P13Fragment();
            case 14:
                return new P14Fragment();
            case 15:
                return new P15Fragment();
            case 16:
                return new P16Fragment();
            case 17:
                return new P17Fragment();
            case 18:
                return new P18Fragment();
            case 19:
                return new P19Fragment();
            case 20:
                return new P20Fragment();
            case 21:
                return new P21Fragment();
            case 22:
                return new P22Fragment();
            case 23:
                return new P23Fragment();
            case 24:
                return new P24Fragment();
            case 25:
                return new P25Fragment();
            case 26:
                return new P26Fragment();
            case 27:
                return new P27Fragment();
            case 28:
                return new P28Fragment();
            case 29:
                return new P29Fragment();
            case 30:
                return new P30Fragment();
            case 31:
                return new P31Fragment();
            case 32:
                return new P32Fragment();
            case 33:
                return new P33Fragment();
            case 34:
                return new P34Fragment();
            case 35:
                return new P35Fragment();
            case 36:
                return new P36Fragment();
            case 37:
                return new P37Fragment();
            case 38:
                return new P38Fragment();
            case 39:
                return new P39Fragment();
            case 40:
                return new P40Fragment();
            case 41:
                return new P41Fragment();
            case 42:
                return new P42Fragment();
            case 43:
                return new P43Fragment();
            case 44:
                return new P44Fragment();
            case 45:
                return new P45Fragment();
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
