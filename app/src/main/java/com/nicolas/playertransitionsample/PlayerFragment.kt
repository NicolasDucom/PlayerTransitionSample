package com.nicolas.playertransitionsample

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nicolas.playertransitionsample.databinding.FragmentPlayerBinding
import com.nicolas.playertransitionsample.databinding.RvItemBinding
import com.nicolas.playertransitionsample.utils.viewBinding

class PlayerFragment : Fragment(R.layout.fragment_player) {

    private val binding by viewBinding(FragmentPlayerBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playerLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                if (p1 == R.id.start) {
                    (activity as? MainActivity)?.updateProgress(p3)
                }
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                if(p1 != R.id.start) {
                    (activity as? MainActivity)?.updateProgress(100f)
                } else {
                    (activity as? MainActivity)?.updateProgress(0f)
                }
                if(p1 == R.id.start) {
                    binding.recyclerView.layoutManager?.scrollToPosition(0)
                }
                if(p1 == R.id.top) {
                    binding.recyclerView.suppressLayout(false)
                } else {
                    binding.recyclerView.suppressLayout(true)
                }
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }
        })
        binding.recyclerView.adapter = RandomAdapter(layoutInflater) {
            binding.playerLayout.transitionToState(R.id.end)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private class RandomAdapter(val inflater: LayoutInflater,val onClick: () -> Unit) :
        RecyclerView.Adapter<RandomAdapter.RandomViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomViewHolder {
            return RandomViewHolder(RvItemBinding.inflate(inflater, null, false))
        }

        override fun onBindViewHolder(holder: RandomViewHolder, position: Int) {
            holder.set(position)
            holder.itemView.setOnClickListener {
                onClick.invoke()
            }
        }

        override fun getItemCount(): Int = 50

        private class RandomViewHolder(val view: RvItemBinding) : RecyclerView.ViewHolder(view.root) {
            fun set(position: Int) {
                view.rvText.text = "Queue $position"
            }
        }
    }

    companion object {
        fun newInstance() = PlayerFragment()
    }
}