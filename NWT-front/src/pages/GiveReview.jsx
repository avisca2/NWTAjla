import { useNavigate, useParams } from "react-router-dom";
import API2 from "../../utils/API2";
import { useEffect, useState, useCallback } from "react";
import Button from "../components/Button";
import API from "../../utils/API";

const GiveReview = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [patient, setPatient] = useState(null);
  const [formData, setFormData] = useState({
    doctorID: Number(id),
    patientName: "",
    comment: "",
    patientId: null,
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });

    if (e.target.name === "patientName") {
      debounceGetPatientByName(e.target.value);
    }
  };

  const getPatientByName = async (name) => {
    await API2.get(`Pacijent/GetByIme/${name}`)
      .then((res) => {
        setPatient(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const debounceGetPatientByName = useCallback(
    debounce((name) => {
      getPatientByName(name);
    }, 300),
    []
  );

  const giveReview = async () => {
    await API.post(`reviews`, formData)
      .then((res) => {
        console.log(res);
        navigate("/treatments");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    if (patient) {
      setFormData((prevFormData) => ({
        ...prevFormData,
        patientId: patient.id,
      }));
    }
  }, [patient]);

  return (
    <div className="flex flex-col gap-6 justify-center items-center">
      <h1 className="text-2xl font-semibold mb-8">Give Review</h1>
      <div className="flex flex-col gap-6">
        {[
          { label: "Pacijent Ime:", type: "text", name: "patientName" },
          { label: "comment :", type: "text", name: "comment" },
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
            />
          </div>
        ))}
        <Button
          color="text-white"
          bgColor="bg-blue-600"
          content={"give review"}
          onClick={giveReview}
        />
      </div>
    </div>
  );
};

function debounce(func, wait) {
  let timeout;
  return function (...args) {
    const context = this;
    clearTimeout(timeout);
    timeout = setTimeout(() => func.apply(context, args), wait);
  };
}

export default GiveReview;
