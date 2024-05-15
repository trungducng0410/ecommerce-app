import { Button, Container, Stack, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useProductsManagement } from "./useProductsManagement";
import { ProductCard } from "../../components/ProductCard";

export const ProductsManagement = () => {
    const navigate = useNavigate();
    const { products } = useProductsManagement();

    return (
        <Container maxWidth="lg" sx={{ mt: 3 }}>
            <Stack
                direction="row"
                justifyContent="space-between"
                alignItems="center"
            >
                <Typography variant="h5">Our Pokémon</Typography>
                <Button
                    style={{ alignSelf: "right" }}
                    onClick={() => {
                        navigate("add");
                    }}
                    variant="contained"
                >
                    Add a new Card
                </Button>
            </Stack>
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
                        onEdit={() => {
                            navigate(`${product.id}`);
                        }}
                        showEditButton
                    />
                ))}
            </Stack>
        </Container>
    );
};
