import { Button, Container, Stack, Typography } from "@mui/material";
import { CategoryCard } from "../../components/CategoryCard";
import { useCategoriesManagement } from "./useCategoriesManagement";
import { useNavigate } from "react-router-dom";

export const CategoriesManagement = () => {
    const navigate = useNavigate();
    const { categories } = useCategoriesManagement();

    return (
        <Container maxWidth="lg" sx={{ mt: 3 }}>
            <Stack
                direction="row"
                justifyContent="space-between"
                alignItems="center"
            >
                <Typography variant="h5">Our Categories</Typography>
                <Button
                    style={{ alignSelf: "right" }}
                    onClick={() => {
                        navigate("add");
                    }}
                    variant="contained"
                >
                    Add a new Category
                </Button>
            </Stack>
            <Stack
                style={{ marginTop: 20 }}
                spacing={5}
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
                        onEdit={() => {
                            navigate(`${category.id}`);
                        }}
                        onViewCards={() => {
                            navigate(`${category.id}/products`);
                        }}
                        showEditButton
                    />
                ))}
            </Stack>
        </Container>
    );
};
