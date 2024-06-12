const Treatments = () => {
  return (
    <>
      <div className="flex flex-col gap-8">
        <h1 className="text-4xl text-center">Tretmani</h1>

        <div className="grid gap-16 grid-cols-1 sm:grid-cols-2 lg:grid-cols-2 justify-center pt-4 items-center">
          <div className="card1">
            <div className="card-content">Masaža</div>
          </div>

          <div className="card2">
            <div className="card-content">Akupuntkura</div>
          </div>

          <div className="card3">
            <div className="card-content">Kiropraktika</div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Treatments;
