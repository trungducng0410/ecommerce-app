import { useEffect, useState } from "react";
import { Product } from "../ProductsManagement/useProductsManagement";
import axios from "axios";
import { API_ENDPOINT } from "../../const";
import { useParams } from "react-router-dom";

export const useProductList = () => {
    const { categoryId } = useParams();
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        const fetchProducts = async () => {
            const response = await axios.get(`${API_ENDPOINT}/products`, {
                params: { categoryId },
            });

            setProducts(response.data);
        };

        fetchProducts();
    });

    return { products };
};
