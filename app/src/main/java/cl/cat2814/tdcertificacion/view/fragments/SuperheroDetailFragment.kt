package cl.cat2814.tdcertificacion.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.cat2814.tdcertificacion.databinding.FragmentSuperheroDetailBinding
import cl.cat2814.tdcertificacion.viewModel.SuperheroViewModel

private const val ARG_SUPERHERO_ID = "id"

class SuperheroDetailFragment : Fragment() {

    private var superheroId: Int = 0
    private lateinit var binding: FragmentSuperheroDetailBinding
    private val superheroViewModel: SuperheroViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            superheroId = it.getInt(ARG_SUPERHERO_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSuperheroDetailBinding.inflate(layoutInflater, container, false)

        initViewModel()

        return binding.root
    }

    private fun initViewModel() {
        superheroViewModel.getSuperheroeDetailFromRepository(superheroId)
    }

}