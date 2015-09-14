package simplelife.com.br.gym2beer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends Activity {

    SharedPreferences sharedPref;
    long dayAtGym = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this.getBaseContext();

        sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        dayAtGym = sharedPref.getInt(getString(R.string.day_at_gym), 0);

        boolean aBeerTaken = sharedPref.getBoolean(getString(R.string.is_beer_taken), false);

        if(aBeerTaken){
            CheckBox checkBoxBeer = (CheckBox) findViewById(R.id.checkBox_monthly);
            checkBoxBeer.setChecked(true);
        }

        for(int i=1; i <= dayAtGym; i++) {
            String buttonID = "checkBox_day" + i;
            int resID = getResources().getIdentifier(buttonID, "id", "simplelife.com.br.gym2beer");
            CheckBox checkBox = ((CheckBox) findViewById(resID));
            checkBox.setChecked(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void monthlyClicked(View view){
        boolean aBeerTaken = sharedPref.getBoolean(getString(R.string.is_beer_taken), false);

        if(aBeerTaken){
            Toast.makeText(this, "Voce nao pode desmarcar, espere a proxima rodada", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(getString(R.string.is_beer_taken), true);
            editor.commit();
        }
    }


    public void dayClicked(View view) {
        // Is the view now checked?
        //tested
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            SharedPreferences.Editor editor = sharedPref.edit();
            dayAtGym++;
            editor.putInt(getString(R.string.day_at_gym), (int) dayAtGym);
            editor.commit();
        }

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox_day1:
                if (checked){
                    Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();

                }
                else Toast.makeText(this, "no no no", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox_day2:
                if (checked) Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
                else Toast.makeText(this, "no no no", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
