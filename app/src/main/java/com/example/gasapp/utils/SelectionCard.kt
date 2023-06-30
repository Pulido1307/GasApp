package com.example.gasapp.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.cardview.widget.CardView
import com.example.gasapp.R

class SelectionCard {
    companion object {
        fun selectionItem(cardView: CardView, context: Context) {
            cardView.setBackgroundColor(context.getColor(R.color.color_selection))
            Handler(Looper.getMainLooper()).postDelayed({

                cardView.setBackgroundColor(context.getColor(R.color.grey_items))
            }, 50)
        }
    }
}