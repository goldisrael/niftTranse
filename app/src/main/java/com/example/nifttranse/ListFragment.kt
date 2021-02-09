package com.example.nifttranse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.nifttranse.api.Resource
import com.example.nifttranse.data.Dec
import com.example.nifttranse.databinding.ListFragmentBinding
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {


    val viewModel: ListViewModel by viewModels()

    lateinit var binding:ListFragmentBinding
     var listDec:List<Dec>?=null
     var decTag:String="ABC"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var selected:Dec?
        viewModel.getDec().observe(viewLifecycleOwner){ networkResource->
            when (networkResource.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
                    if (networkResource.statusResponse == "sucsses") {
                        if(networkResource.data!=null)
                        {
                            listDec=networkResource.data
                            val listItems= arrayOfNulls<String>(listDec!!.size)
                            for(i in 0 until listItems.size)
                            {
                                listItems[i]= listDec!![i].firstname+" "+ listDec!![i].lastname
                            }
                            val adapter = ArrayAdapter<String>(this.requireContext().applicationContext, android.R.layout.simple_list_item_1, listItems)
                            listview.adapter = adapter
                        }
                        else
                        {
                            listview.isVisible=false
                            textView2.text="אין נפטרים רשומים כרגע"
                        }

                    } else {
                        Toast.makeText(requireContext(), "אירעה שגיאה בעת נסיון שליפה..", Toast.LENGTH_SHORT).show()

                    }
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), "בעיה בתקשורת עם השרת..", Toast.LENGTH_SHORT).show()

                }
            }
        }

      listview.setOnItemClickListener { parent, view, position, id ->
         selected= listDec?.get(position);
          viewModel.updateTag(selected!!.nift,this.decTag).observe(viewLifecycleOwner){ networkResource->
              when (networkResource.status) {
                  Resource.Status.LOADING -> {

                  }
                  Resource.Status.SUCCESS -> {
                      if (networkResource.statusResponse == "sucsses") {
                          if(networkResource.data!=null)
                          {
                             val item=networkResource.data
                              val bundle = bundleOf("dec" to selected)
                              findNavController().navigate(R.id.action_listFragment2_to_endFragment, bundle)
                          }
                          else
                          {
                              listview.isVisible=false
                              Toast.makeText(requireContext(),"אירעה שגיאה בניסיון העדכון", Toast.LENGTH_SHORT).show()
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

        newDec.setOnClickListener {
          findNavController().navigate(R.id. action_listFragment2_to_newDec)
      }
    }


    }




