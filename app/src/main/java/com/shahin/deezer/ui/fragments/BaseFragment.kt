package com.shahin.deezer.ui.fragments

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding : ViewBinding>(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {
    protected var _binding : Binding? = null
    protected  val binding : Binding get() = _binding!!

    /**
     * we would like to remove binding on detach
     * just in case  we need fragment destroy view to clear any view
     */
    override fun onDetach() {
        super.onDetach()
        _binding = null
    }

}
