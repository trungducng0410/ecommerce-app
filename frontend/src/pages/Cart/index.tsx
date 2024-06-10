import { Container, Grid, Typography } from "@mui/material";
import { ShoppingCartItem } from "../../components/ShoppingCartItem";
import { OrderSummary } from "../../components/OrderSummary";
import { useCart } from "./useCart";

export const Cart = () => {
    const { total, itemsCount, cartItems, deleteItem } = useCart();

    return (
        <Container sx={{ mt: 3 }}>
            <Typography variant="h5">Shopping Cart</Typography>
            <Grid container spacing={3}>
                <Grid item xs={12} sm={6} md={7} lg={7}>
                    <Grid container>
                        <Grid item xs>
                            {cartItems.map((item) => (
                                <ShoppingCartItem
                                    key={item.id}
                                    item={item}
                                    deleteItem={deleteItem}
                                />
                            ))}
                        </Grid>
                    </Grid>
                </Grid>
                <Grid item xs={12} sm={6} md={5} lg={5}>
                    <OrderSummary total={total} itemsCount={itemsCount} />
                </Grid>
            </Grid>
        </Container>
    );
};
