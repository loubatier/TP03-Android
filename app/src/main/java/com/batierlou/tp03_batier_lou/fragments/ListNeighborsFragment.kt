package com.batierlou.tp03_batier_lou.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.batierlou.tp03_batier_lou.NavigationListener
import com.batierlou.tp03_batier_lou.R
import com.batierlou.tp03_batier_lou.adapters.ListNeighborHandler
import com.batierlou.tp03_batier_lou.adapters.ListNeighborsAdapter
import com.batierlou.tp03_batier_lou.data.NeighborRepository
import com.batierlou.tp03_batier_lou.databinding.ListNeighborsFragmentBinding
import com.batierlou.tp03_batier_lou.models.Neighbor
import kotlin.random.Random

class ListNeighborsFragment : Fragment(), ListNeighborHandler {

    private lateinit var binding: ListNeighborsFragmentBinding
    private lateinit var adapter: ListNeighborsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as? NavigationListener)?.updateTitle(R.string.list_fragment_title)

        binding = ListNeighborsFragmentBinding.inflate(inflater, container, false)
        binding.neighborsList.layoutManager = LinearLayoutManager(context)
        binding.neighborsList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        adapter = ListNeighborsAdapter(neighbors, this)
        binding.neighborsList.adapter = adapter

        binding.addNeighbor.setOnClickListener(View.OnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighborFragment())
            }
        })
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_warning)
            .setTitle("Supprimer un voisin ?")
            .setMessage("Êtes vous sur de vouloir supprimer un voisin ?")
            .setPositiveButton("supprimer",
                DialogInterface.OnClickListener { dialog, which -> deleteNeighbor(neighbor, adapter) })
            .setNegativeButton("annuler", null)
            .show()
    }

    fun deleteNeighbor(neighbor: Neighbor, adapter: ListNeighborsAdapter) {
        // On supprime des datas
        val deletedItemIndex : Int = NeighborRepository.getInstance().dataSource.deleteNeighbor(neighbor)

        // On supprime de la view
        binding.neighborsList.removeViewAt(deletedItemIndex)
        adapter.notifyItemRemoved(deletedItemIndex)
        adapter.notifyItemRangeChanged(deletedItemIndex, NeighborRepository.getInstance().getNeighbours().size);
    }
}