import doctor from "../assets/this3.png";
import { useParams } from "react-router-dom";
import API from "../../utils/API";
import { useEffect, useState } from "react";

const DoctorReviews = () => {
  const { id } = useParams();
  const [reviews, setReviews] = useState([]);
  const getDoctorReviews = async () => {
    await API.get(`reviews/doctor/${id}`)
      .then((res) => {
        console.log(res);
        setReviews(res.data);
      })
      .catch((err) => {
        alert(err.response.data.error);
      });
  };

  useEffect(() => {
    getDoctorReviews();
  }, []);
  return (
    <>
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

          <div className="flex flex-col items-center gap-4 bg-white h-fit w-[500px] p-4">
            <h1 className="text-center">Recenzije</h1>
            {reviews &&
              reviews.map((review) => (
                <div className="flex gap-2" key={review.id}>
                  <p>{review.pacijent.ime}</p>
                  <p>{review.pacijent.prezime} : </p>
                  <p>{review.comment}</p>
                </div>
              ))}
          </div>
        </div>
      </div>
    </>
  );
};

export default DoctorReviews;
