package dairo.aguas.acromine.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dairo.aguas.acromine.databinding.FragmentDetailBinding
import dairo.aguas.acromine.ui.adapter.DetailAdapter
import dairo.aguas.acromine.ui.model.SearchViewData
import dairo.aguas.acromine.ui.viewmodel.DetailViewModel

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private val detailAdapter by lazy { DetailAdapter() }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val searchViewData: SearchViewData
        get() = DetailFragmentArgs.fromBundle(requireArguments()).searchViewData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchViewData()
        configureDataBinding()
        setupAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setSearchViewData() {
        viewModel.searchViewData = searchViewData
    }

    private fun configureDataBinding() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setupAdapter() {
        binding.rvDefinition.apply {
            adapter = detailAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        detailAdapter.submitList(searchViewData.variations)
    }
}