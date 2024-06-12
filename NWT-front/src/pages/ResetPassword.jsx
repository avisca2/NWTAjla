import { Link, useNavigate, useSearchParams } from "react-router-dom";
import Button from "../components/Button";
import { useEffect, useState } from "react";
import API from "../../utils/API";

const ResetPassword = () => {
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams();
  const [formData, setFormData] = useState({
    newPassword: "",
  });

  useEffect(()=>{
    (async()=>{
      const token = searchParams.get("token");
      console.log(token);
      if(token === undefined)
          navigate("/login");
      else{
        await API.get("/reset-password/"+token)
                .then((res)=>{
                  if(!res.data.valide){
                    alert("token is expired");
                    navigate("/login");
                  }
                })
                .catch((err)=>{
                  alert(err.response.data.error);
                  navigate("/forget-password");
                });
      }
    })();
  },[]);
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const change = async (e) => {
    e.preventDefault();
    console.log(formData);
    await API.post(`/reset-password/activate/${searchParams.get("token")}`, formData)
      .then((res) => {
        if (res.status == 200){
          alert(res.data.message);
          navigate("/login");
        }
      })
      .catch((err) => {
        alert(err.message);
      });
  };
  return (
    <>
      <div className="flex justify-center items-center min-h-screen">
        <div>
          <h1 className="text-center text-2xl mb-8">Reset Password</h1>
          <div className="border-2 rounded-md flex justify-center items-center">
            <div className="flex flex-col gap-8 justify-center items-center p-16">
              <div className="flex gap-12 justify-between">
                <div className="border-2 bg-white border-black">
                  <span className="p-2">new password:</span>
                </div>
                <input name="newPassword" onChange={handleChange} type="password" className="p-2 border-2 border-black" />
              </div>

              <Button onClick={change} content={"change password"} />
              <Link to="/login">back to login page</Link>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ResetPassword;
