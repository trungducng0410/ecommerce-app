import { Container, Typography, Stack } from "@mui/material";
import { ProductCard } from "../../components/ProductCard";
import { useWishList } from "./useWishList";

export const WishList = () => {
    const { wishList } = useWishList();

    return (
        <Container sx={{ mt: 3 }}>
            <Typography variant="h5">My WishList</Typography>
            <Stack
                style={{ marginTop: 20 }}
                spacing={5}
                direction="row"
                useFlexGap
                flexWrap="wrap"
            >
                {wishList.map((product) => (
                    <ProductCard
                        key={product.id}
                        imageUrl={product.imageUrl}
                        name={product.name}
                        price={product.price}
                        description={product.description}
                    />
                ))}
            </Stack>
        </Container>
    );
};
