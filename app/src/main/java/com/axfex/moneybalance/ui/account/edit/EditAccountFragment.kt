package com.axfex.moneybalance.ui.account.edit

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
import com.axfex.moneybalance.utils.hideKeyboard
import com.axfex.moneybalance.utils.showKeyboard
import com.axfex.moneybalance.utils.subscribe

import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
import kotlinx.android.synthetic.main.fragment_edit_account.*
import java.math.BigDecimal
import javax.inject.Inject


class EditAccountFragment : AppFragment() {

    @Inject
    lateinit var viewModel: EditAccountViewModel

    @Inject
    lateinit var iconAdapter: EditAccountIconAdapter

    @Inject
    lateinit var colorAdapter: EditAccountColorAdapter


    private val args: EditAccountFragmentArgs by navArgs()


    private val accountId: String? by lazy { args.accountId }
//    private val categoryType: CategoryType by lazy { args.categoryType }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_edit_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        iconAdapter.onRestoreInstanceState(savedInstanceState)

        setupUI()

        viewModel.messageEvent.subscribe(this, this::showMessage)
        if (accountId != null && savedInstanceState == null)
            viewModel.account(accountId!!).subscribe(this) {
                accountName.setText(it.name)
                iconAdapter.selectByName(it.iconName)
                colorAdapter.selectByColor(it.color)
            }


        viewModel.iconList.subscribe(this) {
            iconAdapter.submitList(it)

        }
        colorAdapter.submitList(viewModel.colorList)
    }


    fun setupUI() {
        editAccountIconRecycler.adapter = iconAdapter
        editAccountColorRecycler.adapter = colorAdapter
        val selectionView = resources.getDrawable(R.drawable.icon_recyclerview_selection, null)
        editAccountIconRecycler.initialize(
            selectionView = selectionView
        )

        iconAdapter.setSelectionChangeCallback { name ->
            colorAdapter.setIcon(
                viewModel.getIconDrawable(name)

            )
        }

        val leftArrow = resources.getDrawable(R.drawable.color_recyclerview_left_arrow, null)
        val rightArrow = resources.getDrawable(R.drawable.color_recyclerview_right_arrow, null)
        editAccountColorRecycler.initialize(
            startArrow = leftArrow,
            endArrow = rightArrow
        )
    }

    private fun showMessage(message: EditAccountViewModel.Message) {
        when (message) {
            is EditAccountViewModel.Message.EmptyName -> {
                val snackbar = Snackbar.make(editAccountRoot, R.string.editCategoryEmptyName, LENGTH_SHORT)
                snackbar.addCallback(object : Snackbar.Callback() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        accountName.requestFocus()
                        accountName.showKeyboard()
                        super.onDismissed(transientBottomBar, event)
                    }
                })
                snackbar.show()

            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        iconAdapter.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_account, menu)
        menu.findItem(R.id.delete).isVisible = accountId != null
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.done -> {
                view?.hideKeyboard()
                val name = accountName.text.toString()
                val iconName = iconAdapter.selectedIconName
                val color = colorAdapter.selectedColor()
                val amount=BigDecimal(100)
                val saved = viewModel.saveAccount(name, iconName, color,amount,accountId)
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
                    viewModel.deleteAccount(accountId!!)

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


}
