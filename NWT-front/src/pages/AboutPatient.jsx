import patient from "../assets/patient.png";

const AboutPatient = () => {
  return (
    <>
      <div className="flex flex-col justify-center items-center gap-8">
        <div className="flex justify-between items-center gap-16">
          <div className="w-[300px] h-[400px]">
            <img src={patient} alt="" />
          </div>
          <div className="flex flex-col gap-6">
            {[
              { label: "Ime pacijenta:", type: "text" },
              { label: "Prezime pacijenta:", type: "text" },
              { label: "Datum rodenja pacijenta:", type: "text" },
              { label: "Broj telefona pacijenta:", type: "text" },
              { label: "Doktor pacijenta:", type: "text" },
            ].map((field, index) => (
              <div key={index} className="flex gap-8 items-center">
                <div className="border-2 border-black bg-white p-2 w-40">
                  <span className="block">{field.label}</span>
                </div>
                <input
                  type={field.type}
                  className="flex-grow p-2 border-2 border-black w-64"
                />
              </div>
            ))}
          </div>
        </div>
        <div className="flex flex-col justify-start items-start p-4">
          <h1 className="text-2xl my-8">Historija lječenja</h1>
          <p className="mb-4">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla quam
            velit, vulputate eu pharetra nec, mattis ac neque. Duis vulputate
            commodo lectus, ac blandit elit tincidunt id. Sed rhoncus, tortor
            sed eleifend tristique, tortor mauris molestie elit, et lacinia
            ipsum quam nec dui.
          </p>
          <p className="mb-4">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla quam
            velit, vulputate eu pharetra nec, mattis ac neque. Duis vulputate
            commodo lectus, ac blandit elit tincidunt id. Sed rhoncus, tortor
            sed eleifend tristique, tortor mauris molestie elit, et lacinia
            ipsum quam nec dui.
          </p>
        </div>
      </div>
    </>
  );
};

export default AboutPatient;
