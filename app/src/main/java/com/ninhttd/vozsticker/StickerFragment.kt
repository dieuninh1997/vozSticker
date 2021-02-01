package com.ninhttd.vozsticker

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast


class StickerFragment: BaseFragment() {
    private val TAG = "StickerFragment"

    // Flag bit, the flag has been initialized.
    private var isPrepared: Boolean = false
    private var rootView: View? = null

    // Layout in Loading
    private var loadingLayout: RelativeLayout? = null

    /* Has it been loaded once, and the second time you stop requesting data */
    private var mHasLoadedOnce = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_sticker, container, false)
            isPrepared = true
            lazyLoad()
        }
        return rootView
    }

    override fun lazyLoad() {
        if(!isPrepared || !isVisible || mHasLoadedOnce) {
            return
        }

        object : AsyncTask<Void?, Void?, Boolean>() {
            override fun onPreExecute() {
                super.onPreExecute()
                //Display loading progress dialog box
                loadingLayout!!.visibility = View.VISIBLE
            }
            override fun onPostExecute(isSuccess: Boolean) {
                if (isSuccess) {
                    // Loading Successful
                    mHasLoadedOnce = true
                    Log.e(TAG, "onPostExecute: =====isSuccedd=====")
                } else {
                    // Load failure
                    Toast.makeText(activity, "I failed.", Toast.LENGTH_SHORT)
                        .show()
                }
                //close dialog boxes
                loadingLayout!!.visibility = View.GONE
            }

            override fun doInBackground(vararg p0: Void?): Boolean {
                try {
                    Thread.sleep(1000)
                    //Here add the code that calls the interface to get the data
                    //doSomething()
                    fetchData()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return true
            }
        }.execute()
    }

    private fun fetchData() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (rootView != null) {
            (rootView!!.parent as ViewGroup).removeView(rootView)
            Log.e(TAG, "onDestroyView: =====")
        }
    }

}