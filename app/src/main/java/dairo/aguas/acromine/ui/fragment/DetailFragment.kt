package dairo.aguas.acromine.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dairo.aguas.acromine.R
import dairo.aguas.acromine.databinding.FragmentDetailBinding
import dairo.aguas.acromine.ui.model.SearchViewData
import dairo.aguas.acromine.ui.viewmodel.DetailViewModel

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()

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
}