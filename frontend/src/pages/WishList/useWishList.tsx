import { useEffect, useState } from "react";
import { Product } from "../ProductsManagement/useProductsManagement";
import axios from "axios";
import { API_ENDPOINT } from "../../const";
import { useAuth } from "../../contexts/Auth/useAuth";

export const useWishList = () => {
    const [wishList, setWishList] = useState<Product[]>([]);
    const { token } = useAuth();

    useEffect(() => {
        const fetchWishList = async () => {
            const response = await axios.get(`${API_ENDPOINT}/wishlists`, {
                headers: {
                    Authorization: token,
                },
            });

            setWishList(response.data);
        };

        fetchWishList();
    }, [token]);

    return { wishList };
};
