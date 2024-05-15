import { useEffect, useState } from "react";
import { Product } from "../ProductsManagement/useProductsManagement";
import { Category } from "../CategoriesManagement/useCategoriesManagement";
import { useParams } from "react-router-dom";
import axios from "axios";
import { API_ENDPOINT } from "../../const";

export const useProductDetail = () => {
    const [product, setProduct] = useState<Product | null>(null);
    const [category, setCategory] = useState<Category | null>(null);
    const [quantity, setQuantity] = useState(1);
    const { productId, categoryId } = useParams();

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

    return { product, category, quantity, onQuantityChange };
};
