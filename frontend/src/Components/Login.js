import axios from 'axios';
import { sha256 } from 'js-sha256';
import { Fragment, useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import '../css/commoncss.css';
import '../css/Loginstyle.css';
import login from '../img/login7.svg';
import { encrytParam } from '../Services/Encrypt';
import Header from './Header';

const Login = () => {
    const navigate = useNavigate();
    const { register, handleSubmit, formState: { errors } } = useForm({
        mode: "onBlur"
    });
    const [captchaError, setCaptchaError] = useState("")
    const [captchaCode, setCaptchaCode] = useState("")

    useEffect(() => {
        getGeneratedCaptcha()
    }, []);

    const registerOptions = {
        loginId: { required: "User Id is required" },
        password: {
            required: "Password is required",
            minLength: {
                value: 8,
                message: "Password must have at least 8 characters"
            }
        },
        captcha: {
            required: "Captcha is required"
        }
    };

    const validateCaptcha = (val) => {
        if (captchaCode !== val) {
            setCaptchaError("Invalid Captcha");
        }
        else {
            setCaptchaError("");
        }
    }

    const onSubmit = (data) => {
        console.log("Data==" + JSON.stringify(data))

        axios.post('user/login', null, {
            params: {
                loginId: data["loginId"],
                password: sha256(data["password"]),
                captcha: data["captcha"],
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

    const handleError = (errors) => { console.log('Error..') };

    const getGeneratedCaptcha = () => {
        axios.get('user/getGeneratedCaptcha', null,).then((response) => {
            var responseCaptcha = response.data;
            setCaptchaCode(responseCaptcha)
        })
    }

    const reloadCaptcha = () => {
        setCaptchaError("");
        getGeneratedCaptcha()
    }

    return (
        <Fragment>
            <div className="container-fluid">
                <form onSubmit={handleSubmit(onSubmit, handleError)}>
                    <div className="row">
                        <Header />
                        <div className="row">

                            <div className="col-sm-12">
                                <div className="container hq-template" style={{ padding: "2% !important" }}>
                                    <div className="row">
                                        <div className="col-sm-6">
                                            <img className="login-image " src={login} />
                                        </div>

                                        <div className="login-block col-sm-6">
                                            <h3 className="mb-3">User login</h3>
                                            <div className="row">
                                                <div className="col-sm-2"></div>
                                                <div className="col-sm-8 username">
                                                    <div className="form-group mb-3">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-person-fill" viewBox="0 0 16 16">
                                                            <path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
                                                        </svg>
                                                        <input name="loginId" type="text" {...register('loginId', registerOptions.loginId)} className="form-control input100 " placeholder="Enter User Id" />
                                                        <small className="text-danger">
                                                            {errors?.loginId && errors.loginId.message}
                                                        </small>

                                                    </div>
                                                    <div className="form-group mb-3">
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-lock-fill" viewBox="0 0 16 16">
                                                            <path d="M2.5 9a2 2 0 0 1 2-2h7a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-7a2 2 0 0 1-2-2V9z" />
                                                            <path fill-rule="evenodd" d="M4.5 4a3.5 3.5 0 1 1 7 0v3h-1V4a2.5 2.5 0 0 0-5 0v3h-1V4z" />
                                                        </svg>
                                                        <input type="password" name="password" {...register('password', registerOptions.password)} className="form-control input100 " placeholder="Password"
                                                        />
                                                        <small className="text-danger">
                                                            {errors?.password && errors.password.message}
                                                        </small>

                                                    </div><br></br>
                                                    <div className="row">
                                                        <div>
                                                            {captchaCode}
                                                        </div>
                                                        <div className="col-sm-12 text-center">
                                                            <input type="text" name="captcha" {...register('captcha', registerOptions.captcha)} className="form-control input100" onChange={e => validateCaptcha(e.target.value)}
                                                                placeholder="Enter captcha" />
                                                            <small className="text-danger">
                                                                {errors?.captcha && errors.captcha.message}
                                                                {captchaError}
                                                            </small>

                                                        </div>
                                                    </div>
                                                    <div className="row">
                                                        <div className="col-sm-12 text-center" >
                                                            <p className='note'> Note: Enter above captcha code and click on Login button to login</p>
                                                        </div>
                                                    </div>
                                                    <div className="text-center login-btn" ><button type="submit" className="btnRegister">Login</button></div>
                                                    <div className="text-center link" style={{ margintop: "35px;" }}> Forgot  <a href="#" className="signup-url"> Username / Password ?</a> </div>
                                                    <div className="text-center"> Not a member? <a href="#" className="signup-url" style={{ fontweight: "bold;" }}>Sign up</a></div>

                                                </div>

                                                <div className="col-sm-2"></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>

                        </div></div></form></div >
        </Fragment >
    )
}
export default Login;