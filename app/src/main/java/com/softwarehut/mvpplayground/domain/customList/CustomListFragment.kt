package com.softwarehut.mvpplayground.domain.customList

import android.os.Bundle
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.softwarehut.mvp.domain.parametrized.ParamsFragment
import com.softwarehut.mvpplayground.R
import kotlinx.android.synthetic.main.fragment_custom_list.*
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.newInstance

class CustomListFragment : ParamsFragment<CustomListPresenter>(), CustomListView {

    private val kodein by closestKodein()

    private lateinit var adapter: ListAdapter<CustomListItem, ViewHolder>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ItemsAdapter()
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list.adapter = adapter

        floatingActionButton.setOnClickListener {
            presenter.onAddClick()
        }
        val initialModel: CustomListModel = params.getParcelable(INITIAL_MODEL_KEY)
        presenter.requestItems(initialModel)
    }

    override fun setListItems(items: List<CustomListItem>) {
        adapter.submitList(items)
    }

    override fun getViewResource(): Int {
        return R.layout.fragment_custom_list
    }

    override fun createPresenter(): CustomListPresenter {
        val bindingContainer = kodein
        val newInstance = bindingContainer.newInstance { CustomListPresenterImpl(this@CustomListFragment, instance()) }
        val presenter by newInstance
        return presenter
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {

        const val INITIAL_MODEL_KEY: String = "INITIAL_MODEL_KEY"

        fun create(initialModel: CustomListModel): CustomListFragment {
            val customListFragment = CustomListFragment()
            val bundle = Bundle()
            bundle.putParcelable(INITIAL_MODEL_KEY, initialModel)
            putParams(customListFragment, bundle)
            return customListFragment
        }
    }
}