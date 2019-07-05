package com.axfex.moneybalance.data.source

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.icon.Icon
import com.axfex.moneybalance.domain.icon.IconColor
import java.io.IOException

class IconsManager(val context: Context) {


    private val ICONS_FOLDER="icons/drawable"

    val icons = ArrayList<Icon>()

    val colors= ArrayList<IconColor>()

    init {
        loadIcons()
        loadColors()
    }

    private fun loadColors() {

        val colorArray=context.resources.obtainTypedArray(R.array.iconColors)
        for (i in 0..colorArray.length()-1) {
            val name=context.resources.getResourceEntryName(colorArray.getResourceId(i,0))
            val color=colorArray.getColor(i,0)

            val iconColor=IconColor(name,color)
            val id = context.resources.getIdentifier(name,"gray",context.packageName)
//            Log.i("IconsManager", "loadColors:$name, ${colorArray.getString(i)},${id},${colorArray.getColor(i,0)}")

            colors.add(iconColor)
        }

        colorArray.recycle()

    }


    private fun loadIcons(){
        try {
            val iconNames = context.assets.list(this.ICONS_FOLDER)

            iconNames?.forEach {
                val parser=context.assets.openXmlResourceParser("assets/${this.ICONS_FOLDER}/$it")
                val d = VectorDrawableCompat.createFromXml(context.resources,parser)
                val icon= Icon(it,d,null)
                icons.add(icon)
            }
        } catch (e:IOException){
            Log.e("IconsManager", "loadIcons: ", e )
        }
    }

    private fun icon(name:String){

    }

    private fun icon(name:String,color:String){

    }



//    private fun loadIcons():List<Drawable>{
//
//        val icons:ArrayList<Drawable> = ArrayList()
//        try {
//            val iconNames = context.assets.list(ICONS_FOLDER)
//            iconNames?.forEach {
//                val d = Drawable.createFromStream(context.assets.open("$ICONS_FOLDER/$it"), null)
//                icons.add(d)
//            }
//
//        } catch (e:IOException){
//            Log.e("IconsManager", "loadIcons: ", e )
//        }
//
//        return icons
//    }
//

}