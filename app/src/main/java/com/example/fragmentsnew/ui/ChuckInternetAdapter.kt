package com.example.fragmentsnew.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


import com.example.fragmentsnew.data.retrofit.chuckList.JokesRandomMulti

import com.example.fragmentsnew.databinding.ThirdFragmentItemLayoutBinding

class ChuckInternetAdapter() :
    RecyclerView.Adapter<ChuckViewHolder>() {
    // ка сделать в первичном конструкторе тоже самое
    private var actionOnLongPress: (() -> Unit)? = null

  /*  fun createAction(action: (() -> Unit)) {
        this.actionOnLongPress = action
    }*/

    private var listOfChuck = listOf<JokesRandomMulti>()


//https://developer.android.com/codelabs/basic-android-kotlin-training-recyclerview-scrollable-list?hl=en&authuser=0#3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChuckViewHolder {
        // когда адаптер хочет создать новый элемент списка
        //The onCreateViewHolder()method is called by the layout manager to create new view
        // holders for the RecyclerView (when there are no existing view holders that can be reused).
        // Remember that a view holder represents a single list item view.


        val adapterLayoutInflater = LayoutInflater.from(parent.context)
        //Once you have a LayoutInflater object instance, add a period followed by another method call
        // to inflate the actual list item view. Pass in the XML layout resource ID R.layout.list_item
        // and the parent view group. The third boolean argument is attachToRoot. This argument needs to be false,
        // because RecyclerView adds this item to the view hierarchy for you when it's time.
        val binding = ThirdFragmentItemLayoutBinding.inflate(adapterLayoutInflater, parent, false)

        //Now adapterLayout holds a reference to the list item view (from which we can later find
        //child views like the TextView). In onCreateViewHolder(), return a new ItemViewHolder instance where the root view is adapterLayout.
        return ChuckViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ChuckViewHolder, position: Int) {
        // обновить элемент списка
        //onBindViewHolder(). This method is called by the layout manager in order to replace the contents of a list item view.
        // The onBindViewHolder() method has two parameters, an ItemViewHolder previously created by the onCreateViewHolder()
        // method, and an int that represents the current item position in the list. In this method, you will find the right ITEM object from
        // the data set based on position.


        //val item = listOfChuck[position] или тоже самое снизу
        val item = getItem(holder.layoutPosition)

        holder.onBindChuck(item)


        /*
 Функция apply работает почти так же, как with, но возвращает объект, переданный в аргументе.
fun printAlphabet() = StringBuilder().apply{
for (letter in 'A'..'Z'){
 append(letter)
}
}.toString()

println(printAlphabet())// ABCDEFGHIJKLMNOPQRSTUVWXYZ
Полезна в тех случаях, когда требуется создание экземпляра, у которого следует
инициализировать некоторые свойства. Часто в этих случаях мы просто повторяем имя экземпляра.
  */


        // holder.itemView.setOnClickListener { actionOnLongPress?.apply { invoke() } }

        /*
        Лямбда-выражение в функции let в качестве параметра it получает объект, для которого
         вызывается функция. Возвращаемый результат функции let представляет результат данного лямбда-выражения.
         inline fun <T, R> T.let(block: (T) -> R): R

         fun main() {

    val sam = Person("Sam", "sam@gmail.com")
    sam.email?.let{ println("Email: $it") }     // Email: sam@gmail.com
    // аналог без функции let
    //if(sam.email!=null) println("Email:${sam.email}")

    val tom = Person("Tom", null)
    tom.email?.let{ println("Email: $it") } // функция let не будет выполняться

}
data class Person(val name: String, val email: String?)
        let полезен при работе с объектами, которые могут принимать значение null. Вместо того,
         чтобы создавать длинные цепочки выражений if-else, можно просто скомбинировать оператор ?
          («оператор безопасного вызова») с let: в результате мы получим лямбду, у которой аргумент
          it является не nullable-версией исходного объекта.
         */
        holder.itemView.setOnLongClickListener {
            actionOnLongPress.let {
                it?.invoke()
                true
            }
        }

    }

    override fun getItemCount(): Int {
        return listOfChuck.size
    }

    fun getItem(position: Int): JokesRandomMulti {
        return listOfChuck[position]


    }

    fun setItemList(list: List<JokesRandomMulti>) {
        val chuckDifCallBack = ChuckDiffUtill(listOfChuck, list)
        val difrence = DiffUtil.calculateDiff(chuckDifCallBack, true)
        listOfChuck = list
        difrence.dispatchUpdatesTo(this)

    }


}