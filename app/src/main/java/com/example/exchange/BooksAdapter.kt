package com.example.exchange

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class BooksAdapter(
    options: FirestoreRecyclerOptions<Book>,
    private val onGetClick: (Book) -> Unit
) : FirestoreRecyclerAdapter<Book, BooksAdapter.BookViewHolder>(options) {



    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookName: TextView = itemView.findViewById(R.id.text_view_book_name)
        val bookClass: TextView = itemView.findViewById(R.id.text_view_class)
        val subject: TextView = itemView.findViewById(R.id.text_view_subject)
        val price: TextView = itemView.findViewById(R.id.text_view_price)
        val sellerContact: TextView = itemView.findViewById(R.id.text_view_seller_contact)
        val getButton: Button = itemView.findViewById(R.id.button_get)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.design_layout, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int, model: Book) {
        holder.bookName.text = model.name
        holder.bookClass.text = model.className
        holder.subject.text = model.subject
        holder.price.text = model.price
        holder.sellerContact.text = model.contact

        holder.getButton.setOnClickListener {
            onGetClick(model)
        }
    }
}
