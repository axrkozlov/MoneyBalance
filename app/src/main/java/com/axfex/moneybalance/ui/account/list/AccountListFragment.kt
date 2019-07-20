package com.axfex.moneybalance.ui.account.list

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import com.axfex.moneybalance.utils.subscribe
import kotlinx.android.synthetic.main.fragment_account_list.*
import javax.inject.Inject

class AccountListFragment : AppFragment() {

    @Inject
    lateinit var viewModel: AccountListViewModel

    @Inject
    lateinit var adapter: AccountListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountListRecycler.adapter=adapter
        viewModel.accountList().subscribe(this) { list ->
            adapter.submitList(list)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.account_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                val direction =
                    AccountListFragmentDirections.actionAccountListFragmentToEditAccountFragment()
                findNavController().navigate(direction)
//                findNavController().navigate(R.id.action_categoryListFragment_to_editCategoryFragment)

            }
        }
        return super.onOptionsItemSelected(item)
    }

}
