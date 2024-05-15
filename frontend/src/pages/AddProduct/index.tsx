import { ProductForm } from "../../components/ProductForm";
import { useAddProduct } from "./useAddProduct";

export const AddProduct = () => {
    const {
        selectedCategory,
        categories,
        productName,
        description,
        imageURL,
        price,
        setSelectedCategory,
        setProductName,
        setDescription,
        setImageURL,
        setPrice,
        addProduct,
    } = useAddProduct();

    return (
        <ProductForm
            formName="Add a new Product"
            productName={productName}
            description={description}
            imageURL={imageURL}
            price={price}
            onChangeProductName={setProductName}
            onChangeDescription={setDescription}
            onChangeImageURL={setImageURL}
            onSubmit={addProduct}
            categories={categories}
            selectedCategory={selectedCategory}
            onChangeCategory={setSelectedCategory}
            onChangePrice={setPrice}
        />
    );
};
