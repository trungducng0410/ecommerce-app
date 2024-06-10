import {
    Button,
    Container,
    FormControl,
    Grid,
    IconButton,
    InputAdornment,
    InputLabel,
    OutlinedInput,
    Stack,
    Typography,
} from "@mui/material";
import { useProductDetail } from "./useProductDetail";
import AddShoppingCartIcon from "@mui/icons-material/AddShoppingCart";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";

export const ProductDetail = () => {
    const {
        product,
        category,
        quantity,
        onQuantityChange,
        addToWishList,
        addToCart,
    } = useProductDetail();

    return (
        <Container maxWidth="md" sx={{ mt: 3 }}>
            <Grid container spacing={5} columns={16}>
                <Grid item md={8}>
                    <img src={product?.imageUrl} width={350} />
                </Grid>
                <Grid item md={8}>
                    <Typography variant="h4">{product?.name}</Typography>
                    <Typography
                        variant="subtitle1"
                        sx={{ fontStyle: "italic" }}
                        gutterBottom
                    >
                        {category?.categoryName}
                    </Typography>

                    <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
                        Description:
                    </Typography>
                    <Typography variant="body1" gutterBottom>
                        {product?.description}
                    </Typography>
                    <Typography variant="subtitle1" gutterBottom>
                        <b>Price:</b> {product?.price}$ per item
                    </Typography>
                    <Stack direction="row" spacing={2} sx={{ my: 2 }}>
                        <FormControl
                            sx={{ m: 1, width: "25ch" }}
                            variant="outlined"
                        >
                            <InputLabel htmlFor="quantity-input">
                                Quantity
                            </InputLabel>
                            <OutlinedInput
                                id="quantity-input"
                                type="number"
                                label="Quantity"
                                endAdornment={
                                    <InputAdornment position="end">
                                        items
                                    </InputAdornment>
                                }
                                value={quantity}
                                onChange={(e) =>
                                    onQuantityChange(Number(e.target.value))
                                }
                            />
                        </FormControl>
                        <IconButton
                            color="primary"
                            aria-label="add to shopping cart"
                            size="large"
                            onClick={addToCart}
                        >
                            <AddShoppingCartIcon />
                        </IconButton>
                    </Stack>

                    <Button
                        startIcon={<FavoriteBorderIcon />}
                        variant="contained"
                        onClick={addToWishList}
                    >
                        Add to wish list
                    </Button>
                </Grid>
            </Grid>
        </Container>
    );
};
