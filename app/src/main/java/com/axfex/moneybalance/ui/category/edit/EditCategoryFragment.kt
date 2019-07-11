package com.axfex.moneybalance.ui.category.edit

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import com.axfex.moneybalance.domain.category.Category
import com.axfex.moneybalance.ui.category.CategoryTypesEnum
import com.axfex.moneybalance.utils.hideKeyboard
import com.axfex.moneybalance.utils.showKeyboard
import com.axfex.moneybalance.utils.subscribe
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import kotlinx.android.synthetic.main.fragment_edit_category.*
import javax.inject.Inject


class EditCategoryFragment : AppFragment() {

    @Inject
    lateinit var viewModel: EditCategoryViewModel

    @Inject
    lateinit var iconAdapter: EditCategoryIconAdapter

    @Inject
    lateinit var colorAdapter: EditCategoryColorAdapter


    private val args: EditCategoryFragmentArgs by navArgs()


    private var categoryId: String? = null
    private var categoryType: CategoryTypesEnum? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_edit_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoryId = args.categoryId
        categoryType = args.categoryType
        viewModel.messageEvent.subscribe(this, this::showMessage)

        editCategoryIconRecycler.onRestoreInstanceState(savedInstanceState)
        editCategoryIconRecycler.adapter=iconAdapter


        editCategoryColorRecycler.adapter=colorAdapter


        if (categoryId!=null && savedInstanceState==null)
            viewModel.expenseCategory(categoryId!!).subscribe(this, this::render)
        else render(null)

    }

    private fun showMessage(message: EditCategoryViewModel.Message) {
        when (message) {
            is EditCategoryViewModel.Message.EmptyName -> {
                val snackbar= Snackbar.make(editCategoryRoot, R.string.editCategoryEmptyName, LENGTH_SHORT)
                snackbar.addCallback(object: Snackbar.Callback(){
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        categoryName.requestFocus()
                        categoryName.showKeyboard()
                        super.onDismissed(transientBottomBar, event)
                    }
                })
                snackbar.show()

            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        editCategoryIconRecycler.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_category, menu)
        menu.findItem(R.id.delete).isVisible = categoryId != null
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.done -> {
                view?.hideKeyboard()
                val name = categoryName.text.toString()
                val colorIndex = editCategoryColorRecycler.getCurrentPosition()
                val iconIndex = editCategoryIconRecycler.getCurrentPosition()
                val saved = viewModel.saveCategory(name, iconIndex, colorIndex,categoryType,categoryId)
                if (saved) findNavController().navigateUp()
            }
            R.id.delete -> {
                view?.hideKeyboard()
                showRemoveDialog()
            }

        }


        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        view?.hideKeyboard()
        super.onDestroyView()
    }

    private fun showRemoveDialog() {
        val dialogTitle = "Delete incomeCategory?"
        AlertDialog.Builder(requireContext())
            .setTitle(dialogTitle)
            .setPositiveButton(R.string.bt_ok) { d, i ->
                run {
                    viewModel.deleteCategory(categoryId!!,categoryType)
                    findNavController().navigateUp()
                }
            }
            .setNegativeButton(R.string.bt_cancel, { d, i -> })
            .create()
            .show()
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }

    private fun render(category: Category?) {
        Log.i("EditCategoryFragment", "render: $category")
        
        var iconPosition: Int? = null
        var colorPosition: Int? = null

        category?.let {
            iconPosition = viewModel.findIconPosition(it.icon)
            colorPosition = viewModel.findColorPosition(category.icon)
            categoryName.setText(it.name)
        }


        val selectionView = resources.getDrawable(R.drawable.edit_category_selection, null)
        editCategoryIconRecycler.initialize(
            position = iconPosition,
            selectionView = selectionView
        )
        iconAdapter.submitList(viewModel.iconList)

        editCategoryIconRecycler.setSelectionChangeCallback { selectedPosition ->
            categoryIconPreview.setImageDrawable(
                viewModel.getIconDrawable(viewModel.iconList[selectedPosition])
            )
        }

        val leftArrow = resources.getDrawable(R.drawable.edit_category_left_arrow, null)
        val rightArrow = resources.getDrawable(R.drawable.edit_category_right_arrow, null)
        editCategoryColorRecycler.initialize(
            position = colorPosition,
            startArrow = leftArrow,
            endArrow = rightArrow
        )
        colorAdapter.submitList(viewModel.colorList)
    }


}
