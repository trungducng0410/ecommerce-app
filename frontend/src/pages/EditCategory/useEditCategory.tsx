import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { API_ENDPOINT } from "../../const";
import Swal from "sweetalert2";

export const useEditCategory = () => {
    const { categoryId } = useParams();
    const navigate = useNavigate();
    const [categoryName, setCategoryName] = useState("");
    const [description, setDescription] = useState("");
    const [imageURL, setImageURL] = useState("");

    useEffect(() => {
        const fetchCategory = async () => {
            const response = await axios.get(
                `${API_ENDPOINT}/categories/${categoryId}`
            );

            const data = response.data;
            setCategoryName(data.categoryName);
            setDescription(data.description);
            setImageURL(data.imageUrl);
        };

        fetchCategory();
    }, [categoryId]);

    const updateCategory = async () => {
        await axios
            .put(`${API_ENDPOINT}/categories/${categoryId}`, {
                categoryName: categoryName,
                description: description,
                imageUrl: imageURL,
            })
            .then(() => {
                Swal.fire({
                    icon: "success",
                    title: "Success",
                    text: "Category updated successfully",
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
        updateCategory,
    };
};
