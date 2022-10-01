package com.example.fragmentsnew

class VolumeList(val values: List<Int>, val currentIndex: Int) {

    companion object {
        fun CreateVolumeValues(currentValue: Int): VolumeList {
            val values: IntProgression = (0..100 step 10)
            val currentIndex = values.indexOf(currentValue)
            return if (currentIndex == -1) { // если значение не  кртоно 10 тогда  индекс =" - 1"
                val list = values + currentValue
                VolumeList(list,list.lastIndex)
            } else {
                VolumeList(values.toList(),currentIndex)
            }
        }
    }

}