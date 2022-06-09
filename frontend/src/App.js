import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Registration from './Components/Registration';
import Login from './Components/Login';
import Home from './Components/Home';
import NoPage from './Components/NoPage';
import Payment from './Components/Payment';
import PaymentSuccessfully from './Components/PaymentSuccessfully';
import Ewallet from './Components/Ewallet';
import OTPLogin from './Components/OTPLogin';

function App() {
  return (
    <BrowserRouter basename={process.env.REACT_APP_ROUTER_BASE}>
      <Routes>
        <Route index path="/" element={<Home />} />
        <Route path="*" element={<NoPage />} />
        <Route exact path="/Login" element={<Login />}/>
        <Route exact path="/Registration" element={<Registration />}/>
        <Route exact path="/Payment" element={<Payment/>}/>
        <Route exact path='/PaymentSuccessfully' element={<PaymentSuccessfully/>}/>
        <Route exact path='/EWallet' element={<Ewallet/>}/>
        <Route exact path="/OTPLogin/:id" element={<OTPLogin/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
