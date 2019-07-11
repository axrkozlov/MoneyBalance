package com.axfex.moneybalance.data.source

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.icon.Icon
import java.io.IOException

class IconsManager(val context: Context) {


    private val ICONS_FOLDER = "icons/drawable"

    fun colorList(): List<Int> {
        val colors = ArrayList<Int>()
        val colorArray = context.resources.obtainTypedArray(R.array.iconColors)

        for (i in 0 until colorArray.length()) {
//            val name=context.resources.getResourceEntryName(colorArray.getResourceId(i,0))
            val color = colorArray.getColor(i, 0)
            colors.add(color)
        }
        colorArray.recycle()
        return colors
    }


    fun iconList(): List<Icon> {
        val icons = ArrayList<Icon>()
        try {
            val iconNames = context.assets.list(this.ICONS_FOLDER)

            iconNames?.forEach {
                val icon = Icon(it, 0)
                icon.sortOrder = "(\\d+)".toRegex().find(it)?.groups?.get(0)?.value?.toInt()
                icons.add(icon)
            }

        } catch (e: IOException) {
            Log.e("IconsManager", "loadIcons: ", e)
        }
        return icons.sortedBy { it.sortOrder }
    }

    private fun inflateVectorDrawable(name : String) : Drawable {
        val parser = context.assets.openXmlResourceParser("assets/$ICONS_FOLDER/$name")
        return VectorDrawableCompat.createFromXml(context.resources, parser)
    }

    fun getIconDrawable(icon: Icon): Drawable {
        return inflateVectorDrawable(icon.name)
    }


}