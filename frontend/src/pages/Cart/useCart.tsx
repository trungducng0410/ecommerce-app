import axios from "axios";
import { useEffect, useState } from "react";
import { API_ENDPOINT } from "../../const";
import { useAuth } from "../../contexts/Auth/useAuth";
import { Product } from "../ProductsManagement/useProductsManagement";
import Swal from "sweetalert2";

export interface CartItem {
    id: number;
    quantity: number;
    product: Product;
}

export const useCart = () => {
    const { token } = useAuth();
    const [cartItems, setCartItems] = useState<CartItem[]>([]);
    const [total, setTotal] = useState(0);

    const fetchCartData = async () => {
        const cartData = await axios.get(`${API_ENDPOINT}/carts`, {
            headers: {
                Authorization: token,
            },
        });

        setCartItems(cartData.data.cartItems);
        setTotal(cartData.data.totalCost);
    };

    useEffect(() => {
        fetchCartData();
    }, [token]);

    const deleteItem = async (itemId: number) => {
        try {
            await axios.delete(`${API_ENDPOINT}/carts/${itemId}`, {
                headers: {
                    Authorization: token,
                },
            });

            await fetchCartData();

            Swal.fire({
                icon: "success",
                title: "Item removed from cart",
                timer: 1500,
            });
        } catch (error) {
            console.log(error);
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "An error occurred while deleting the item",
            });
        }
    };

    return { cartItems, total, itemsCount: cartItems.length, deleteItem };
};
