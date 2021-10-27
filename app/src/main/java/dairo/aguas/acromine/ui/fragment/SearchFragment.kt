package dairo.aguas.acromine.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dairo.aguas.acromine.R
import dairo.aguas.acromine.ui.viewmodel.SearchViewModel

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getAbbreviationDefinitions("SMART")
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}