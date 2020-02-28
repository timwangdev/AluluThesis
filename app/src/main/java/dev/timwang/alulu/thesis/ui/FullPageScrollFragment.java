package dev.timwang.alulu.thesis.ui;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ortiz.touchview.TouchImageView;

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
        TouchImageView imageView = view.findViewById(R.id.fullpage_image);
        imageView.setImageDrawable(getDrawable());

    }

    private Drawable getDrawable() {
        Activity activity = requireActivity();
        int resId;
        switch (pageNum) {
            case 1:
            default:
                resId = R.drawable.fullpage_1;
                break;
            case 2:
                resId = R.drawable.fullpage_2;
                break;
            case 3:
                resId = R.drawable.fullpage_3;
                break;
        }
        return activity.getResources().getDrawable(resId, activity.getTheme());
    }
}
