import './App.css';
import React from "react";
import {Component} from "react";
import LibraryService from "../../repository/libraryRepository";
import Countries from "../Countries/Countries";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Books from "../Books/BookList/Books";
import Authors from "../Authors/authors";
import {Navigate} from "react-router-dom"
import Header from "../Header/header";
import AddBook from "../Books/AddBook/addBook";
import BookEdit from "../Books/BookEdit/bookEdit";
class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            countries: [],
            books: [],
            authors: [],
            selectedBook: {},
        }
    }

    render() {
        return (
            <BrowserRouter>
                <Header/>
                <main>
                    <div className={"container"}>
                        <Routes>
                            <Route path={"/"} element={<Books books={this.state.books} onDelete={this.deleteBook}/>}/>
                            <Route path={"/books/edit/:id"}
                                   element={<BookEdit authors={this.state.authors} onEditBook={this.editBook}
                                                      book={this.state.selectedBook}/>}/>
                            <Route path={"/books/add"}
                                   element={<AddBook authors={this.state.authors} onAddBook={this.addBook}/>}/>
                            <Route path={"/books"}
                                   element={<Books books={this.state.books} onDelete={this.deleteBook} onEdit={this.getBook}/>}/>
                            <Route path={"/countries"} element={<Countries countries={this.state.countries}/>}/>
                            <Route path={"/authors"} element={<Authors authors={this.state.authors}/>}/>
                            <Route path={"*"} element={<Navigate to={"/books"}/>}/>
                        </Routes>
                    </div>
                </main>
            </BrowserRouter>
        );
    }

    componentDidMount() {
        this.loadCountries();
        this.loadBooks();
        this.loadAuthors();
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data,
                })
            });
    }

    loadCountries = () => {
        LibraryService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data,
                })
            });
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            })
    }

    addBook = (name, category, author, availableCopies) => {
        LibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            });
    }

    editBook = (id, name, category, author, availableCopies) => {
        LibraryService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }
}

export default App;
