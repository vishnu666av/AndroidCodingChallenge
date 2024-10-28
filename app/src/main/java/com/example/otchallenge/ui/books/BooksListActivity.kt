package com.example.otchallenge.ui.books

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.otchallenge.MyApplication
import com.example.otchallenge.R
import com.example.otchallenge.databinding.ActivityMainBinding
import com.example.otchallenge.model.BookResults
import com.example.otchallenge.presenter.BooksPresenter
import com.example.otchallenge.utils.SpacingItemDecoration

/**
 * Activity that displays a list of books retrieved from the New York Times Books API.
 * This class implements the `BooksView` interface to handle UI updates based on
 * the data provided by the `BooksPresenter`.
 *
 * The `onCreate` method initializes the Dagger component for dependency injection,
 * sets up data binding, and configures the RecyclerView with a custom adapter and
 * item decoration. It also sets up a swipe-to-refresh feature for reloading the book
 * list.
 *
 * The activity observes and reacts to loading states, successfully loaded book data,
 * and error messages, updating the UI accordingly. The use of Observable properties
 * allows for efficient UI updates in response to data changes.
 */

class BooksListActivity : AppCompatActivity(), BooksView {

	private lateinit var binding: ActivityMainBinding
	private lateinit var presenter: BooksPresenter
	private lateinit var adapter: BooksAdapter

	var booksVisibility = ObservableInt(View.GONE)
	var errorVisibility = ObservableInt(View.GONE)
	var errorMessage = ObservableField<String>()

	override fun onCreate(savedInstanceState: Bundle?) {
		(application as MyApplication).appComponent.inject(this)
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		binding.view = this

		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		setupRecyclerView()
		val getBooksUseCase = (application as MyApplication).appComponent.getBooksUseCase()
		presenter = BooksPresenter(this, getBooksUseCase)
		presenter.loadBooks()
		binding.swipeRefreshLayout.setOnRefreshListener {
			presenter.loadBooks()
		}
	}

	private fun setupRecyclerView() {
		val itemDecoration = SpacingItemDecoration(
			spacing = 8,
			separatorHeight = 2,
			separatorColor = Color.GRAY
		)
		adapter = BooksAdapter(this)
		binding.recyclerView.adapter = adapter
		binding.recyclerView.layoutManager = LinearLayoutManager(this)
		binding.recyclerView.addItemDecoration(itemDecoration)
	}

	override fun showLoading(isLoading: Boolean) {
		binding.swipeRefreshLayout.isRefreshing = isLoading
	}

	override fun displayBooks(books: BookResults?) {
		booksVisibility.set(View.VISIBLE)
		errorVisibility.set(View.GONE)
		binding.bookListTitle.text = books?.displayName
		books?.books?.let { adapter.updateBooks(it) }
	}

	override fun showError(errorMessage: String) {
		this.errorMessage.set(errorMessage)
		booksVisibility.set(View.GONE)
		errorVisibility.set(View.VISIBLE)
	}
}
