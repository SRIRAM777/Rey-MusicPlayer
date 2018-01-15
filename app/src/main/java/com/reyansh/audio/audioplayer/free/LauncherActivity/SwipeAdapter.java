package com.reyansh.audio.audioplayer.free.LauncherActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.reyansh.audio.audioplayer.free.Album.AlbumFragment;
import com.reyansh.audio.audioplayer.free.Artist.FragmentArtist;
import com.reyansh.audio.audioplayer.free.Common;
import com.reyansh.audio.audioplayer.free.FileDirectory.FolderFragment;
import com.reyansh.audio.audioplayer.free.Genres.FragmentGenres;
import com.reyansh.audio.audioplayer.free.PlayList.PlaylistFragment;
import com.reyansh.audio.audioplayer.R;
import com.reyansh.audio.audioplayer.free.Songs.SongsFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SwipeAdapter extends FragmentPagerAdapter {
    private Map<Integer, String> mFragmentTags;
    private FragmentManager mFragmentManager;

    private String mPageTile[];

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public SwipeAdapter(FragmentManager fm) {
        super(fm);
        mPageTile = Common.getInstance().getResources().getStringArray(R.array.fragments_titles);
        mFragmentManager = fm;
        mFragmentTags = new HashMap<>();

        fragments.add(new AlbumFragment());
        fragments.add(new FragmentArtist());
        fragments.add(new SongsFragment());
        fragments.add(new FragmentGenres());
        fragments.add(new PlaylistFragment());
        fragments.add(new FolderFragment());

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag == null)
            return null;
        return mFragmentManager.findFragmentByTag(tag);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTile[position];
    }

    @Override
    public int getCount() {
        return 6;
    }
}