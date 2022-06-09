import { useState } from 'react';
import axios from 'axios';
import { sha256 } from 'js-sha256';
import { Link, useNavigate } from 'react-router-dom';
import { encrytParam } from '../Services/Encrypt';

const Login = () => {
    const [inputs, setInputs] = useState({});
    const navigate = useNavigate();
    const [captcha, setCaptcha] = useState("");


    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({ ...values, [name]: value }))
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        let userDetails = new FormData(event.target);
        userDetails.set('password', sha256(userDetails.get('password')));

        axios.post('user/login', null, {
            params: {
                loginId: userDetails.get('loginId'),
                password: userDetails.get('password'),
                captcha:captcha
            }
        }).then((response) => {
            if (response.data.errorMsg) {
                alert(response.data.errorMsg);
            } else if (response.data.isValidUser) {
                alert('Login successful!');
                var encParam = encrytParam(response.data.id.toString());
                navigate("/OTPLogin/" + encParam);
            } else {
                alert('Something went wrong. Please retry!');
            }

        })
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="row">
                <div className="col-sm-1"></div>
                <div className="col-sm-5">
                    <label>User Id:
                        <input
                            type="text"
                            name="loginId"
                            value={inputs.loginId || ""}
                            onChange={handleChange}
                        />
                    </label>
                </div>
                <div className="col-sm-5">
                    <label>Password:
                        <input
                            type="text"
                            name="password"
                            value={inputs.password || ""}
                            onChange={handleChange}
                        />
                    </label>
                </div>
                <div className="col-sm-1"></div>

            </div>

            <br />

            <input type="submit" />


            <Link to="/Registration" >  <button type="button" className=" btn btn-sm btn-primary themebutton">Register</button></Link>
        </form>
    )
}
export default Login;