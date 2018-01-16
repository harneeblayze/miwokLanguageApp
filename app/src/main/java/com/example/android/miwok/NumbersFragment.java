package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    //create an object of audiomanager below, we need this for the audiofocuschangelistener so the audiomanager would know what to do for each state of the focuschange of the audio file so we made it global so we can call it anywhwere else in the remaining part of the code AudioManager.OnAudioFocusChangeListener is the variable type. see it as Scanner x = new Scanner()
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT|| focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                //the method below helps to return our playback to 0secs when the user gains audiofocus on the app again so as to hear the word completely from the top
                mediaPlayer.seekTo(0);
                //pause music

            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
                //the start method also acts as resume.
                //resume audio
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();

            }

        }
    };
    //an object of MediaPlayer OnCompletionListener so as to release mediaplayer resources when audio has finished playing
    MediaPlayer.OnCompletionListener moncompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };



    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list,container,false);
        //just like find view by id we are trying to call the system's audio service attention to our audio and save the call in audioManager variable
        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);




        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("one","lutti",R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko",R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three","tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four","oyyisa", R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six","temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven","kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight","kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine","wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten","na'aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(getActivity(),R.color.category_numbers,words);
        ListView listview = (ListView)rootView.findViewById(R.id.list);
        listview.setAdapter(adapter);





        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                releaseMediaPlayer();

                //before we create our mediaplayer object, we need audiofocus to control/handle interruption/other app sounds
                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                   //above audioManager calls the systems attention and we requestAudioFocus then we pass the parameters which include the global variable of mOnAudiofocuschangelistener now if the state of focuschange is gained then we have access to play from the above global code
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //before we start playing any file the system has to grant us permission even if we have the audiofocus box
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // We have audio focus now.


                    mediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getmAudioResourceId());
                    mediaPlayer.start();


                    mediaPlayer.setOnCompletionListener(moncompletionListener);



                }
            }
        });


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {

        // If the media player is not null, then it may be currently playing a sound.

        if (mediaPlayer != null) {

            // Regardless of the current state of the media player, release its resources

            // because we no longer need it.

            mediaPlayer.release();



            // Set the media player back to null. For our code, we've decided that

            // setting the media player to null is an easy way to tell that the media player

            // is not configured to play an audio file at the moment.

            mediaPlayer = null;
            //we shall abandon audio focus here since the release method is situated in the strategic places of the app to relase all resources, we can quickly also abandon audio focus here
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }





    }


}
