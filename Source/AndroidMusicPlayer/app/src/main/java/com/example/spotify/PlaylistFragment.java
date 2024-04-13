package com.example.spotify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaylistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaylistFragment extends Fragment implements FragmentCallback {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    boolean isDarkMode = true;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView playlist;
    MusicAdapter playlistAdapter;
    ArrayList<MusicFiles> musicFiles = new ArrayList<>();


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PlaylistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlaylistFragment newInstance(String param1) {
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);

        LinearLayoutManager playlistLM = new LinearLayoutManager(this.getContext());

        playlistLM.setOrientation(LinearLayoutManager.VERTICAL);

        playlist = (RecyclerView) view.findViewById(R.id.yourLibraryList);
        playlistAdapter = new MusicAdapter(this.getContext(), musicFiles, musicFiles, isDarkMode);
        playlist.setLayoutManager(playlistLM);
        playlist.setAdapter(playlistAdapter);

        return view;
    }

    @Override
    public void onMessageFromMainToFrag(String sender, MusicFiles musicFiles) {
        if (sender.equals("MAIN"))
        {
            this.musicFiles.add(musicFiles);
            playlistAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onMusicFromMainToFrag(String sender, MusicFiles musicFiles) {

    }

    @Override
    public void onMessageFromMainToFrag(String sender, boolean isDarkMode) {

    }
}