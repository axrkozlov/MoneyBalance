package com.axfex.moneybalance.ui.category.dialog

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppChoiceDialogFragment
import com.axfex.moneybalance.domain.model.category.CategoryType
import com.axfex.moneybalance.ui.category.edit.EditCategoryFragmentArgs
import com.axfex.moneybalance.ui.main.MainViewModel
import com.axfex.moneybalance.utils.addKeyboardEventListener
import com.axfex.moneybalance.utils.subscribe
import kotlinx.android.synthetic.main.fragment_category_dialog.*
import javax.inject.Inject

class CategoryDialogFragment : AppChoiceDialogFragment() {

    @Inject
    lateinit var viewModel: CategoryDialogViewModel


    @Inject
    lateinit var adapter: CategoryDialogAdapter

    private val animationHandler = Handler()
    var animator: ValueAnimator? = null

    private val args: CategoryDialogFragmentArgs by navArgs()
    private val categoryType: CategoryType by lazy { args.categoryType }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observe()
    }

    private fun observe() {
        viewModel.categoryList().subscribe(this){ list ->
            val categoryList=list.filter{
                it.type== categoryType
            }
            adapter.submitList(categoryList)

        }
    }

    private fun setupUI() {
        settingsCategory.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.action_to_categoryListFragment)
        }

        searchCategory.setOnFocusChangeListener { _, _ ->
            val lp1 = LinearLayout.LayoutParams(categoryDialogRecycler.layoutParams)
            lp1.height = 0
            categoryDialogRecycler.layoutParams = lp1
        }

        categoryDialogRecycler.adapter = adapter
        adapter.dismissCallback= { dismiss() }
        addKeyboardEventListener { isOpen ->
            changeDialogAppearance(isOpen)
        }

    }


    private fun changeDialogAppearance(isKeyboardOpen:Boolean){
        val endHeight: Int
        if (isKeyboardOpen) {
            endHeight = 200
            categoryDialogRecycler.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        } else {
            endHeight = 750
            searchCategory.clearFocus()
            categoryDialogRecycler.layoutManager=GridLayoutManager(context,4)
        }

        animationHandler.postDelayed({
            val lp = LinearLayout.LayoutParams(categoryDialogRecycler.layoutParams)
            animator?.cancel()
            animator = ValueAnimator.ofInt(lp.height, endHeight)
            animator?.let {
                it.addUpdateListener { animation ->
                    lp.height = animation.animatedValue as Int
                    categoryDialogRecycler.layoutParams = lp

                }
                it.duration = 150
                it.startDelay = 50L
                it.start()
            }

        }, 50)
    }

    override fun onPause() {
        super.onPause()
        animator?.cancel()
        animationHandler.removeCallbacksAndMessages(null)
    }
}
