package com.example.gasapp.utils

import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView

class RotateImage {
    companion object {
        fun rotateImage(imageView: ImageView) {
            val animation = RotateAnimation(
                0f, 360f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f
            )

            animation.duration = 2000
            animation.repeatCount = Animation.INFINITE
            animation.repeatMode = Animation.REVERSE
            imageView.startAnimation(animation)
        }
    }
}