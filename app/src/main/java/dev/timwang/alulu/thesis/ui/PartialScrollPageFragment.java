package dev.timwang.alulu.thesis.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import dev.timwang.alulu.thesis.R;

public class PartialScrollPageFragment extends Fragment {

    private int pageNum;

    public PartialScrollPageFragment(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    private int getLayout() {
        switch (pageNum) {
            default:
            case 9:
                return R.layout.fragment_partial_scroll_9;
            case 14:
                return R.layout.fragment_partial_scroll_14;
            case 16:
                return R.layout.fragment_partial_scroll_16;
            case 31:
                return R.layout.fragment_partial_scroll_31;
            case 33:
                return R.layout.fragment_partial_scroll_33;
            case 45:
                return R.layout.fragment_partial_scroll_45;
        }
    }

}
