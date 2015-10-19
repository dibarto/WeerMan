package nl.dibarto.weerman;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MyGlobals
{
	static String[] getPages(Context context)
	{
		SharedPreferences preferences = context.getSharedPreferences("WeerMan", Context.MODE_PRIVATE);
		
		String pages = "";
		
		pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/short_term_vandaag_dag.png\n";
		pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/short_term_morgen_nacht.png\n";
		pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/short_term_morgen_dag.png\n";
		
		pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Tmax.png\n";
		pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Tmin.png\n";
		pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Prec.png\n";
		pages += "1http://www.knmi.nl/waarschuwingen_en_verwachtingen/images/Wind.png\n";
		
		pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=temperatuur&tijd=0\n";
		pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=wind&tijd=0\n";
		pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=zicht&tijd=0\n";
		
		pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=maxtemperatuur&tijd=0\n";
		pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=maxtemperatuur&tijd=0\n";
		pages += "1http://www10.buienradar.nl/images.aspx?jaar=-7&soort=temperatuurgrond&tijd=0\n";
		
		pages += "1http://wolken.buienradar.nl/image2.ashx?region=nl&ir=False\n";
		
		pages = preferences.getString("pages", pages);
		
		return (pages.split("\n"));
	}
	
	static void setPages(Context context, String pages)
	{
		SharedPreferences preferences = context.getSharedPreferences("WeerMan", Context.MODE_PRIVATE);

		Editor edit = preferences.edit();
		
		edit.putString("pages", pages);

		edit.commit();
	}
}
