import axios from "../custom-axios/axios";

const LibraryService = {
    fetchCountries: () => {
        return axios.get("/countries")
    },
    fetchBooks: () => {
        return axios.get("/" || "books")
    },
}

export default LibraryService