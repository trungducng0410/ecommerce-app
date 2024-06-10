import {
    Card,
    CardContent,
    Typography,
    Grid,
    Button,
    CardActions,
} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import { CartItem } from "../../pages/Cart/useCart";

export const ShoppingCartItem = ({
    item,
    deleteItem,
}: {
    item: CartItem;
    deleteItem: (id: number) => void;
}) => {
    return (
        <Card style={{ marginBottom: 10, marginTop: 10 }}>
            <CardContent>
                <Typography variant="body1" component="h2">
                    {item.product.name}
                </Typography>
                <Typography variant="subtitle2">
                    <hr />
                </Typography>
                <Grid container>
                    <Grid item xs={11} sm={11} md={11} lg={11}>
                        <Typography variant="body1" component="div">
                            Quantity
                        </Typography>
                    </Grid>
                    <Grid item xs={1} sm={1} md={1} lg={1}>
                        <Typography variant="h6" component="div">
                            {item.quantity}
                        </Typography>
                    </Grid>
                    <Grid item xs={11} sm={11} md={11} lg={11}>
                        <Typography
                            variant="body1"
                            component="div"
                            style={{ fontWeight: "bold" }}
                        >
                            Price
                        </Typography>
                    </Grid>
                    <Grid item xs={1} sm={1} md={1} lg={1}>
                        <Typography
                            variant="h6"
                            component="div"
                            color="secondary"
                        >
                            ${item.product.price * item.quantity}
                        </Typography>
                    </Grid>
                </Grid>
            </CardContent>
            <CardActions style={{ justifyContent: "flex-end" }}>
                <Button
                    size="small"
                    endIcon={<DeleteIcon />}
                    onClick={() => deleteItem(item.id)}
                >
                    Delete
                </Button>
            </CardActions>
        </Card>
    );
};
