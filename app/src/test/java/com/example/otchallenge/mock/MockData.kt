package com.example.otchallenge.mock

import com.example.otchallenge.model.Book
import com.example.otchallenge.model.BookResponse
import com.example.otchallenge.model.BookResults
import com.example.otchallenge.model.BuyLink
import com.example.otchallenge.model.Isbn

    fun createMockBookResponse(): BookResponse {
        val mockIsbn = Isbn(isbn10 = "1234567890", isbn13 = "123-1234567890")
        val mockBuyLink = BuyLink(name = "Amazon", url = "https://www.amazon.com")
        val mockBook = Book(
            rank = 1,
            rankLastWeek = 2,
            weeksOnList = 3,
            asterisk = 0,
            dagger = 0,
            primaryIsbn10 = "1234567890",
            primaryIsbn13 = "123-1234567890",
            publisher = "Mock Publisher",
            description = "A mock description of the book.",
            price = "$19.99",
            title = "Mock Book Title",
            author = "Mock Author",
            contributor = "Mock Contributor",
            contributorNote = null,
            bookImage = "https://mockimageurl.com",
            bookImageWidth = 200,
            bookImageHeight = 300,
            amazonProductUrl = "https://www.amazon.com",
            ageGroup = null,
            bookReviewLink = "https://mockbookreview.com",
            firstChapterLink = "https://mockfirstchapter.com",
            sundayReviewLink = "https://mocksundayreview.com",
            articleChapterLink = "https://mockarticlechapter.com",
            isbns = listOf(mockIsbn),
            buyLinks = listOf(mockBuyLink),
            bookUri = "mock:book:uri"
        )

        // Mocking BookResults data
        val mockBookResults = BookResults(
            listName = "Hardcover Fiction",
            listNameEncoded = "hardcover-fiction",
            bestsellersDate = "2024-01-01",
            publishedDate = "2024-01-08",
            publishedDateDescription = "Published Weekly",
            nextPublishedDate = null,
            previousPublishedDate = "2023-12-25",
            displayName = "Hardcover Fiction",
            normalListEndsAt = 15,
            updated = "WEEKLY",
            books = listOf(mockBook),
            corrections = listOf("Correction 1")
        )

        return BookResponse(
            status = "OK",
            copyright = "© 2024 Mock NY Times",
            numResults = 1,
            lastModified = "2024-01-08T12:00:00-05:00",
            results = mockBookResults
        )
    }
