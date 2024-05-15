import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { API_ENDPOINT } from "../../const";

export interface Product {
    id: number;
    name: string;
    imageUrl: string;
    price: number;
    description: string;
}

export const useProductsManagement = () => {
    const [products, setProducts] = useState<Product[]>([]);
    const { categoryId } = useParams();

    useEffect(() => {
        const fetchProducts = async () => {
            const response = await axios.get(`${API_ENDPOINT}/products`, {
                params: { categoryId },
            });

            setProducts(response.data);
        };

        fetchProducts();
    }, [categoryId]);

    return { products };
};
