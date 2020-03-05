package dev.timwang.alulu.thesis.ui;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import dev.timwang.alulu.thesis.R;

public class ContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Activity content = getActivity();
        if (content == null) return;
        final ViewPager vp = content.findViewById(R.id.view_pager);
        view.findViewById(R.id.s1).setOnClickListener(new OnContentClick(vp, 5));
        view.findViewById(R.id.s2).setOnClickListener(new OnContentClick(vp, 6));
        view.findViewById(R.id.s3).setOnClickListener(new OnContentClick(vp, 8));
        view.findViewById(R.id.s4).setOnClickListener(new OnContentClick(vp, 8));
        view.findViewById(R.id.s5).setOnClickListener(new OnContentClick(vp, 11));
        view.findViewById(R.id.s6).setOnClickListener(new OnContentClick(vp, 15));
        view.findViewById(R.id.s7).setOnClickListener(new OnContentClick(vp, 15));
        view.findViewById(R.id.s8).setOnClickListener(new OnContentClick(vp, 18));
        view.findViewById(R.id.s9).setOnClickListener(new OnContentClick(vp, 20));
        view.findViewById(R.id.s10).setOnClickListener(new OnContentClick(vp, 20));
        view.findViewById(R.id.s11).setOnClickListener(new OnContentClick(vp, 22));
        view.findViewById(R.id.s12).setOnClickListener(new OnContentClick(vp, 22));
        view.findViewById(R.id.s13).setOnClickListener(new OnContentClick(vp, 23));
        view.findViewById(R.id.s14).setOnClickListener(new OnContentClick(vp, 23));
        view.findViewById(R.id.s15).setOnClickListener(new OnContentClick(vp, 26));
        view.findViewById(R.id.s16).setOnClickListener(new OnContentClick(vp, 30));
        view.findViewById(R.id.s17).setOnClickListener(new OnContentClick(vp, 32));
        view.findViewById(R.id.s18).setOnClickListener(new OnContentClick(vp, 32));
        view.findViewById(R.id.s19).setOnClickListener(new OnContentClick(vp, 34));
        view.findViewById(R.id.s20).setOnClickListener(new OnContentClick(vp, 34));
        view.findViewById(R.id.s21).setOnClickListener(new OnContentClick(vp, 44));
        view.findViewById(R.id.s22).setOnClickListener(new OnContentClick(vp, 47));
        view.findViewById(R.id.s23).setOnClickListener(new OnContentClick(vp, 49));
    }

    private class OnContentClick implements View.OnClickListener {

        ViewPager viewPager;
        int index;

        OnContentClick(ViewPager viewPager, int index) {
            this.viewPager = viewPager;
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }
}
