import { Link, useNavigate } from "react-router-dom";
import Button from "../components/Button";
import { useState } from "react";
import API from "../../utils/API";
import Cookies from "js-cookie";

const Login = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const login = async (e) => {
    e.preventDefault();
    await API.post(`auth/login`, formData)
      .then((res) => {
        if (res.status == 200) {
          Cookies.set("token", res.data.access_token);
          navigate("/treatments");
        }
      })
      .catch((err) => {
        alert(err.response.data.error);
      });
  };
  return (
    <>
      <div className="flex justify-center items-center min-h-screen">
        <div>
          <h1 className="text-center text-2xl mb-8">LogIn</h1>
          <div className="border-2 rounded-md flex justify-center items-center">
            <div className="flex flex-col gap-8 justify-center items-center p-16">
              <div className="flex gap-12 justify-between">
                <div className="border-2 bg-white border-black">
                  <span className="p-2">User Name:</span>
                </div>
                <input
                  name="username"
                  onChange={handleChange}
                  type="text"
                  className="p-2 border-2 border-black"
                />
              </div>

              <div className="flex gap-12 justify-between">
                <div className="border-2 border-black bg-white">
                  <span className="p-2">Password:</span>
                </div>
                <input
                  name="password"
                  onChange={handleChange}
                  type="password"
                  className="p-2 border-2 border-black"
                />
              </div>

              <Button onClick={login} content={"Login"} />

              <Link className="underline" to="/forget-password">
                Forgot password ?
              </Link>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
