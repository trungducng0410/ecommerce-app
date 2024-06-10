import { useEffect, useState } from "react";
import { Product } from "../ProductsManagement/useProductsManagement";
import { Category } from "../CategoriesManagement/useCategoriesManagement";
import { useParams } from "react-router-dom";
import axios from "axios";
import { API_ENDPOINT } from "../../const";
import { useAuth } from "../../contexts/Auth/useAuth";
import Swal from "sweetalert2";

export const useProductDetail = () => {
    const [product, setProduct] = useState<Product | null>(null);
    const [category, setCategory] = useState<Category | null>(null);
    const [quantity, setQuantity] = useState(1);
    const { productId, categoryId } = useParams();
    const { token } = useAuth();

    useEffect(() => {
        const fetchProduct = async () => {
            const response = await axios.get(
                `${API_ENDPOINT}/products/${productId}`
            );
            const product = response.data;

            setProduct(product);
        };

        const fetchCategory = async () => {
            const response = await axios.get(
                `${API_ENDPOINT}/categories/${categoryId}`
            );
            const category = response.data;

            setCategory(category);
        };

        fetchProduct();
        fetchCategory();
    }, [categoryId, productId]);

    const onQuantityChange = (value: number) => {
        if (value < 1) {
            setQuantity(1);
        } else {
            setQuantity(value);
        }
    };

    const addToWishList = async () => {
        try {
            await axios.post(
                `${API_ENDPOINT}/wishlists`,
                {
                    productId: productId,
                },
                {
                    headers: {
                        Authorization: token,
                    },
                }
            );

            Swal.fire({
                icon: "success",
                title: "Success",
                text: "Product added to wishlist",
            });
        } catch (error) {
            console.error(error);
            Swal.fire({
                icon: "error",
                title: "Error",
                text: `${error.response.data.message}`,
            });
        }
    };

    const addToCart = async () => {
        try {
            await axios.post(
                `${API_ENDPOINT}/carts`,
                {
                    productId: productId,
                    quantity: quantity,
                },
                {
                    headers: {
                        Authorization: token,
                    },
                }
            );

            Swal.fire({
                icon: "success",
                title: "Success",
                text: "Product added to cart",
            });
        } catch (error) {
            console.error(error);
            Swal.fire({
                icon: "error",
                title: "Error",
                text: `${error.response.data.message}`,
            });
        }
    };

    return {
        product,
        category,
        quantity,
        onQuantityChange,
        addToWishList,
        addToCart,
    };
};
