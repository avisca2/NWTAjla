import Calendar from "react-calendar";
import Button from "../components/Button";
import { useState } from "react";

const Booking = () => {
  const [value, onChange] = useState(new Date());
  return (
    <div className="flex flex-col justify-start items-center gap-8">
      <div className="flex flex-col justify-start items-center">
        <h1 className="text-2xl font-bold">Akupunktura sa:</h1>
        <h2 className="text-2xl font-bold">dr. Doktor Akupunktolog</h2>
      </div>

      <div className="flex justify-between items-center gap-12">
        <div className="calendar-container">
          <Calendar onChange={onChange} value={value} />
        </div>
        <Button
          bgColor="bg-green-500"
          color="text-white"
          content={"Rezerviši"}
        />
      </div>
    </div>
  );
};

export default Booking;
