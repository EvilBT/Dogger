package cn.sherlockzp.dogger

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import cn.sherlockzp.dogger.repository.GankRepository
import cn.sherlockzp.dogger.ui.girl.GirlFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() , HasSupportFragmentInjector{

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject lateinit var gankRepository: GankRepository
    /** Returns an [AndroidInjector] of [Fragment]s.  */
    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            gankRepository.loadGank("福利",10,1)
                    .observe(this, Observer {
                        it?.let {
                            Log.d("Sherlock","${it.msg},${it.code}")
                            it.body?.let {
                                Log.d("Sherlock","${it.error},${it.results}")
                            }?:let {
                                Log.d("Sherlock","空的啊")
                            }
                        }?:let {
                            Toast.makeText(this,"为空",Toast.LENGTH_LONG).show()
                        }
                    })
        }

        if (savedInstanceState == null) {
            val fragment = GirlFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.container, fragment)
                    .commit()
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
