import { useAddCategory } from "./useAddCategory";
import { CategoryForm } from "../../components/CategoryForm";

export const AddCategory = () => {
    const {
        categoryName,
        description,
        imageURL,
        setCategoryName,
        setDescription,
        setImageURL,
        addCategory,
    } = useAddCategory();

    return (
        <CategoryForm
            formName="Add Category"
            categoryName={categoryName}
            description={description}
            imageURL={imageURL}
            onChangeCategoryName={setCategoryName}
            onChangeDescription={setDescription}
            onChangeImageURL={setImageURL}
            onSubmit={addCategory}
        />
    );
};
