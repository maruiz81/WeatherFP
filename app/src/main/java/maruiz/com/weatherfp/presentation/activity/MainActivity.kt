package maruiz.com.weatherfp.presentation.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import arrow.data.fix
import kotlinx.android.synthetic.main.activity_main.*
import maruiz.com.weatherfp.R
import maruiz.com.weatherfp.presentation.di.WeatherContext
import maruiz.com.weatherfp.presentation.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), MainActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            MainActivityPresenter.getWeather("London").run(WeatherContext.GetWeatherContext())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
