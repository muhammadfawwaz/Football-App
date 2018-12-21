package com.example.fif.kade3.View

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import com.example.fif.kade3.R
import com.example.fif.kade3.R.id.*
import com.example.fif.kade3.invisible
import com.example.fif.kade3.visible

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityManager

class MainActivity : AppCompatActivity() {
    private var menuItem: Menu? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener {
                item->when(item.itemId) {
                    matches_menu -> {
                        if(menuItem != null) {
                            menuItem!!.findItem(R.id.search_menu).actionView.visible()
                        }
                        supportFragmentManager.beginTransaction().replace(R.id.main_container,FragmentMatch.newInstance()).commit()
                    }

                    teams_menu -> {
                        if(menuItem != null) {
                            menuItem!!.findItem(R.id.search_menu).actionView.visible()
                        }
                        supportFragmentManager.beginTransaction().replace(R.id.main_container,FragmentListTeam.newInstance()).commit()
                    }

                    favorites_menu -> {
                        menuItem!!.findItem(R.id.search_menu).actionView.invisible()
                        supportFragmentManager.beginTransaction().replace(R.id.main_container,FragmentFavorite.newInstance()).commit()
                    }
                }
            true
        }

        bottom_navigation.selectedItemId = matches_menu
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        menuItem = menu
        var searchItem: MenuItem = menu!!.findItem(R.id.search_menu)
        var searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var bundle = Bundle()
                bundle.putString("newText",newText)
                if(bottom_navigation.selectedItemId == R.id.matches_menu) {
                    var fragmentLastMatch = FragmentLastMatch.newInstance()
                    fragmentLastMatch.arguments = bundle
                    Log.i("fragmentarguments",fragmentLastMatch.arguments.toString())
                    supportFragmentManager.beginTransaction().replace(R.id.main_container,fragmentLastMatch).commit()
                }
                else {
                    var fragmentListTeam = FragmentListTeam.newInstance()
                    fragmentListTeam.arguments = bundle
                    supportFragmentManager.beginTransaction().replace(R.id.main_container,fragmentListTeam).commit()
                }
                return false
            }
        })
        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                if(bottom_navigation.selectedItemId == R.id.matches_menu){
                    supportFragmentManager.beginTransaction().replace(R.id.main_container,FragmentMatch.newInstance()).commit()
                }
                else {
                    supportFragmentManager.beginTransaction().replace(R.id.main_container,FragmentListTeam.newInstance()).commit()
                }
                return false
            }

        })
        return true
    }
}
