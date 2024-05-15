import { Container, Stack, Typography } from "@mui/material";
import { useHome } from "./useHome";
import { CategoryCard } from "../../components/CategoryCard";
import { useNavigate } from "react-router-dom";

export const Home = () => {
    const { categories } = useHome();
    const navigate = useNavigate();

    return (
        <>
            <img src="/public/banner.avif" height={300} width="100%" />
            <Container maxWidth="xl" sx={{ mt: 2 }}>
                <Typography variant="h5">Our categories</Typography>
                <Stack
                    style={{ marginTop: 20 }}
                    spacing={3}
                    direction="row"
                    useFlexGap
                    flexWrap="wrap"
                >
                    {categories.map((category) => (
                        <CategoryCard
                            key={category.id}
                            name={category.categoryName}
                            description={category.description}
                            imageUrl={category.imageUrl}
                            onViewCards={() => {
                                navigate(`categories/${category.id}/products`);
                            }}
                        />
                    ))}
                </Stack>
            </Container>
        </>
    );
};
