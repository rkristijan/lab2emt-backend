import React from "react";
import {useNavigate} from 'react-router-dom';

const BookEdit = (props) => {

    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "",
        author: 1,
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
        const bookName = formData.name !== "" ? formData.name : props.book.name;
        const bookCategory = formData.category !== "" ? formData.category : props.book.category;
        const bookAuthor = formData.author !== 0 ? formData.author : props.book.author.id;
        const bookAvailableCopies = formData.availableCopies !== 0 ? formData.availableCopies : props.book.availableCopies;

        props.onEditBook(props.book.id,bookName,bookCategory,bookAuthor,bookAvailableCopies);
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
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="category">Category</label>
                        <input type="text"
                               className="form-control"
                               id="category"
                               name="category"
                               placeholder={props.book.category}
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) =>{
                                if(props.book.author !== undefined && props.book.author.id === term.id)
                                    return <option selected={props.book.author.id} value={term.id} key={term.id}>{term.name + " " + term.surname}</option>
                                else
                                    return <option value={term.id} key={term.id}>{term.name + " " + term.surname}</option>
                                }
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label htmlFor="availableCopies">Available Copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               placeholder={props.book.availableCopies}
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

export default BookEdit;
