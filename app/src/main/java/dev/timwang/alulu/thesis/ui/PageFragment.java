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

public class PageFragment extends Fragment {

    private int pageNum;

    public PageFragment(int pageNum) {
        super();
        this.pageNum = pageNum;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SubsamplingScaleImageView imageView = view.findViewById(R.id.page_image);
        imageView.setImage(ImageSource.resource(getResources().getIdentifier(
                "@drawable/page_" + pageNum,
                null,
                getActivity().getPackageName()
        )));
    }
}
