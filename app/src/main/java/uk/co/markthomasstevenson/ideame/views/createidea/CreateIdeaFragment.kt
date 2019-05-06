package uk.co.markthomasstevenson.ideame.views.createidea


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import uk.co.markthomasstevenson.ideame.R

/**
 * A simple [Fragment] subclass.
 */
class CreateIdeaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_idea, container, false)
    }


}
