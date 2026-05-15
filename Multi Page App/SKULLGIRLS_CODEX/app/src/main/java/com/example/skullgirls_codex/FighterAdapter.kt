package com.example.skullgirls_codex

import android.app.Activity
import com.example.skullgirls_codex.CharacterDetailActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FighterAdapter(
    private val context: Context,
    private var fighters: List<Fighter>
) : RecyclerView.Adapter<FighterAdapter.FighterViewHolder>() {

    private val prefs = context.getSharedPreferences("codex_prefs", Context.MODE_PRIVATE)

    class FighterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.fighterName)
        val image: ImageView = view.findViewById(R.id.fighterImage)
        val fav: ImageView = view.findViewById(R.id.favIcon)
        val difficulty: TextView = view.findViewById(R.id.fighterDifficulty)
        val style: TextView = view.findViewById(R.id.fighterStyle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FighterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fighter, parent, false)
        return FighterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FighterViewHolder, position: Int) {
        val fighter = fighters[position]

        holder.itemView.alpha = 0f
        holder.itemView.translationY = 50f

        holder.itemView.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(300)
            .start()

        holder.difficulty.text = fighter.difficulty
        holder.style.text = fighter.fightingStyle
        holder.name.text = fighter.name
        holder.image.setImageResource(fighter.imageRes)

        val isFav = prefs.getBoolean("fav_${fighter.name}", false)
        holder.fav.setImageResource(
            if (isFav) R.drawable.ic_fav_filled else R.drawable.ic_fav_outline
        )

        holder.fav.setOnClickListener {
            val newState = !prefs.getBoolean("fav_${fighter.name}", false)
            prefs.edit().putBoolean("fav_${fighter.name}", newState).apply()
            notifyItemChanged(position)
        }

        holder.itemView.setOnClickListener {
            holder.itemView.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(80)
                .withEndAction {

                    holder.itemView.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(80)
                        .start()

                    val intent = Intent(context, CharacterDetailActivity::class.java)

                    intent.putExtra("name", fighter.name)
                    intent.putExtra("bio", fighter.bio)
                    intent.putExtra("difficulty", fighter.difficulty)
                    intent.putExtra("style", fighter.fightingStyle)
                    intent.putExtra("image", fighter.imageRes)

                    context.startActivity(intent)
                    if (context is Activity) {

                        context.overridePendingTransition(
                            R.anim.slide_in_right,
                            R.anim.slide_out_left
                        )
                    }
                }
                .start()
        }
    }

    override fun getItemCount(): Int = fighters.size
    fun updateList(newList: List<Fighter>) {
        fighters = newList
        notifyDataSetChanged()
    }
}