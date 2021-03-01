package com.batierlou.tp03_batier_lou.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.batierlou.tp03_batier_lou.NavigationListener
import com.batierlou.tp03_batier_lou.R
import com.batierlou.tp03_batier_lou.adapters.ListNeighborHandler
import com.batierlou.tp03_batier_lou.adapters.ListNeighborsAdapter
import com.batierlou.tp03_batier_lou.databinding.ListNeighborsFragmentBinding
import com.batierlou.tp03_batier_lou.di.DI
import com.batierlou.tp03_batier_lou.models.Neighbor
import com.batierlou.tp03_batier_lou.viewmodels.NeighborViewModel
import java.util.concurrent.Executors

class ListNeighborsFragment : Fragment(), ListNeighborHandler {

    private lateinit var binding: ListNeighborsFragmentBinding
    private lateinit var viewModel: NeighborViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()

        binding.addNeighbor.setOnClickListener(
            View.OnClickListener {
                (activity as? NavigationListener)?.showFragment(AddNeighborFragment())
            }
        )
    }

    override fun onDeleteNeighbor(neighbor: Neighbor) {
        AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_warning)
            .setTitle("Supprimer un voisin ?")
            .setMessage("Êtes vous sur de vouloir supprimer un voisin ?")
            .setPositiveButton(
                "supprimer",
                DialogInterface.OnClickListener { dialog, which -> deleteNeighbor(neighbor) }
            )
            .setNegativeButton("annuler", null)
            .show()
    }

    fun deleteNeighbor(neighbor: Neighbor) {
        // On supprime des datas
        Executors.newSingleThreadExecutor().execute {
            DI.repository.delete(neighbor)
        }
    }

    private fun setData() {
        viewModel.neighbors.observe(viewLifecycleOwner) {
            val adapter = ListNeighborsAdapter(it, this)
            binding.neighborsList.adapter = adapter
        }
    }
}
