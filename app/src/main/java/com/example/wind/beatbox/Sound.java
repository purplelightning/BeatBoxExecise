package com.example.wind.beatbox;

/**
 * Created by wind on 17-6-13.
 */

public class Sound {
    private String mAssertPath;
    private String mName;
    private Integer mSoundId;

    public Sound(String assertPath){
        mAssertPath=assertPath;
        String [] components=assertPath.split("/");
        String filename=components[components.length-1];
        mName=filename.replace(".wav","");
    }

    public String getmAssertPath() {
        return mAssertPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundId(){
        return mSoundId;
    }

    public void setSoundId(Integer soundId){
        mSoundId=soundId;
    }

}
