import { useEffect, useState } from "react";
import { Category } from "../CategoriesManagement/useCategoriesManagement";
import { API_ENDPOINT } from "../../const";
import axios from "axios";

export const useHome = () => {
    const [categories, setCategories] = useState<Category[]>([]);

    useEffect(() => {
        const fetchCategories = async () => {
            const response = await axios.get(`${API_ENDPOINT}/categories`);
            setCategories(response.data);
        };

        fetchCategories();
    }, []);

    return { categories };
};
