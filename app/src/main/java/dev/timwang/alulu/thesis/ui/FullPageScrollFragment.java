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

public class FullPageScrollFragment extends Fragment {

    private int pageNum;

    public FullPageScrollFragment(int pageNum) {
        super();
        this.pageNum = pageNum;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fullpage_scroll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SubsamplingScaleImageView imageView = view.findViewById(R.id.fullpage_image);
        imageView.setImage(ImageSource.resource(getDrawable()));

    }

    private int getDrawable() {
        int resId;
        switch (pageNum) {
            default:
            case 5:
                resId = R.drawable.page_5;
                break;
            case 38:
                resId = R.drawable.page_38;
                break;
            case 52:
                resId = R.drawable.bi_1;
                break;
            case 53:
                resId = R.drawable.bi_2;
                break;
            case 54:
                resId = R.drawable.bi_3;
                break;
        }
        return resId;
    }
}
