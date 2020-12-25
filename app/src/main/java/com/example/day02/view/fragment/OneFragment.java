package com.example.day02.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.day02.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class OneFragment extends Fragment {

    private Banner ban;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_a, null);
        initView(inflate);
        return inflate;

    }

    private void initView(View inflate) {
        ban = inflate.findViewById(R.id.ban);
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.aa);
        integers.add(R.drawable.bb);
        integers.add(R.drawable.cc);
        integers.add(R.drawable.dd);
        integers.add(R.drawable.ee);
        ban.setImages(integers)
                .setBannerAnimation(Transformer.ForegroundToBackground)
                .setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getContext()).load(path).into(imageView);
            }
        }).start();
    }
}
