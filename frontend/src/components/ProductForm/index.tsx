import {
    Container,
    Typography,
    Stack,
    TextField,
    FormControl,
    InputLabel,
    OutlinedInput,
    InputAdornment,
    Button,
    MenuItem,
    Select,
} from "@mui/material";
import SendIcon from "@mui/icons-material/Send";
import { Category } from "../../pages/CategoriesManagement/useCategoriesManagement";

interface ProductFormProps {
    formName: string;
    productName: string;
    description: string;
    imageURL: string;
    price: number;
    categories: Category[];
    selectedCategory: number;
    onChangeProductName: (productName: string) => void;
    onChangeDescription: (description: string) => void;
    onChangeImageURL: (imageURL: string) => void;
    onChangeCategory: (category: number) => void;
    onChangePrice: (price: number) => void;
    onSubmit: () => void;
}

export const ProductForm = ({
    formName,
    productName,
    description,
    imageURL,
    price,
    categories,
    selectedCategory,
    onChangeProductName,
    onChangeDescription,
    onChangeImageURL,
    onChangeCategory,
    onChangePrice,
    onSubmit,
}: ProductFormProps) => {
    return (
        <Container maxWidth="md" sx={{ mt: 3 }}>
            <Typography variant="h5">{formName}</Typography>
            <Stack style={{ marginTop: 20, marginBottom: 20 }} spacing={2}>
                <FormControl fullWidth>
                    <InputLabel id="category-select">Category</InputLabel>
                    <Select
                        labelId="category-select"
                        value={selectedCategory}
                        label="Category"
                        onChange={(e) =>
                            onChangeCategory(Number(e.target.value))
                        }
                        required
                    >
                        {categories.map((category) => (
                            <MenuItem value={category.id} key={category.id}>
                                {category.categoryName}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <TextField
                    value={productName}
                    onChange={(e) => onChangeProductName(e.target.value)}
                    required
                    label="Name"
                    variant="outlined"
                />
                <TextField
                    value={description}
                    onChange={(e) => onChangeDescription(e.target.value)}
                    required
                    label="Description"
                    variant="outlined"
                />
                <TextField
                    value={imageURL}
                    onChange={(e) => onChangeImageURL(e.target.value)}
                    required
                    label="ImageURL"
                    variant="outlined"
                />
                <FormControl fullWidth>
                    <InputLabel htmlFor="card-price">Price</InputLabel>
                    <OutlinedInput
                        id="card-price"
                        startAdornment={
                            <InputAdornment position="start">$</InputAdornment>
                        }
                        label="Price"
                        value={price}
                        required
                        onChange={(e) => onChangePrice(Number(e.target.value))}
                    />
                </FormControl>
            </Stack>
            <Button
                onClick={onSubmit}
                variant="contained"
                endIcon={<SendIcon />}
            >
                Submit
            </Button>
        </Container>
    );
};
