package thrymr.apps.bankersapp;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by thrymr on 24/9/15.
 */
public class ExpertVideoFragment extends android.support.v4.app.Fragment {

    private View view;
    private VideoView myVideoView;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;
    private int position = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.expert_video, container, false);
        /*myVideoView = (VideoView) view.findViewById(R.id.video_view);
        if (mediaControls == null) {
            mediaControls = new MediaController(getActivity());
        }
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        myVideoView.setMediaController(mediaControls);

        myVideoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.kitkat));
        myVideoView.requestFocus();
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
                myVideoView.seekTo(position);
                if (position == 0) {
                    myVideoView.start();
                } else {
                    myVideoView.pause();
                }
            }
        });*/
        getInit();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();
    }

    public void getInit() {
        myVideoView = (VideoView) view.findViewById(R.id.video_view);
        mediaControls = new MediaController(getActivity());
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        myVideoView.setMinimumWidth(width);
        myVideoView.setMinimumHeight(height);
        myVideoView.setMediaController(mediaControls);
        myVideoView.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.kitkat));
        myVideoView.start();
    }


}
