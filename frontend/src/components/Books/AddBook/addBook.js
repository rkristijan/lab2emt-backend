import React from "react";
import {useNavigate} from 'react-router-dom';

const AddBook = (props) => {

    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "",
        author: 0,
        availableCopies: 0,
    });

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        const bookName = formData.name;
        const bookCategory = formData.category;
        const bookAuthor = formData.author;
        const bookAvailableCopies = formData.availableCopies;

        props.onAddBook(bookName,bookCategory,bookAuthor,bookAvailableCopies);
        history("/books");
    }

    return (
        <div className="row mt-5">
            <div className="col-md-5 ">
                <form onSubmit={onFormSubmit} >
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter book name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="category">Category</label>
                        <input type="text"
                               className="form-control"
                               id="category"
                               name="category"
                               placeholder="Category"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) =>
                                <option value={term.id} key={term.id}>{term.name + " " + term.surname}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder="Available Copies"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary mt-2">Submit</button>
                </form>
            </div>
        </div>
    );
}

export default AddBook;