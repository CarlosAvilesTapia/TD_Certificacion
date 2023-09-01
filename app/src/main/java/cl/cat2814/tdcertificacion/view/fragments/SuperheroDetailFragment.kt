package cl.cat2814.tdcertificacion.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cl.cat2814.tdcertificacion.R
import cl.cat2814.tdcertificacion.databinding.FragmentSuperheroDetailBinding
import cl.cat2814.tdcertificacion.viewModel.SuperheroViewModel
import coil.load
import coil.transform.RoundedCornersTransformation

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

        initListener()

        return binding.root
    }

    private fun initViewModel() {
        superheroViewModel.getSuperheroeDetailFromRepository(superheroId)
        superheroViewModel.liveDataSuperheroDetailFromRepository(superheroId)
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.tvSuperheroDetailName.text = it.name.uppercase()
                    binding.tvBirthPlace.text = it.birthPlace
                    binding.tvCreationYear.text = it.creationYear.toString()
                    binding.tvSuperheroColor.text = it.color
                    binding.tvPowerDetail.text = it.superPower
                    binding.ivSuperheroDetail.load(it.imageLink) {
                        transformations(RoundedCornersTransformation(30f))
                        error(R.drawable.wonder_woman)
                    }
                    if (it.translation) {
                        binding.tvTranslation.text = getString(R.string.translate_true)
                    } else {
                        binding.tvTranslation.text = getString(R.string.translate_false)
                    }
                }
            }
    }

    private fun initListener() {
        binding.fabEmail.setOnClickListener {
            val intent = createEmailIntent()
            startActivity(Intent.createChooser(intent, getString(R.string.send_email)))
        }
    }

    private fun createEmailIntent(): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        val name = binding.tvSuperheroDetailName.text
        intent.data = Uri.parse(getString(R.string.mailto))
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.email_address)))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Votación: $name")
        intent.putExtra(
            Intent.EXTRA_TEXT, getEmailBodyContent(name)
        )
        return intent
    }

    private fun getEmailBodyContent(name: CharSequence): String {
        return "Hola.\n"+
               "Quiero que el super héroe $name aparezca en la nueva edición de biografías animadas.\n" +
               "Número contacto: _________\n" +
                       "Gracias."
    }
}
