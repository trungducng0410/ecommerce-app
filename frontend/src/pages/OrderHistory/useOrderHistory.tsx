import { useEffect, useState } from "react";
import { useAuth } from "../../contexts/Auth/useAuth";
import axios from "axios";
import { API_ENDPOINT } from "../../const";

export interface Order {
    id: number;
    createdAt: Date;
    totalPrice: number;
    sessionId: string;
    orderItems: OrderItem[];
}

export interface OrderItem {
    id: number;
    quantity: number;
    price: number;
    createdAt: Date;
    product: Product;
}

export interface Product {
    id: number;
    name: string;
    imageUrl: string;
    price: number;
    description: string;
}

export const useOrderHistory = () => {
    const { token } = useAuth();
    const [orders, setOrders] = useState<Order[]>([]);

    useEffect(() => {
        const fetchOrderHistory = async () => {
            const orders = await axios.get(`${API_ENDPOINT}/orders`, {
                headers: {
                    Authorization: token,
                },
            });

            setOrders(orders.data);
        };

        fetchOrderHistory();
    }, []);

    return { orders };
};
