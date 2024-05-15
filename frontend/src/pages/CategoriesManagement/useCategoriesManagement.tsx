import axios from "axios";
import { useEffect, useState } from "react";
import { API_ENDPOINT } from "../../const";

export interface Category {
    id: number;
    categoryName: string;
    description: string;
    imageUrl: string;
}

export const useCategoriesManagement = () => {
    const [categories, setCategories] = useState<Category[]>([]);

    useEffect(() => {
        const fetchCategories = async () => {
            const response = await axios.get(`${API_ENDPOINT}/categories`);
            const data = response.data;
            setCategories(data);
        };

        fetchCategories();
    }, []);

    return { categories };
};
