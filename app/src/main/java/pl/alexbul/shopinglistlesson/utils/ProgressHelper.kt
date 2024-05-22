package pl.alexbul.shopinglistlesson.utils

class ProgressHelper {
    fun getProgress(allCount:Int, selectedCount:Int): Float{
        return if(selectedCount ==0) 0.0f
        else
            selectedCount.toFloat()/allCount.toFloat()
    }
}