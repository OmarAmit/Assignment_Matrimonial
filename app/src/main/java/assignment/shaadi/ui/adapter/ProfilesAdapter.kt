package assignment.shaadi.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import assignment.shaadi.R
import assignment.shaadi.database.entity.ProfileEntity
import assignment.shaadi.util.ItemClickListener

internal class ProfilesAdapter(
        private var itemClickListener: ItemClickListener,
        private var listProfileEntities: List<ProfileEntity>
) : RecyclerView.Adapter<ProfilesAdapter.ProfileHolder>() {

    internal inner class ProfileHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.li_profile_tv_name)
        val tvDetails: TextView = view.findViewById(R.id.li_profile_tv_details)
        val ivProfile: ImageView = view.findViewById(R.id.li_profile_iv_image)
        val ivAccept: ImageView = view.findViewById(R.id.li_profile_iv_accept)
        val ivDecline: ImageView = view.findViewById(R.id.li_profile_iv_decline)
        val tvDecline: TextView = view.findViewById(R.id.li_profile_tv_decline)
        val tvAccept: TextView = view.findViewById(R.id.li_profile_tv_accept)
        val tvStatus: TextView = view.findViewById(R.id.li_profile_tv_status)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile, parent, false)
        return ProfileHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProfileHolder, position: Int) {
        holder.tvName.text = "${listProfileEntities[position].nameTitle} " +
                "${listProfileEntities[position].firstName} " +
                listProfileEntities[position].lastName

        holder.tvDetails.text = "${listProfileEntities[position].age}, ${listProfileEntities[position].gender}" +
                "\n${listProfileEntities[position].city}, ${listProfileEntities[position].state}, " +
                "${listProfileEntities[position].country}, ${listProfileEntities[position].postalCode}"

        Glide.with(holder.ivProfile.context)
                .load(listProfileEntities[position].imageLarge)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .fallback(R.mipmap.ic_launcher_round)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.ivProfile)

        when (listProfileEntities[position].selectionStatus) {
            0 -> {
                holder.ivAccept.visibility = View.VISIBLE
                holder.ivDecline.visibility = View.VISIBLE
                holder.tvAccept.visibility = View.VISIBLE
                holder.tvDecline.visibility = View.VISIBLE
                holder.tvStatus.visibility = View.INVISIBLE
            }
            1 -> {
                holder.ivAccept.visibility = View.INVISIBLE
                holder.ivDecline.visibility = View.INVISIBLE
                holder.tvDecline.visibility = View.INVISIBLE
                holder.tvAccept.visibility = View.INVISIBLE
                holder.tvStatus.visibility = View.VISIBLE
                holder.tvStatus.text = holder.tvStatus.context.getString(R.string.member_accepted)
                holder.tvStatus.setBackgroundColor(Color.parseColor("#00af80"))
            }
            2 -> {
                holder.ivAccept.visibility = View.INVISIBLE
                holder.ivDecline.visibility = View.INVISIBLE
                holder.tvDecline.visibility = View.INVISIBLE
                holder.tvAccept.visibility = View.INVISIBLE
                holder.tvStatus.visibility = View.VISIBLE
                holder.tvStatus.text = holder.tvStatus.context.getString(R.string.member_declined)
                holder.tvStatus.setBackgroundColor(Color.parseColor("#FE5A61"))

            }
        }

        holder.ivAccept.setOnClickListener {
            listProfileEntities[position].selectionStatus = 1
            itemClickListener.onItemClick(position, listProfileEntities[position])

            val context = holder.ivAccept.context
            Toast.makeText(context, context.getString(R.string.member_accepted), Toast.LENGTH_LONG).show()
        }

        holder.ivDecline.setOnClickListener {
            listProfileEntities[position].selectionStatus = 2
            itemClickListener.onItemClick(position, listProfileEntities[position])

            val context = holder.ivDecline.context
            Toast.makeText(context, context.getString(R.string.member_declined), Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return listProfileEntities.size
    }
}