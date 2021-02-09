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
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.nifttranse.api.Resource
import com.example.nifttranse.data.Dec
import kotlinx.android.synthetic.main.end_fragment.*
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.coroutines.NonCancellable.start

class endFragment : Fragment() {


    val viewModel: EndViewModel by viewModels()
    var dec: Dec?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dec = arguments?.getParcelable<Dec>("dec")
//       arguments?.getParcelable<Dec>("dec")?.let { viewModel.start(it) }


        return inflater.inflate(R.layout.end_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(dec!=null)
        {
            val string=dec!!.firstname
            if(string!=null)
            textView6.text=string
        }
        cancel.setOnClickListener {
            viewModel.cancelTag(dec!!.nift).observe(viewLifecycleOwner) { networkResource ->
                when (networkResource.status) {
                    Resource.Status.LOADING -> {
                    }
                    Resource.Status.SUCCESS -> {
                        if (networkResource.statusResponse == "sucsses") {
                                findNavController().navigate(R.id.action_endFragment_to_listFragment2)
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
