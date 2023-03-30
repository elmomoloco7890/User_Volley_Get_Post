package prime.projects.user_volley_get_post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import prime.projects.user_volley_get_post.data.Hotlines
import prime.projects.user_volley_get_post.databinding.VolleyItemRowBinding
import prime.projects.user_volley_get_post.fragments.MessageItemClickListener

class HotlineAdapter(private val list: ArrayList<Hotlines>,
                     private val hotItemClick: MessageItemClickListener
                     ): RecyclerView.Adapter<HotlineAdapter.HotlineViewHolder>() {

    private lateinit var binding: VolleyItemRowBinding

    class HotlineViewHolder(var hotlineItems: VolleyItemRowBinding): RecyclerView.ViewHolder(hotlineItems.root){
        fun bind(cItem: Hotlines, listener: MessageItemClickListener){
            hotlineItems.hotlines = cItem
            hotlineItems.hotItemClicked = listener
            hotlineItems.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotlineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val hotLineBinding = VolleyItemRowBinding.inflate(inflater, parent, false)
        binding = hotLineBinding
        return HotlineViewHolder(hotLineBinding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HotlineViewHolder, position: Int) =
        holder.bind(list[position], hotItemClick)

}