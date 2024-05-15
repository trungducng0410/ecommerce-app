import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { API_ENDPOINT } from "../../const";
import { Category } from "../CategoriesManagement/useCategoriesManagement";
import Swal from "sweetalert2";

export const useAddProduct = () => {
    const { categoryId } = useParams();
    const navigate = useNavigate();
    const [selectedCategory, setSelectedCategory] = useState(
        Number(categoryId)
    );
    const [categories, setCategories] = useState<Category[]>([]);
    const [productName, setProductName] = useState("");
    const [description, setDescription] = useState("");
    const [imageURL, setImageURL] = useState("");
    const [price, setPrice] = useState(0);

    useEffect(() => {
        const fetchCategories = async () => {
            const response = await axios.get(`${API_ENDPOINT}/categories`);
            setCategories(response.data);
        };

        fetchCategories();
    }, []);

    const addProduct = async () => {
        const addProductInput = {
            categoryId: selectedCategory,
            name: productName,
            description: description,
            imageUrl: imageURL,
            price: price,
        };

        await axios
            .post(`${API_ENDPOINT}/products`, addProductInput)
            .then(() => {
                Swal.fire({
                    icon: "success",
                    title: "Success",
                    text: "Product added successfully",
                });

                navigate(-1);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    return {
        selectedCategory,
        setSelectedCategory,
        categories,
        productName,
        setProductName,
        description,
        setDescription,
        imageURL,
        setImageURL,
        price,
        setPrice,
        addProduct,
    };
};
