import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Reservations from "./pages/Reservations";
import Treatments from "./pages/Treatments";
import AboutDoctor from "./pages/AboutDoctor";
import AboutPatient from "./pages/AboutPatient";
import Booking from "./pages/Booking";
import DoctorReviews from "./pages/DoctorReviews";
import ForgetPassword from "./pages/ForgetPassword";
import ResetPassword from "./pages/ResetPassword";
import GiveReview from "./pages/GiveReview";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route index element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/register" element={<Register />}></Route>
        <Route path="/reservations" element={<Reservations />}></Route>
        <Route path="/treatments" element={<Treatments />}></Route>
        <Route path="/doctor" element={<AboutDoctor />}></Route>
        <Route path="/patient" element={<AboutPatient />}></Route>
        <Route path="/booking" element={<Booking />}></Route>
        <Route path="/doctorReviews/:id" element={<DoctorReviews />}></Route>
        <Route path="/forget-password" element={<ForgetPassword />} />
        <Route path="/reset-password" element={<ResetPassword />} />
        <Route path="/giveReview/:id" element={<GiveReview />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
