/*
 * Copyright (c) 2020. By Shahin Montazeri (shahin.montazeri@gmail.com
 */

package com.shahin.deezer.ui.fragments

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Base Fragment
 * Providing Binding
 *
 * CAUTION: Do not use any views after
 * @see onDetach is called
 */
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
