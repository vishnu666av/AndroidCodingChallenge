# Book App

Demonstrated the Model-View-Presenter (MVP) architecture along with Retrofit for networking,
Dagger for dependency injection, Glide for image loading, and data binding.

## Project Structure

The project is organized into the following main layers:

- **Data Layer**
    - Repositories and API interactions.
    - Retrofit for API calls.
    - Error handling and result processing.

- **Domain Layer**
    - Use cases to handle business logic, decoupled from UI or data source implementations.

- **Presentation Layer**
    - Activities, presenters, and views for UI.
    - Adapters for RecyclerView.
    - Loading indicators and error messages.

## Components and Architecture

### Components

- **BooksRepository Interface**
    - Defines the contract for the repository handling API calls.

- **BooksRepositoryImpl Implementation**
    - Implements `BooksRepository`.
    - Manages API calls and error handling for `getBooksList`.

- **GetBooksUseCase**
    - Executes the `getBooksList` call from `BooksRepository`.
    - Returns `Result<BookResults?>` for handling success or failure.

- **BooksPresenter**
    - Orchestrates the flow between `BooksView` and `GetBooksUseCase`.
    - Calls view methods to update UI based on API call results.

- **BooksView Interface**
    - Defines the contract for views to implement, e.g., `showLoading`, `displayBooks`, and `showError`.

- **BooksAdapter**
    - Manages book list display in a RecyclerView.

- **MyApplication**
    - Initializes `AppComponent` for Dagger dependency injection.

- **Constants**
    - Holds API keys and other constants.

### Architecture Flow

1. **View Layer (Activity)**
    - `BooksListActivity` initializes the UI and presenter, and manages swipe-to-refresh behavior.
    - Handles user interactions and updates based on presenter calls.

2. **Presentation Layer (Presenter)**
    - `BooksPresenter`:
        - Starts by calling `showLoading(true)`.
        - Asynchronously invokes `GetBooksUseCase` to fetch books.
        - Based on the result:
            - Calls `displayBooks` if successful.
            - Calls `showError` if it encounters an error.
        - Hides the loading indicator after receiving a result.

3. **Domain Layer (Use Case)**
    - `GetBooksUseCase` interacts with `BooksRepository` to execute the API call.
    - Wraps the repository result in a `Result` type to signal success or failure.

4. **Data Layer (Repository)**
    - `BooksRepositoryImpl` calls `ApiService` to fetch data.
    - On receiving a response:
        - Returns `Result.success(data)` if successful.
        - Returns `Result.failure(exception)` on failure.
### Flow Diagram

BooksListActivity
         |
         | --> Presenter.loadBooks()
         |
         ↓
BooksPresenter
         |
         | --> GetBooksUseCase()
         |
         ↓
GetBooksUseCase
         |
         | --> BooksRepository.getBooksList()
         |
         ↓
BooksRepositoryImpl
         |
         | --> ApiService.getBooksList()
         |
         ↓
Retrofit API Call
