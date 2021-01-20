package com.oganbelema.elearning.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.oganbelema.elearning.R
import com.oganbelema.elearning.databinding.DialogProgressBinding

class CustomProgressDialog(context: Context?) : Dialog(context!!, R.style.FullScreenDialogStyle) {

    private lateinit var rotate: RotateAnimation

    private lateinit var dialogProgressBinding: DialogProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialogProgressBinding = DialogProgressBinding.inflate(layoutInflater)
        setContentView(dialogProgressBinding.root)

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        val window = this.window
        if (window != null) {
            window.setLayout(width, height)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        setCancelable(false)
        setCanceledOnTouchOutside(true)
        rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = 1000
        rotate.repeatCount = Animation.INFINITE
        dialogProgressBinding.ivLogo.startAnimation(rotate)
    }

}