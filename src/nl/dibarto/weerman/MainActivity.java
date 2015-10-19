package nl.dibarto.weerman;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity
{
	ViewPager _pager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		
		_pager = (ViewPager) findViewById(R.id.viewpager);
		
		_pager.setAdapter(new MyPageAdapter(getSupportFragmentManager(), getFragments()));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		this.finish();
		
		startActivity(new Intent(this, MainActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case R.id.action_settings:
			onPages();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void onPages()
	{
		startActivityForResult(new Intent(this, PagesActivity.class), 1);
	}
	
	private List<Fragment> getFragments()
	{
		List<Fragment> fragments = new ArrayList<Fragment>();
		
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
		double lat = 0.0;
		double lon = 0.0;

		if (location != null)
		{
			lat = location.getLatitude();
			lon = location.getLongitude();
		}

		fragments.add(MyFragment.newInstance("http://www.dibarto.nl/weerman/weerman.php?lat=" + lat + "&lon=" + lon, false));
		
		String[] pages = MyGlobals.getPages(this);
		
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
}
