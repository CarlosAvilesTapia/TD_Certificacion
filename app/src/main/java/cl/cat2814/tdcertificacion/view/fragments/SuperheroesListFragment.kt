package cl.cat2814.tdcertificacion.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.cat2814.tdcertificacion.R
import cl.cat2814.tdcertificacion.databinding.FragmentSuperheroesListBinding
import cl.cat2814.tdcertificacion.viewModel.SuperheroesViewModel

class SuperheroesListFragment : Fragment() {

    private lateinit var binding: FragmentSuperheroesListBinding
    private val superheroesViewModel: SuperheroesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSuperheroesListBinding.inflate(layoutInflater, container, false)

        initViewModel()



        return binding.root
    }

    private fun initViewModel() {
        superheroesViewModel.getAllSuperheroesFromRepository()
    }
}