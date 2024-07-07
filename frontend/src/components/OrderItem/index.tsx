import { Grid } from "@mui/material";
import { Order } from "../../pages/OrderHistory/useOrderHistory";

export const OrderItem = ({ order }: { order: Order }) => {
    return (
        <>
            <h3>Order number #{order.id}</h3>
            <Grid container spacing={2}>
                {order.orderItems.map((item) => (
                    <>
                        <Grid item xs={2}>
                            <img
                                src={item.product.imageUrl}
                                alt={item.product.name}
                                width={100}
                            />
                        </Grid>
                        <Grid item xs={10}>
                            <h4>{item.product.name}</h4>
                            <p>${item.price} per unit</p>
                            <p>Quantity: {item.quantity}</p>
                            <p>Total: ${item.price * item.quantity}</p>
                        </Grid>
                    </>
                ))}

                <Grid item xs={12}>
                    <h4>Total cost: ${order.totalPrice}</h4>
                    <p>
                        Order placed on:{" "}
                        {new Date(order.createdAt).toDateString()}
                    </p>
                </Grid>
            </Grid>
        </>
    );
};
