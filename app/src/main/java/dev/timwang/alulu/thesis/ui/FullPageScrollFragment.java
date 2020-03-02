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
        View layout = view.findViewById(R.id.fullpage);
        layout.setBackgroundResource(getTitleDrawable());
        imageView.setImage(ImageSource.resource(getDrawable()));

    }

    private int getDrawable() {
        int resId;
        switch (pageNum) {
            default:
            case 38:
                resId = R.drawable.fullpage_38;
                break;
            case 39:
                resId = R.drawable.fullpage_39;
                break;
            case 40:
                resId = R.drawable.fullpage_40;
                break;
        }
        return resId;
    }

    private int getTitleDrawable() {
        int resId;
        switch (pageNum) {
            default:
            case 38:
                resId = R.drawable.page_38;
                break;
            case 39:
                resId = R.drawable.page_39;
                break;
            case 40:
                resId = R.drawable.page_40;
                break;
        }
        return resId;
    }
}
