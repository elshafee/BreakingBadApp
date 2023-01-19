package com.elshafee.androiden.breakingbadapp.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elshafee.androiden.R
import com.elshafee.androiden.breakingbadapp.model.BreakingBadCharacter
import com.elshafee.androiden.databinding.ItemCharacterBinding

class CharacterListAdapter(private val clickCallBack: ((BreakingBadCharacter) -> Unit)?) :
    ListAdapter<BreakingBadCharacter, CharacterListAdapter.CharacterViewHolder>(CharacterCompare()) {

    class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val tvCharacterName: TextView = itemView.findViewById(R.id.tvCharacterName)
        private val tvCharacterNickName: TextView = itemView.findViewById(R.id.tvCharacterNickName)
        private val tvCharacterBirthday: TextView = itemView.findViewById(R.id.tvCharacterBirthday)
        private val tvCharacterOccupation: TextView =
            itemView.findViewById(R.id.tvCharacterOccupation)
        private val tvCharacterStatus: TextView = itemView.findViewById(R.id.tvCharacterStatus)
        private val ivCharacterImage: ImageView = itemView.findViewById(R.id.ivCharacterImage)

        fun bind(charcter: BreakingBadCharacter) {
            tvCharacterName.text = charcter.name
            tvCharacterNickName.text = charcter.nickname
            tvCharacterBirthday.text = charcter.birthday
            tvCharacterOccupation.text = charcter.occupation.joinToString(", ")
            tvCharacterStatus.text = charcter.status

            if (charcter.img != null) {
                Glide.with(itemView).load(charcter.img).centerCrop().into(ivCharacterImage)
            }
        }

        companion object {
            fun create(parent: ViewGroup): CharacterViewHolder {
                return CharacterViewHolder(
                    ItemCharacterBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.setOnClickListener { clickCallBack?.invoke(character) }
    }
}

class CharacterCompare : DiffUtil.ItemCallback<BreakingBadCharacter>() {
    override fun areItemsTheSame(
        oldItem: BreakingBadCharacter,
        newItem: BreakingBadCharacter
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BreakingBadCharacter,
        newItem: BreakingBadCharacter
    ): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.nickname == newItem.nickname &&
                oldItem.status == newItem.status &&
                oldItem.img == newItem.img &&
                oldItem.birthday == newItem.birthday &&
                oldItem.occupation == newItem.occupation
    }
}