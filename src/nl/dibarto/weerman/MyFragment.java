package nl.dibarto.weerman;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyFragment extends Fragment
{
	public static final MyFragment newInstance(String url, boolean picture)
	{
		MyFragment fragment = new MyFragment();

		Bundle bundle = new Bundle(1);

		bundle.putString("URL", url);
		bundle.putBoolean("PICTURE", picture);

		fragment.setArguments(bundle);

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.myfragment_layout, container, false);

		WebView webView = (WebView) view.findViewById(R.id.webView1);
		
		String url = getArguments().getString("URL");
		boolean picture = getArguments().getBoolean("PICTURE");

		if (picture)
		{
			String html = "";

			html += "<HTML>";
			html += "<meta name='viewport' content='width=device-width; initial-scale=1.0;' />";
			html += "<BODY style='background-color:black;'>";
			html += "<IMG width=100% src='" + url + "'></IMG>";
			html += "</BODY>";
			html += "</HTML>";

			webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
		}
		else
		{
			webView.setWebViewClient(new WebViewClient());
			
			webView.loadUrl(url);
		}

		return view;
	}
}
