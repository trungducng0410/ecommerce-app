import { useEditCategory } from "./useEditCategory";
import { CategoryForm } from "../../components/CategoryForm";

export const EditCategory = () => {
    const {
        categoryName,
        description,
        imageURL,
        setCategoryName,
        setDescription,
        setImageURL,
        updateCategory,
    } = useEditCategory();

    return (
        <CategoryForm
            formName="Edit Category"
            categoryName={categoryName}
            description={description}
            imageURL={imageURL}
            onChangeCategoryName={setCategoryName}
            onChangeDescription={setDescription}
            onChangeImageURL={setImageURL}
            onSubmit={updateCategory}
        />
    );
};
