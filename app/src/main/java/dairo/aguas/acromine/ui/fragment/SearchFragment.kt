package dairo.aguas.acromine.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dairo.aguas.acromine.databinding.FragmentSearchBinding
import dairo.aguas.acromine.ui.adapter.DefinitionAdapter
import dairo.aguas.acromine.ui.base.BaseFragment
import dairo.aguas.acromine.ui.state.SearchState
import dairo.aguas.acromine.ui.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by viewModels()
    private val definitionAdapter by lazy { DefinitionAdapter() }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupListeners()
        subscribeToSearchState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAdapter() {
        binding.rvDefinition.apply {
            adapter = definitionAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupListeners() {
        binding.etAbbreviation.setOnEditorActionListener { editText, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE && editText.text.toString().isNotEmpty()) {
                viewModel.getAbbreviationDefinitions(editText.text.toString())
            }
            return@setOnEditorActionListener true
        }
    }

    private fun subscribeToSearchState() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.state.collect(::handleSearchState)
        }
    }

    private fun handleSearchState(searchState: SearchState) {
        hideLoading()
        when (searchState) {
            is SearchState.Loading -> {
                showLoading()
            }
            is SearchState.Error -> {
                showMessageSnackBar(getString(searchState.resource))
            }
            is SearchState.Success -> {
                definitionAdapter.submitList(searchState.data)
            }
        }
    }
}