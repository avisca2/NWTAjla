import { useNavigate } from "react-router-dom";
import doctor from "../assets/this3.png";
import Button from "../components/Button";

const AboutDoctor = () => {
  const navigate = useNavigate();
  return (
    <div className="flex justify-center items-center gap-8 p-6">
      <div className="w-[300px] h-[400px] p-6">
        <img
          src={doctor}
          alt="Doctor"
          className="w-full h-full object-cover rounded-lg"
        />
      </div>

      <div className="flex flex-col gap-4">
        <h1 className="text-2xl text-left font-bold mb-4">
          dr. Doktor Akupunktolog
        </h1>
        <p className="text-left w-[500px]">
          Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ipsam
          repellendus odit architecto optio distinctio molestias, corrupti atque
          labore accusamus corporis enim voluptas aliquam nisi delectus
          veritatis ducimus magni voluptate itaque.
        </p>
        <p className="text-left w-[500px]">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Porro modi
          facere aut nemo a consequatur, sit voluptatum vero explicabo maiores!
          Magni deleniti corrupti perferendis architecto numquam nesciunt,
          similique esse. Ratione.
        </p>
        <p className="text-left w-[500px]">
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Quibusdam
          provident maxime sit sapiente cupiditate corrupti, tempora id ipsam
          magni aliquam, placeat, eveniet ipsum. Magni, aperiam veniam at
          tempore ipsa corporis?
        </p>
        <div className="flex gap-8 justify-between items-center p-4">
          <Button content={"Ostavite recenziju za doktora"}
                  onClick={() => navigate("/giveReview/1")}
          />
          <Button
            onClick={() => navigate("/doctorReviews/1")}
            content={"Vidi te sve recenzije doctor"}
          />
        </div>
      </div>
    </div>
  );
};

export default AboutDoctor;
