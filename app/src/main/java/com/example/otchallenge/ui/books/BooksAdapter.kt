package com.example.otchallenge.ui.books

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.otchallenge.R
import com.example.otchallenge.databinding.ItemBookBinding
import com.example.otchallenge.model.Book

/**
 * Adapter class for displaying a list of books in a RecyclerView.
 * This class manages the binding of book data to views and handles the
 * creation of view holders. It maintains a mutable list of books
 * and provides methods to update this list and notify the RecyclerView of changes.
 * The `BookViewHolder` inner class is responsible for binding individual book items
 * to their corresponding views using data binding and Glide for image loading.
 * It also includes functionality to show an information popup with book details
 * when the info icon is clicked, enhancing user interaction.
 */

class BooksAdapter(
    private val context: Context,
) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    private val books = mutableListOf<Book>()

    fun updateBooks(newBooks: List<Book>) {
        books.clear()
        books.addAll(newBooks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BookViewHolder {
        val binding: ItemBookBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_book,
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksAdapter.BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    inner class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.book = book
            Glide.with(binding.bookImage.context)
                .load(book.bookImage)
                .into(binding.bookImage)
            binding.infoIcon.setOnClickListener {
                showInfoPopup(book)
            }
            binding.executePendingBindings()
        }
    }

    private fun showInfoPopup(book: Book) {
        AlertDialog.Builder(context)
            .setTitle(book.title)
            .setMessage(
                "Rank: #${book.rank}\n\n" +
                        "Price: ${book.price}\n\n" +
                        "Publisher: ${book.publisher}\n\n"
            )
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}