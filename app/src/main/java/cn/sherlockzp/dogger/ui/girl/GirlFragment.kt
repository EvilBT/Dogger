package cn.sherlockzp.dogger.ui.girl

import android.arch.lifecycle.Observer
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
import cn.sherlockzp.dogger.toast
import cn.sherlockzp.dogger.util.AutoClearedValue
import cn.sherlockzp.vo.Status
import xyz.zpayh.adapter.BaseMultiAdapter
import xyz.zpayh.adapter.BaseViewHolder
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class GirlFragment : Fragment() , Injectable{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: GirlViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[GirlViewModel::class.java]
    }

    private val adapter = object : BaseMultiAdapter(){
        /**
         * 开启子view的点击事件，或者其他监听
         * @param holder 默认的ViewHolder
         * @param layoutRes 对应的布局Layout ID，也代表为ViewType
         */
        override fun bind(holder: BaseViewHolder?, layoutRes: Int) {

        }
    }

    lateinit var binding: AutoClearedValue<FragmentMainBinding>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentMainBinding>(inflater, R.layout.fragment_main, container, false)
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getGirls().observe(this, Observer {
            it?.let {
                when(it.status) {
                    Status.LOADING -> binding.get()?.srRefresh?.isRefreshing = true
                    Status.ERROR -> {
                        toast(it.message.toString())
                        binding.get()?.srRefresh?.isRefreshing = false
                    }
                    Status.SUCCESS -> {
                        adapter.data = it.data
                        binding.get()?.srRefresh?.isRefreshing = false
                    }
                }
            }
        })
        binding.get()?.rvGirlList?.adapter = adapter
        binding.get()?.srRefresh?.setOnRefreshListener(::refresh)

        refresh()
    }

    private fun refresh() {
        viewModel.setPage(1)
    }
}
