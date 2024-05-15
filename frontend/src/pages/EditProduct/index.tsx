import { ProductForm } from "../../components/ProductForm";
import { useEditProduct } from "./useEditProduct";

export const EditProduct = () => {
    const {
        productName,
        description,
        imageURL,
        price,
        categories,
        selectedCategory,
        setProductName,
        setDescription,
        setImageURL,
        setSelectedCategory,
        setPrice,
        updateProduct,
    } = useEditProduct();

    return (
        <ProductForm
            formName={"Edit Product"}
            productName={productName}
            description={description}
            imageURL={imageURL}
            price={price}
            categories={categories}
            selectedCategory={selectedCategory}
            onChangeProductName={setProductName}
            onChangeDescription={setDescription}
            onChangeImageURL={setImageURL}
            onChangeCategory={setSelectedCategory}
            onChangePrice={setPrice}
            onSubmit={updateProduct}
        />
    );
};
