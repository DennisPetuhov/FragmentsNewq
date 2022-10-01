package com.example.fragmentsnew.ui

import android.content.SharedPreferences
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fragmentsnew.R
import com.example.fragmentsnew.databinding.ActivityMainBinding
import com.example.fragmentsnew.ui.fragments.Fragment3


const val TEX_VALUE ="VALUE"


class ActivityMain : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: SharedPreferences
//    lateinit var repo: RepositoryImpL //  почему не лэйзи?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        bottonNavigator()
        toolbarNavigator()
            //  repo= RepositoryImpL((applicationContext as App).getNoteDAO()) // репоизтиторий с функией
            /*  preferences= getSharedPreferences(app_preferences, MODE_PRIVATE)
       Fragment1().requireActivity().edit_text.setText(preferences.getString(TEX_VALUE,""))
        Fragment1().requireActivity().button_shmatton.setOnClickListener{
            val value = Fragment1().requireActivity().edit_text.toString()
            preferences.edit().putString(TEX_VALUE,value).apply()
        }



             */


    }



    private fun toolbarNavigator() {
        binding.myToolbar.setOnMenuItemClickListener {
            if (it.itemId ==R.id.toolbar3) {
                supportFragmentManager.beginTransaction().replace(R.id.my_container,Fragment3()).commit()
                true
            } else false
        }

    }




private fun bottonNavigator() {
    val controller=findNavController(R.id.my_container)
    binding.myBottomBar.setupWithNavController(controller)
   /* binding.myBottomBar.setOnItemSelectedListener {
        Log.d(TAG, "*****")
        //вызываем листенерр выбора ийтема в разметке
        when (it.itemId) {
            R.id.fragment1 -> {
                supportFragmentManager//вызвать сааапорт фрагмент мэнеджер
                    .beginTransaction() // начинаем транзакцию по изменению
                    .addToBackStack(null)
                    .replace(
                        R.id.my_container,
                        Fragment1()
                    )//вызываем метод add который доавляет в когнтейнер (R.id.container) нужный нам фрагмент
                    .commit()
                true


            }
            R.id.fragment2 -> { // жмеме на айтем в разметке
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.my_container, Fragment2())
                    .commit()

                true
            }
            R.id.fragment3 -> {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.my_container, Fragment3())
                    .commit()
                true
            }
            R.id.fragment4 -> {
                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.my_container, Fragment4())
                    .commit()
                true
            }

            else -> {
                false
            }
        }

    }
    binding.myBottomBar.selectedItemId =
        R.id.fragment2 //когда листенер не активен

    */

}
}