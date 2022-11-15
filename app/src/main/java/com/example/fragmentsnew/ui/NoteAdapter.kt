package com.example.fragmentsnew.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentsnew.ItemActions
import com.example.fragmentsnew.data.room.NoteEntity

import com.example.fragmentsnew.databinding.SecondFragmentItemLayoutBinding

class NoteAdapter :
    RecyclerView.Adapter<NoteViewHolder>() { //class, that allows us to bind a text to
    // a TextView. The class exposes a static create() function that handles inflating the layout.

    /* override fun onClick(p0: View) {
         when (p0.id) {
             R.id.menu_more -> {
                 showPopUpMenu(p0)

             }
             else -> {


             }
         }
     }

     private fun showPopUpMenu(p0: View) {



             val popupMenu= PopupMenu(p0.context,p0)
             val context = p0.context
            // popupMenu.menu.add(0)
            // popupMenu.setOnMenuItemClickListener         return@setOnMenuItemClickListener true


         popupMenu.show()







     }

     */


    private var itemList = listOf<NoteEntity>() //список того что есть, изначально это пустой лист

    private var actions: ItemActions? = null //действия с айтемами
    fun bindaction(actions: ItemActions) {
        this.actions = actions
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        //когда хочет сохдать новый элемент списка
        val inflate =
            LayoutInflater.from(parent.context) //method, obtain an instance of LayoutInflater
        // from the provided context (context of the parent). The layout inflater knows how to inflate an
        // XML layout into a hierarchy of view objects.
        val binding = SecondFragmentItemLayoutBinding.inflate(inflate, parent, false)
        return (NoteViewHolder(binding))


        /* return NoteViewHolder(
             ItemLayoutBinding.inflate(
                 LayoutInflater.from(parent.context),
                 parent,
                 false
             )
         )

         */

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        // обновить элемент списка
        val item =
            getItem(holder.layoutPosition)//layoutPosition Returns the position of the ViewHolder
        // in terms of the latest layout pass.This position is mostly used by RecyclerView
        // components to be consistent while RecyclerView lazily processes adapter updates.

        holder.onBind(item)
        //  holder.binding.menuMore.tag = item
        // holder.binding.root.tag = item
        holder.itemView.setOnClickListener {
            actions?.apply { onClick(item.id) }
        }

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
        holder.itemView.setOnLongClickListener {
            actions?.let { it.onLongClick(item.email) }
            true

        }

        /*
        let полезен при работе с объектами, которые могут принимать значение null. Вместо того,
        чтобы создавать длинные цепочки выражений if-else, можно просто скомбинировать оператор ?
        («оператор безопасного вызова») с let: в результате мы получим лямбду, у которой аргумент
        it является не nullable-версией исходного объекта.
         */
        holder.myPopUpMenu.setOnClickListener {
            actions?.let { it.onLongClick(item.email) }
            /*
       let полезен при работе с объектами, которые могут принимать значение null. Вместо того,
        чтобы создавать длинные цепочки выражений if-else, можно просто скомбинировать оператор ?
         («оператор безопасного вызова») с let: в результате мы получим лямбду, у которой аргумент
         it является не nullable-версией исходного объекта.
        */


        }
        holder.deleteNote.setOnClickListener { actions?.let { it.onMenuDeleteDeleteNote(item) } }
        holder.myIdPressButton.setOnClickListener { actions?.let { it.onIdToFragmentId(item) } }


    }


    override fun getItemCount(): Int {
        //надо знать количество элементов

        return itemList.size
    }

    private fun getItem(position: Int): NoteEntity {

        return itemList[position]
    }

    fun setItemList(list: List<NoteEntity>) { // лист то что переадаеться в адаптер и для сравнения обновлений дифутил делаем ->

        val noteDiffCallBack =
            NoteDiffUtill(itemList, list) //обект класса созданного ранее для сравнения списков
        val diffResult =
            DiffUtil.calculateDiff(noteDiffCallBack, true) //считаем разницу первый параметр коллбэк
        itemList = list
        diffResult.dispatchUpdatesTo(this) // передаем все обновления списка (dispatchUpdatesTo) в адаптер


    }


}