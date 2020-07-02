package de.chillercode.deepdive.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import de.chillercode.deepdive.R
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsDetailFragment : Fragment() {

    private lateinit var viewModel: NotificationsDetailViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(NotificationsDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications_detail, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications_detail)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }
}