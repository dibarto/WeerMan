package nl.dibarto.weerman;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

public class SettingsActivity extends Activity
{
    CheckBox[] _checkBox = new CheckBox[10];
    EditText[] _editText = new EditText[10];

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_settings);

        _checkBox[0] = (CheckBox) findViewById(R.id.CheckBox00);
        _checkBox[1] = (CheckBox) findViewById(R.id.CheckBox01);
        _checkBox[2] = (CheckBox) findViewById(R.id.CheckBox02);
        _checkBox[3] = (CheckBox) findViewById(R.id.CheckBox03);
        _checkBox[4] = (CheckBox) findViewById(R.id.CheckBox04);
        _checkBox[5] = (CheckBox) findViewById(R.id.CheckBox05);
        _checkBox[6] = (CheckBox) findViewById(R.id.CheckBox06);
        _checkBox[7] = (CheckBox) findViewById(R.id.CheckBox07);
        _checkBox[8] = (CheckBox) findViewById(R.id.CheckBox08);
        _checkBox[9] = (CheckBox) findViewById(R.id.CheckBox09);

        _editText[0] = (EditText) findViewById(R.id.EditText00);
        _editText[1] = (EditText) findViewById(R.id.EditText01);
        _editText[2] = (EditText) findViewById(R.id.EditText02);
        _editText[3] = (EditText) findViewById(R.id.EditText03);
        _editText[4] = (EditText) findViewById(R.id.EditText04);
        _editText[5] = (EditText) findViewById(R.id.EditText05);
        _editText[6] = (EditText) findViewById(R.id.EditText06);
        _editText[7] = (EditText) findViewById(R.id.EditText07);
        _editText[8] = (EditText) findViewById(R.id.EditText08);
        _editText[9] = (EditText) findViewById(R.id.EditText09);

        String[] pages = MainActivity.getPages(this);

        for (int i = 0; i < 10; i++)
        {
            _checkBox[i].setChecked(pages[i].startsWith("1"));

            _editText[i].setText(pages[i].substring(1));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            NavUtils.navigateUpFromSameTask(this);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause()
    {
        String pages = "";

        for (int i = 0; i < 10; i++)
        {
            if (_checkBox[i].isChecked())
            {
                pages += "1";
            }
            else
            {
                pages += "0";
            }

            pages += _editText[i].getText() + "\n";
        }

        MainActivity.setPages(this, pages);

        super.onPause();
    }
}
