package com.example.otchallenge.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response structure for the book API.
 * It includes fields for the API response.
 * This class is used for deserializing the JSON response from the API
 * into Kotlin objects for easier data handling and manipulation within the application.
 */

data class BookResponse(
    @SerializedName("status") val status: String,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("num_results") val numResults: Int,
    @SerializedName("last_modified") val lastModified: String,
    @SerializedName("results") val results: BookResults
)

data class BookResults(
    @SerializedName("list_name") val listName: String,
    @SerializedName("list_name_encoded") val listNameEncoded: String,
    @SerializedName("bestsellers_date") val bestsellersDate: String,
    @SerializedName("published_date") val publishedDate: String,
    @SerializedName("published_date_description") val publishedDateDescription: String,
    @SerializedName("next_published_date") val nextPublishedDate: String?,
    @SerializedName("previous_published_date") val previousPublishedDate: String?,
    @SerializedName("display_name") val displayName: String,
    @SerializedName("normal_list_ends_at") val normalListEndsAt: Int,
    @SerializedName("updated") val updated: String,
    @SerializedName("books") val books: List<Book>,
    @SerializedName("corrections") val corrections: List<String>
)

data class Book(
    @SerializedName("rank") val rank: Int,
    @SerializedName("rank_last_week") val rankLastWeek: Int,
    @SerializedName("weeks_on_list") val weeksOnList: Int,
    @SerializedName("asterisk") val asterisk: Int,
    @SerializedName("dagger") val dagger: Int,
    @SerializedName("primary_isbn10") val primaryIsbn10: String,
    @SerializedName("primary_isbn13") val primaryIsbn13: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: String,
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String,
    @SerializedName("contributor") val contributor: String,
    @SerializedName("contributor_note") val contributorNote: String?,
    @SerializedName("book_image") val bookImage: String,
    @SerializedName("book_image_width") val bookImageWidth: Int,
    @SerializedName("book_image_height") val bookImageHeight: Int,
    @SerializedName("amazon_product_url") val amazonProductUrl: String,
    @SerializedName("age_group") val ageGroup: String?,
    @SerializedName("book_review_link") val bookReviewLink: String?,
    @SerializedName("first_chapter_link") val firstChapterLink: String?,
    @SerializedName("sunday_review_link") val sundayReviewLink: String?,
    @SerializedName("article_chapter_link") val articleChapterLink: String?,
    @SerializedName("isbns") val isbns: List<Isbn>,
    @SerializedName("buy_links") val buyLinks: List<BuyLink>,
    @SerializedName("book_uri") val bookUri: String
)

data class Isbn(
    @SerializedName("isbn10") val isbn10: String,
    @SerializedName("isbn13") val isbn13: String
)

data class BuyLink(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)