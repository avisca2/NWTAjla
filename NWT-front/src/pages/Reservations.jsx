import bed_img from "../assets/first.png";
import massage_img from "../assets/second.png";
import Button from "../components/Button";

const Reservations = () => {
  return (
    <>
      <div className="flex flex-col justify-center items-center gap-6">
        <h1 className="text-4xl">Centar Za bolji život</h1>
        <div className="flex justify-between gap-4 p-4">
          <div className="flex flex-col gap-6 items-center">
            <div>
              <img src={bed_img} alt="" />
            </div>
            <Button content={"Reserviši smještaj"} />
          </div>

          <div className="flex flex-col gap-6 items-center">
            <div>
              <img src={massage_img} alt="" />
            </div>
            <Button content={"Reserviši tretman"} />
          </div>
        </div>
      </div>
    </>
  );
};

export default Reservations;
