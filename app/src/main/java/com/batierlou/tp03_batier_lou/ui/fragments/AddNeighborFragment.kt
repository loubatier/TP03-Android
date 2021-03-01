package com.batierlou.tp03_batier_lou.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.batierlou.tp03_batier_lou.NavigationListener
import com.batierlou.tp03_batier_lou.R
import com.batierlou.tp03_batier_lou.repositories.NeighborRepository
import com.batierlou.tp03_batier_lou.databinding.AddNeighborFragmentBinding
import com.batierlou.tp03_batier_lou.models.Neighbor
import kotlin.random.Random

class AddNeighborFragment : Fragment() {

    // TODO : Upload l'image choisie dans l'ImageView en haut quand on a fini d'edit imageField
    // TODO : Griser le bouton tant que les fields ne sont pas remplis

    lateinit var binding: AddNeighborFragmentBinding
    lateinit var mAwesomeValidation: AwesomeValidation
    lateinit var neighbor: Neighbor

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initValidation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddNeighborFragmentBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {

        (activity as? NavigationListener)?.updateTitle(R.string.add_fragment_title)

        binding.saveButton.setOnClickListener(View.OnClickListener {
            if (mAwesomeValidation.validate()) {
                val id: Long = Random.nextLong(from = 0, until = Long.MAX_VALUE)
                val name: String = binding.nameField.text.toString()
                val avatarUrl: String = binding.imageField.text.toString()
                val mail: String = binding.mailField.text.toString()
                val tel: String = binding.telField.text.toString()
                val about: String = binding.aboutField.text.toString()
                val siteUrl: String = binding.siteField.text.toString()

                neighbor = Neighbor(id, name, avatarUrl, mail, tel, about, false, siteUrl)
                NeighborRepository.getInstance().dataSource.createNeighbor(neighbor)

                (activity as? NavigationListener)?.showFragment(ListNeighborsFragment())
            }
        })
    }

    private fun initValidation() {
        mAwesomeValidation = AwesomeValidation(ValidationStyle.BASIC)

        mAwesomeValidation.addValidation(
            binding.imageField,
            android.util.Patterns.WEB_URL,
            getString(R.string.err_image)
        )
        mAwesomeValidation.addValidation(
            binding.telField,
            "^0[6-7]([-. ]?[0-9]{2}){4}$",
            getString(R.string.err_tel)
        )
        mAwesomeValidation.addValidation(
            binding.siteField,
            android.util.Patterns.WEB_URL,
            getString(R.string.err_site)
        )
        mAwesomeValidation.addValidation(
            binding.mailField,
            android.util.Patterns.EMAIL_ADDRESS,
            getString(R.string.err_mail)
        )
        mAwesomeValidation.addValidation(
            binding.aboutField,
            "^.{1,30}$",
            getString(R.string.err_about)
        )
    }
}