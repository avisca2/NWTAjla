import { useState } from "react";
import API from "../../utils/API";
import Button from "../components/Button";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    email: "",
    role: "",
    birthday: "",
    surname: "",
    phone: "",
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const register = async (e) => {
    e.preventDefault();
    await API.post(`auth/register`, formData)
      .then((res) => {
        if (res.status == 201) navigate("/login");
      })
      .catch((err) => {
        alert(err.response.data.error);
      });
  };

  return (
    <div className="flex justify-center items-center min-h-screen">
      <div>
        <h1 className="text-center text-2xl mb-8">Register</h1>
        <div className="border-2 rounded-md p-8">
          <div className="flex flex-col gap-6">
            {[
              { label: "Ime:", type: "text", name: "username" },
              { label: "Prezime:", type: "text", name: "surname" },
              { label: "Datum rođenja:", type: "date", name: "birthday" },
              { label: "Email:", type: "email", name: "email" },
              { label: "Broj telefona:", type: "text", name: "phone" },
              { label: "Role:", type: "text", name: "role", placeholder: "ex:ADMIN, DOCTOR, PATIENT"},
              { label: "Password:", type: "password", name: "password" },
            ].map((field, index) => (
              <div key={index} className="flex gap-4 items-center">
                <div className="border-2 border-black bg-white p-2 w-40">
                  <span className="block">{field.label}</span>
                </div>
                <input
                  type={field.type}
                  name={field.name}
                  onChange={handleChange}
                  className="flex-grow p-4 rounded-md border-2 border-black w-64"
                  placeholder={field.placeholder || ""}
                />
              </div>
            ))}
            <Button
              color="text-white"
              bgColor="bg-blue-600"
              content={"Register"}
              onClick={register}
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
