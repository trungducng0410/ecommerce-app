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
    const { token, setSession } = useAuth();
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

    const confirmOrder = async () => {
        const orderInput = cartItems.map((item) => ({
            productId: item.product.id,
            quantity: item.quantity,
            price: item.product.price,
            productName: item.product.name,
        }));

        try {
            const response = await axios.post(
                `${API_ENDPOINT}/orders/create-checkout-session`,
                orderInput
            );

            setSession(response.data.sessionId);
            window.location.href = response.data.url;
        } catch (error) {
            console.log(error);
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "An error occurred while confirming the order",
            });
        }
    };

    return {
        cartItems,
        total,
        itemsCount: cartItems.length,
        deleteItem,
        confirmOrder,
    };
};
