package com.elshafee.androiden.breakingbadapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.elshafee.androiden.R
import com.elshafee.androiden.breakingbadapp.BreakingBadApplication
import com.elshafee.androiden.breakingbadapp.ui.utils.CharacterListAdapter

@Suppress("DEPRECATION")
class CharacterListFragment : Fragment() {

    private val characterListViewModel: CharacterListViewModel by viewModels {
        CharacterListViewModelFactory((requireActivity().application as BreakingBadApplication).characterRepositry)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_character_list_fragment, container, false)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val rvCharacterList = requireActivity().findViewById<RecyclerView>(R.id.rvCharacterList)
        val adapter = CharacterListAdapter { bbCharacter ->
            //TODO
            if (bbCharacter.img != null) {
                findNavController().navigate(
                    CharacterListFragmentDirections.showCharacterImageFragment(
                        bbCharacter.img
                    )
                )
            }
        }
        rvCharacterList.adapter = adapter
        characterListViewModel.characterList.observe(viewLifecycleOwner, { breakingbadCharacter ->
            adapter.submitList(breakingbadCharacter)
        })
        val refreshLayout = requireActivity().findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refreshLayout.setOnRefreshListener {
            characterListViewModel.refreshDataFromRepository() // Refresh Data
            Toast.makeText(requireContext(), " Data Updated ", Toast.LENGTH_LONG).show()
            refreshLayout.isRefreshing = false
        }
    }
}