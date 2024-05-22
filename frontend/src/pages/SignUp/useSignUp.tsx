import axios from "axios";
import { API_ENDPOINT } from "../../const";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

export const useSignUp = () => {
    const navigate = useNavigate();

    const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const data = new FormData(event.currentTarget);
        const input = {
            firstName: data.get("firstName"),
            lastName: data.get("lastName"),
            email: data.get("email"),
            password: data.get("password"),
        };

        try {
            await axios.post(`${API_ENDPOINT}/signup`, input);
            Swal.fire({
                icon: "success",
                title: "Success",
                text: "User signup successful. Please Login",
            });

            navigate("/signin");
        } catch (error) {
            console.error(error);
            Swal.fire({
                icon: "error",
                title: "Error",
                text: "An error occurred. Please try again",
            });
            navigate(-1);
        }
    };

    return { handleSubmit };
};
