package dev.timwang.alulu.thesis.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import dev.timwang.alulu.thesis.R;

public class TitlePageScrollFragment extends Fragment {

    private int pageNum;

    public TitlePageScrollFragment(int pageNum) {
        super();
        this.pageNum = pageNum;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_titlepage_scroll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SubsamplingScaleImageView imageView = view.findViewById(R.id.title_page_image);
        View layout = view.findViewById(R.id.title_page);
        layout.setBackgroundResource(getTitleDrawable());
        imageView.setImage(ImageSource.resource(getDrawable()));

    }

    private int getDrawable() {
        int resId;
        switch (pageNum) {
            default:
            case 39:
                resId = R.drawable.fullpage_39;
                break;
            case 40:
                resId = R.drawable.fullpage_40;
                break;
            case 41:
                resId = R.drawable.fullpage_41;
                break;
        }
        return resId;
    }

    private int getTitleDrawable() {
        int resId;
        switch (pageNum) {
            default:
            case 39:
                resId = R.drawable.page_39_bg;
                break;
            case 40:
                resId = R.drawable.page_40_bg;
                break;
            case 41:
                resId = R.drawable.page_41_bg;
                break;
        }
        return resId;
    }
}
