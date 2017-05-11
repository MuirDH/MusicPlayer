package com.example.android.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button play;
    private Button pause;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        releaseMediaPlayer();

        mediaPlayer = MediaPlayer.create(this, R.raw.the_other_guys_well_sung_ep_04_desperado);

        play = (Button) findViewById(R.id.play);
        play.setText(R.string.play_button);

        pause = (Button) findViewById(R.id.pause);
        pause.setText(R.string.pause_button);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOnPlay();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performOnPause();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                performOnEnd();
            }
        });

    }

    private void performOnPause() {

        Toast.makeText(getApplicationContext(),
                "Pausing song", Toast.LENGTH_SHORT).show();
        mediaPlayer.pause();
        pause.setEnabled(false);
        play.setEnabled(true);
    }

    private void performOnPlay() {

        Toast.makeText(getApplicationContext(),
                "Playing song", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
        pause.setEnabled(true);
        play.setEnabled(false);
    }

    private void performOnEnd() {

        Toast.makeText(getApplicationContext(),
                "Song finished", Toast.LENGTH_SHORT).show();
        pause.setEnabled(false);
        play.setEnabled(true);
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
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

            Log.v("MainActivity", "MediaPlayer has been released");
        }
    }


}
