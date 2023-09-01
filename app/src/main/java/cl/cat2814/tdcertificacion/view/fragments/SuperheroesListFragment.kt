package cl.cat2814.tdcertificacion.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.cat2814.tdcertificacion.databinding.FragmentSuperheroesListBinding
import cl.cat2814.tdcertificacion.view.adapter.SuperheroAdapter
import cl.cat2814.tdcertificacion.viewModel.SuperheroViewModel

class SuperheroesListFragment : Fragment() {

    private lateinit var binding: FragmentSuperheroesListBinding
    private val superheroViewModel: SuperheroViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSuperheroesListBinding.inflate(layoutInflater, container, false)

        initViewModel()

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        val adapter = SuperheroAdapter()
        binding.rvSuperheroesList.adapter = adapter
        superheroViewModel.liveDataSuperheroesFromRepository().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun initViewModel() {
        superheroViewModel.getAllSuperheroesFromRepository()
    }
}
