package nl.dibarto.weerman;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class MyAdapter extends FragmentPagerAdapter
{
    private List<Fragment> fragments;

    public MyAdapter(FragmentManager manager, List<Fragment> fragments)
    {
        super(manager);

        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return this.fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return this.fragments.size();
    }
}
