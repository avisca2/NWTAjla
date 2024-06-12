import { useNavigate } from "react-router-dom";
import Button from "../components/Button";

const Home = () => {
  const navigate = useNavigate();
  return (
    <div className="min-h-screen flex justify-center items-center">
      <div className="flex flex-col gap-24 justify-center items-center">
        <div className="border-2">
          <h1 className="p-2 font-semibold text-gray-600 text-xl">
            Centar za bolij zivot
          </h1>
        </div>
        <div className="flex gap-24 justify-between items-center">
          <Button onClick={() => navigate("/login")} content={"Login"} />
          <Button onClick={() => navigate("/register")} content={"Sign In"} />
        </div>
      </div>
    </div>
  );
};

export default Home;
