import axios from "axios";
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { API_ENDPOINT } from "../../const";
import { Category } from "../CategoriesManagement/useCategoriesManagement";
import Swal from "sweetalert2";

export const useEditProduct = () => {
    const { categoryId, productId } = useParams();
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

        const fetchProduct = async () => {
            const response = await axios.get(
                `${API_ENDPOINT}/products/${productId}`
            );
            const product = response.data;

            setProductName(product.name);
            setDescription(product.description);
            setImageURL(product.imageUrl);
            setPrice(product.price);
        };

        fetchCategories();
        fetchProduct();
    }, [productId]);

    const updateProduct = async () => {
        const updateProductInput = {
            categoryId: selectedCategory,
            name: productName,
            description: description,
            imageUrl: imageURL,
            price: price,
        };

        await axios
            .put(`${API_ENDPOINT}/products/${productId}`, updateProductInput)
            .then(() => {
                Swal.fire({
                    icon: "success",
                    title: "Success",
                    text: "Product updated successfully",
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
        updateProduct,
    };
};
