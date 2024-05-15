import { Container, Stack, Typography } from "@mui/material";
import { useProductList } from "./useProductList";
import { ProductCard } from "../../components/ProductCard";
import { useNavigate } from "react-router-dom";

export const ProductList = () => {
    const { products } = useProductList();
    const navigate = useNavigate();

    return (
        <Container sx={{ mt: 3 }}>
            <Typography variant="h5">Our Pokémon</Typography>
            <Stack
                style={{ marginTop: 20 }}
                spacing={5}
                direction="row"
                useFlexGap
                flexWrap="wrap"
            >
                {products.map((product) => (
                    <ProductCard
                        imageUrl={product.imageUrl}
                        name={product.name}
                        price={product.price}
                        description={product.description}
                        showDetail
                        onDetail={() => {
                            navigate(`${product.id}`);
                        }}
                    />
                ))}
            </Stack>
        </Container>
    );
};
