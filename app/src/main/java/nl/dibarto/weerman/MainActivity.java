package nl.dibarto.weerman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ConnectionCallbacks, OnConnectionFailedListener
{
    private GoogleApiClient _googleApiClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) != ConnectionResult.SUCCESS)
        {
            return;
        }

        onRefresh();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        this.finish();

        startActivity(new Intent(this, MainActivity.class));

        //onRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                onSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onSettings()
    {
        startActivityForResult(new Intent(this, SettingsActivity.class), 1);
    }

    protected void onRefresh()
    {
        if (_googleApiClient == null)
        {
            _googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }

        if (_googleApiClient.isConnected())
        {
            onConnected(null);
        }
        else
        {
            if (!_googleApiClient.isConnecting())
            {
                _googleApiClient.connect();
            }
        }
    }

    private List<Fragment> getFragments()
    {
        List<Fragment> fragments = new ArrayList<Fragment>();

        Location location = LocationServices.FusedLocationApi.getLastLocation(_googleApiClient);

        if (location == null)
        {
            fragments.add(MyFragment.newInstance("http://www.dibarto.nl/weerman/weerman.php", false));
        }
        else
        {
            double lat = location.getLatitude();
            double lon = location.getLongitude();

            fragments.add(MyFragment.newInstance("http://www.dibarto.nl/weerman/weerman.php?lat=" + lat + "&lon=" + lon, false));
        }

        String[] pages = getPages(this);

        for (int i = 0; i < 10; i++)
        {
            if (pages[i].startsWith("1"))
            {
                String page = pages[i].substring(1);

                boolean image = page.contains("images");

                fragments.add(MyFragment.newInstance(page, image));
            }
        }

        return (fragments);
    }

//    static String[] getPages(Context context)
//    {
//        SharedPreferences preferences = context.getSharedPreferences("WeerMan", Context.MODE_PRIVATE);
//
//        String pages = "";
//
//        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/short_term_vandaag_dag.png\n";
//        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/short_term_morgen_nacht.png\n";
//        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/short_term_morgen_dag.png\n";
//
//        pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=temperatuur&tijd=0\n";
//        pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=wind&tijd=0\n";
//        pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=zicht&tijd=0\n";
//
//        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Tmax.png\n";
//        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Tmin.png\n";
//        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Prec.png\n";
//        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Wind.png\n";
//
//        pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=maxtemperatuur&tijd=0\n";
//        pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=mintemperatuur&tijd=0\n";
//        pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=temperatuurgrond&tijd=0\n";
//
//        pages += "1http://wolken.buienradar.nl/image2.ashx?region=nl&ir=False\n";
//
//        pages = preferences.getString("pages", pages);
//
//        return (pages.split("\n"));
//    }

    static String[] getPages(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences("WeerMan", Context.MODE_PRIVATE);

        String pages = "";

        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/short_term_vandaag_dag.png\n";
        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/short_term_morgen_nacht.png\n";
        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/short_term_morgen_dag.png\n";

        pages += "1http://www.knmi.nl/actueel/images/tempgmt.png\n";
        pages += "1http://www.knmi.nl/actueel/images/windbftgmt.png\n";
        pages += "1http://www.knmi.nl/actueel/images/zichtgmt.png\n";

        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Tmax.png\n";
        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Tmin.png\n";
        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Prec.png\n";
        pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Wind.png\n";

        pages = preferences.getString("pages3", pages);

        return (pages.split("\n"));
    }

    static void setPages(Context context, String pages)
    {
        SharedPreferences preferences = context.getSharedPreferences("WeerMan", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = preferences.edit();

        edit.putString("pages3", pages);

        edit.commit();
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), getFragments());

        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(adapter.getCount());
    }

    @Override
    public void onConnectionSuspended(int i)
    {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {

    }
}
