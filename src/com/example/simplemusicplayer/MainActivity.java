package com.example.simplemusicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

	private Button mNextButton;
	private Button mBackButton;
	private Button mPlayPauseButton;

	private ImageView mImageView;
	private int mCurrentImageId;

	private int mCurrentSong;

	private MediaPlayer mPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mImageView = (ImageView) findViewById( R.id.imageView1 );

		mNextButton = (Button) findViewById( R.id.nextButton );
		mBackButton = (Button) findViewById( R.id.backButton );
		mPlayPauseButton = (Button) findViewById( R.id.playPauseButton );

		mCurrentImageId = R.drawable.beyonce3; 
		mCurrentSong = R.raw.party;

		//BACK BUTTON LISTENER
		mBackButton.setOnClickListener( new View.OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				//Stop song
//				mPlayPauseButton.setText( "Play" );
//				pauseSong();

				//Change image 
				if( mCurrentImageId == R.drawable.beyonce3 )
				{
					mImageView.setImageResource( R.drawable.beyonce2 );
					mCurrentImageId = R.drawable.beyonce2;
					mCurrentSong = R.raw.endoftime;
				}
				else if( mCurrentImageId == R.drawable.beyonce2 )
				{
					mImageView.setImageResource( R.drawable.beyonce );
					mCurrentImageId = R.drawable.beyonce;
					mCurrentSong = R.raw.singleladies;
				} 
				else if( mCurrentImageId == R.drawable.beyonce )
				{
					mImageView.setImageResource( R.drawable.beyonce3 );
					mCurrentImageId = R.drawable.beyonce3;
					mCurrentSong = R.raw.party;
				}

				//Go to previous song
				mPlayPauseButton.setText( "Pause" );
				changeSong();

			}
		} );

		//PLAY/PAUSE BUTTON LISTENER
		mPlayPauseButton.setOnClickListener( new View.OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				//For first time playing a song
				if( mPlayer == null )
				{
					mPlayPauseButton.setText( "Pause" );
					changeSong();
				}
				
				//For subsequent Play/Pause button presses
				else if( mPlayer != null)
				{
					if(  mPlayer.isPlaying() )
					{
						//if was Playing
						//change text to Play (b/c now it is paused) 
						mPlayPauseButton.setText( "Play" );

						//stop song 
						pauseSong();

					}

					else 
					{
						//if was Paused: 
						//change text to Pause (b/c now it is playing) 
						mPlayPauseButton.setText( "Pause" );

						//play song
						resumeSong();
					}
				}




			}
		} );

		//NEXT BUTTON LISTENER
		mNextButton.setOnClickListener( new View.OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				//Stop song 
//				mPlayPauseButton.setText( "Play" );
//				pauseSong();

				//Change image and current song
				if( mCurrentImageId == R.drawable.beyonce3 )
				{
					mImageView.setImageResource( R.drawable.beyonce );
					mCurrentImageId = R.drawable.beyonce;
					mCurrentSong = R.raw.singleladies;
				}
				else if( mCurrentImageId == R.drawable.beyonce )
				{
					mImageView.setImageResource( R.drawable.beyonce2 );
					mCurrentImageId = R.drawable.beyonce2;
					mCurrentSong = R.raw.endoftime;
				} 
				else if( mCurrentImageId == R.drawable.beyonce2 )
				{
					mImageView.setImageResource( R.drawable.beyonce3 );
					mCurrentImageId = R.drawable.beyonce3;
					mCurrentSong = R.raw.party;
				}

				//Play song
				mPlayPauseButton.setText( "Pause" );
				changeSong();


			}
		} );

	}

	private void resumeSong()
	{
		if( mPlayer != null && !mPlayer.isPlaying() )
		{
			//Resume where you were
			mPlayer.start();
		}

		//If you finish the song
		mPlayer.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) 
			{
				mp.release();
			}
		} );

	}

	private void pauseSong()
	{
		if( mPlayer != null && mPlayer.isPlaying() )
		{
			mPlayer.pause();
		}


	}

	private void changeSong()
	{
		if( mPlayer != null )
		{
			mPlayer.release();
		}
		
		mPlayer = MediaPlayer.create( this , mCurrentSong );
		mPlayer.start();

		mPlayer.setOnCompletionListener( new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) 
			{
				mp.release();
			}
		} );
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
