import { Link, useNavigate } from "react-router-dom";
import Button from "../components/Button";
import { useState } from "react";
import API from "../../utils/API";

const ForgetPassword = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    email: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const forget = async (e) => {
    e.preventDefault();
    await API.post(`/reset-password/forgot`, formData)
      .then((res) => {
        if (res.status == 200) alert("activation link was sent")
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
                  <span className="p-2">Email:</span>
                </div>
                <input name="email" onChange={handleChange} type="text" className="p-2 border-2 border-black" />
              </div>

              <Button onClick={forget} content={"send activation link"} />
              <Link to="/login">back to login page</Link>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ForgetPassword;
