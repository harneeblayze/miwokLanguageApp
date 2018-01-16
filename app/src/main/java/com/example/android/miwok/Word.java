package com.example.android.miwok;

/**
 * Created by HARNY on 9/27/2017.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mAudioResourceId;
    private int mResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;


    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;

    }
    public Word(String defaultTranslation,String miwokTranslation, int resourceId, int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mResourceId = resourceId;
        mAudioResourceId = audioResourceId;
        
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmResourceId(){return mResourceId;}

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }
    //best way to debug is to use the toString method by doing the keyboard shortcut is ALT + Insert for windows users


    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mAudioResourceId=" + mAudioResourceId +
                ", mResourceId=" + mResourceId +
                '}';
    }
    //the above will print the string value to logs incase of any errors.

    public boolean hasImage(){
        return mResourceId != NO_IMAGE_PROVIDED;

    }

}
