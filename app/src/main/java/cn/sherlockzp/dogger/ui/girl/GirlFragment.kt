package cn.sherlockzp.dogger.ui.girl

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.sherlockzp.dogger.R
import cn.sherlockzp.dogger.databinding.FragmentMainBinding
import cn.sherlockzp.dogger.di.Injectable
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class GirlFragment : Fragment() , Injectable{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val girlViewModel: GirlViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[GirlViewModel::class.java]
    }

    lateinit var dataBinding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dataBinding.name = girlViewModel.name
    }
}
