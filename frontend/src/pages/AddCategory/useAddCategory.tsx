import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import { API_ENDPOINT } from "../../const";

export const useAddCategory = () => {
    const navigate = useNavigate();
    const [categoryName, setCategoryName] = useState("");
    const [description, setDescription] = useState("");
    const [imageURL, setImageURL] = useState("");

    const addCategory = async () => {
        await axios
            .post(`${API_ENDPOINT}/categories`, {
                categoryName: categoryName,
                description: description,
                imageUrl: imageURL,
            })
            .then(() => {
                Swal.fire({
                    icon: "success",
                    title: "Success",
                    text: "Category added successfully",
                });

                navigate(-1);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    return {
        categoryName,
        setCategoryName,
        description,
        setDescription,
        imageURL,
        setImageURL,
        addCategory,
    };
};
