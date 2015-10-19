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
    public static MyFragment newInstance(String url, boolean picture)
    {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString("URL", url);
        args.putBoolean("PICTURE", picture);
        fragment.setArguments(args);
        return fragment;
    }

    public MyFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        WebView webView = (WebView) rootView.findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

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

        return rootView;
    }
}
