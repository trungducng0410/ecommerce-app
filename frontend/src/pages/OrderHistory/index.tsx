import { Container, Divider, List, Typography } from "@mui/material";
import { useOrderHistory } from "./useOrderHistory";
import { OrderItem } from "../../components/OrderItem";

export const OrderHistory = () => {
    const { orders } = useOrderHistory();

    return (
        <Container sx={{ mt: 3 }}>
            <Typography variant="h5">Order History</Typography>
            <List>
                {orders.map((order) => (
                    <>
                        <OrderItem key={order.id} order={order} />
                        <Divider component="li" />
                    </>
                ))}
            </List>
        </Container>
    );
};
