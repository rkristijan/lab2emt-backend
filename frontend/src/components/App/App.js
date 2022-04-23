import logo from '../../logo.svg';
import './App.css';
import React from "react";
import {Component} from "react";
import LibraryService from "../../repository/libraryRepository";
import Countries from "../Countries/Countries";
import Books from "../Books/Books";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            countries: [],
            books: [],
        }
    }

    render() {
        return (
            <div>
                <Books books={this.state.books}/>
            </div>
        );
    }

    componentDidMount() {
        this.loadCountries();
        this.loadBooks();
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
}

export default App;
