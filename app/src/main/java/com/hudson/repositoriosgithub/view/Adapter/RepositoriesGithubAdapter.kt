package com.hudson.repositoriosgithub.view.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hudson.repositoriosgithub.R
import com.hudson.repositoriosgithub.domain.models.AppDataGithub

class RepositoriesGithubAdapter(private val list: List<AppDataGithub>) :
    RecyclerView.Adapter<RepositoriesGithubAdapter.RepositoriesGithubViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoriesGithubViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.git_repositories,parent,false)
        return RepositoriesGithubViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoriesGithubViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class RepositoriesGithubViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bind(data: AppDataGithub) {
            with(itemView) {
                val name = findViewById<TextView>(R.id.nameRepository)
                val nameRepository = findViewById<TextView>(R.id.nameRepository)
                val numberOfStars = findViewById<TextView>(R.id.starPoints)
                val picture = findViewById<ImageView>(R.id.photoUser)
                name.text = data.author.toString()
                nameRepository.text = data.nameRepository.toString()
                numberOfStars.text = data.numberOfStars.toString()


            }
        }
    }
}