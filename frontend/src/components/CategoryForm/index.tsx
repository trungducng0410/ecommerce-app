import { Container, Typography, Stack, TextField, Button } from "@mui/material";
import SendIcon from "@mui/icons-material/Send";

interface CategoryFormProps {
    formName: string;
    categoryName: string;
    description: string;
    imageURL: string;
    onChangeCategoryName: (categoryName: string) => void;
    onChangeDescription: (description: string) => void;
    onChangeImageURL: (imageURL: string) => void;
    onSubmit: () => void;
}

export const CategoryForm = ({
    formName,
    categoryName,
    description,
    imageURL,
    onChangeCategoryName,
    onChangeDescription,
    onChangeImageURL,
    onSubmit,
}: CategoryFormProps) => {
    return (
        <Container maxWidth="md" sx={{ mt: 3 }}>
            <Typography variant="h5">{formName}</Typography>
            <Stack style={{ marginTop: 20, marginBottom: 20 }} spacing={2}>
                <TextField
                    value={categoryName}
                    onChange={(e) => onChangeCategoryName(e.target.value)}
                    required
                    label="Category Name"
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
