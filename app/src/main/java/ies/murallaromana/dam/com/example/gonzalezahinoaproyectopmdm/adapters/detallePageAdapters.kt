package ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.youtube.player.YouTubeBaseActivity
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.fragments.Detalle_Fragment
import ies.murallaromana.dam.com.example.gonzalezahinoaproyectopmdm.activities.fragments.Sinopsis_Fragment

class detallePageAdapters(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val mFragmentList = ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }
}