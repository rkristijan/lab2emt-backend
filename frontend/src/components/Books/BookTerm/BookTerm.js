import React, {useState} from "react";
import {Link} from "react-router-dom";

const BookTerm = (props) => {

    const [count,setCount] = useState(props.term.availableCopies);
    let markAsTaken= () => setCount(count-1);

    if (count<=1){
        markAsTaken = () => setCount(1);
    }

    return(
        <tr>
            <td scope={"col"}>{props.term.name}</td>
            <td scope={"col"}>{props.term.category}</td>
            <td scope={"col"}>{props.term.author.name + " " + props.term.author.surname}</td>
            <td scope={"col"}>{count}</td>
            <td scope={"col"} className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                onClick={() => props.onDelete(props.term.id)}>Delete</a>
                <Link className={"btn btn-info ms-2"} onClick={() => props.onEdit(props.term.id)} to={`/books/edit/${props.term.id}`}>Edit</Link>
                <a title={"Mark as Taken"} className={"btn btn-success ms-2"} onClick={markAsTaken}>Mark as Taken</a>
            </td>
        </tr>
    );
}

export default BookTerm;