package com.axfex.moneybalance.ui.category.edit

import android.os.Bundle
import android.util.Log
import android.view.*
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import kotlinx.android.synthetic.main.fragment_edit_category.*
import javax.inject.Inject







class EditCategoryFragment : AppFragment() {

    @Inject
    lateinit var viewModel: EditCategoryViewModel

    @Inject
    lateinit var iconAdapter: EditCategoryIconAdapter

    @Inject
    lateinit var colorAdapter: EditCategoryColorAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iconAdapter.submitList(viewModel.iconList())
        val selectionView = resources.getDrawable(R.drawable.fragment_edit_right_arrow, null)
        editCategoryIconRecycler.initialize(iconAdapter,selectionView=selectionView)
//        editCategoryIconRecycler.adapter=iconAdapter

//        editCategoryColorRecycler.setHasFixedSize(true)
//        val layoutManager=CarouselLayoutManager(CarouselLayoutManager.VERTICAL,true)
//        layoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())
//        editCategoryColorRecycler.layoutManager= layoutManager
//        editCategoryColorRecycler.addOnScrollListener(CenterScrollListener())


        val leftArrow = resources.getDrawable(R.drawable.fragment_edit_left_arrow, null)
        val rightArrow = resources.getDrawable(R.drawable.fragment_edit_right_arrow, null)
        editCategoryColorRecycler.initialize(colorAdapter,startArrow= leftArrow,endArrow = rightArrow)
            colorAdapter.submitList(viewModel.colorList())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_category, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val current = editCategoryColorRecycler.getCurrentPosition()
//        Log.i("EditCategoryFragment", "onContextItemSelected: $current")

        return super.onOptionsItemSelected(item)
    }
}
