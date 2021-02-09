package com.example.nifttranse

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.nifttranse.api.Resource
import com.example.nifttranse.data.Dec
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.android.synthetic.main.new_dec_fragment.*

class newDec : Fragment() {

    companion object {
        fun newInstance() = newDec()
    }

    var decTag: String = "ABC"
    val viewModel: NewDecViewModel by viewModels()
    var dec: Dec? = Dec(0, 0, "", "", "", "", "", "")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_dec_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        save.setOnClickListener {
            dec?.firstname = firstname.text.toString()
            dec?.lastname = lastname.text.toString()
            dec?.name=decTag
            viewModel.newDec(dec).observe(viewLifecycleOwner) { networkResource ->
                when (networkResource.status) {
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        if (networkResource.statusResponse == "sucsses") {
                            if (networkResource.data != null) {
                                val item = networkResource.data
                                dec?.nift=item.nift
                                val bundle = bundleOf("dec" to dec)
                                findNavController().navigate(R.id.action_newDec_to_endFragment, bundle)
                            } else {
                                listview.isVisible = false
                                Toast.makeText(requireContext(), "אירעה שגיאה בניסיון העדכון", Toast.LENGTH_SHORT).show()
                            }

                        } else {
                            Toast.makeText(requireContext(), "אירעה שגיאה בעת נסיון עדכון..", Toast.LENGTH_SHORT).show()

                        }
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(requireContext(), "בעיה בתקשורת עם השרת..", Toast.LENGTH_SHORT).show()

                    }

                }

            }
        }

    }
}