package com.mjjang.apartmentsns.manager

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardManager {

    public fun showKeyboard(context: Context?, view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    public fun hideKeyboard(context: Context?, view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}