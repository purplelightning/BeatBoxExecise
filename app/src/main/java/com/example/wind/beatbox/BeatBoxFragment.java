package com.example.wind.beatbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.List;

/**
 * Created by wind on 17-6-13.
 */

public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;
    private Sound mSound;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mBeatBox=new BeatBox(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_beat_box_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        recyclerView.setAdapter(new SoundAdapter());
        recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBeatBox.release();
    }

    private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnTouchListener{
        private Button mButton;

        public SoundHolder(LayoutInflater inflater,ViewGroup container){

            super(inflater.inflate(R.layout.list_item_sound,container,false));
            mButton=(Button)itemView.findViewById(R.id.list_item_sound_button);
            mButton.setOnClickListener(this);
            mButton.setOnTouchListener(this);
        }

        public void bindSound(Sound sound){
            mSound=sound;
            mButton.setText(mSound.getName());
        }

        @Override
        public void onClick(View v) {
            mBeatBox.play(mSound);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //按下操作
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    Animation animation1= AnimationUtils.loadAnimation(getContext(),R.anim.scale_to_big);
                    v.startAnimation(animation1);
                    break;
                case MotionEvent.ACTION_UP:
                    Animation animation2=AnimationUtils.loadAnimation(getContext(),R.anim.scale_to_small);
                    v.startAnimation(animation2);
                    break;
                case MotionEvent.ACTION_MOVE:
                    v.clearAnimation();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    v.clearAnimation();
                    break;

                default:
                    break;
            }
            return false;
        }
    }



    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        private List<Sound> mSounds;
        public SoundAdapter(List<Sound> sounds){
            mSounds=sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            return new SoundHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder soundHolder, int position) {
            Sound sound=mSounds.get(position);
            soundHolder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

}












