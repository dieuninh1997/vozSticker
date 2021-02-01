package com.ninhttd.vozsticker

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    /** Fragment Is the current state visible  */
    protected var isVisibled = false
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            isVisibled = true
            onVisible()
        } else {
            isVisibled = false
            onInvisible()
        }
    }

    //  So
    protected fun onVisible() {
        lazyLoad()
    }

    // Invisible
    protected fun onInvisible() {}

    //Delayed loading
    //Subclasses must override this method
    protected abstract fun lazyLoad()
}