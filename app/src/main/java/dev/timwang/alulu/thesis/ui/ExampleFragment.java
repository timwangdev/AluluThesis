package dev.timwang.alulu.thesis.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.timwang.alulu.thesis.R;


public class ExampleFragment extends Fragment {

    private int pageNum;

    public ExampleFragment(int pageNum) {
        super();
        this.pageNum = pageNum;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_example, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.example_text);
        switch (pageNum) {
            default:
                textView.setText("Page " + pageNum);
                break;
            case 0:
                textView.setText("Title");
                break;
            case 1:
                textView.setText("Instruction");
                break;
            case 2:
                textView.setText("Abstract");
                break;
            case 3:
                textView.setText("Acknowledgement");
                break;
            case 4:
                textView.setText("Content");
                break;
            case 5:
                textView.setText("Chapter One");
                break;
            case 9:
                textView.setText("Chapter Two");
                break;
        }
    }

}
