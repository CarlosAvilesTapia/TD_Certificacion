package cl.cat2814.tdcertificacion.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import cl.cat2814.tdcertificacion.R
import cl.cat2814.tdcertificacion.databinding.ItemLayoutSuperheroBinding
import cl.cat2814.tdcertificacion.model.localData.SuperheroEntity
import coil.load
import coil.transform.RoundedCornersTransformation

class SuperheroAdapter : RecyclerView.Adapter<SuperheroAdapter.ItemSuperheroViewHolder>() {

    private lateinit var binding: ItemLayoutSuperheroBinding
    private val superheroesList = mutableListOf<SuperheroEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuperheroAdapter.ItemSuperheroViewHolder {
        binding =
            ItemLayoutSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemSuperheroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuperheroAdapter.ItemSuperheroViewHolder, position: Int) {
        val superheroes = superheroesList[position]
        holder.bind(superheroes)

        // Aplicación de la animación creada en la carpeta res.
        holder.binding.cvSuperhero.startAnimation(
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim)
        )
    }

    override fun getItemCount(): Int {
        return superheroesList.size
    }

    fun setData(superheroes: List<SuperheroEntity>) {
        this.superheroesList.clear()
        this.superheroesList.addAll(superheroes)
        notifyDataSetChanged()
    }

    class ItemSuperheroViewHolder(val binding: ItemLayoutSuperheroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(superhero: SuperheroEntity) {
            binding.tvSuperheroName.text = superhero.name.uppercase()
            binding.ivSuperheroImage.load(superhero.imageLink) {
                transformations(RoundedCornersTransformation(20f))
                error(R.drawable.wonder_woman)
            }

            binding.cvSuperhero.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", superhero.id)
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_superheroesListFragment_to_superheroDetailFragment, bundle)
            }
        }
    }
}
