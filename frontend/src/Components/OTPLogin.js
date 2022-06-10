import axios from 'axios';
import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

export default function OTPLogin() {
    const { id } = useParams();
    const [mobileNumber, setMobileNumber] = useState("");
    const [emailId, setEmailId] = useState("");
    const [showOTPScreen, setShowOTPScreen] = useState(false)
    const [OTPSent, setOTPSent] = useState(false)
    const [buttonValue, setButtonValue] = useState("Send OTP")
    const [timerCount, setTimerCount] = useState("")
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('user/getUser/' + id)
            .then((response) => {
                setMobileNumber(response.data.mobileNumber)
                setEmailId(response.data.emailId)
            });
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();
        let userDetails = new FormData(event.target);
        axios.post('user/validateOtpLogin/' + id, null, {
            params: {
                mobileNumber: userDetails.get('mobileNumber'),
                emailId: userDetails.get('emailId'),
                otp: userDetails.get('otp')
            }
        }).then((response) => {

            if (response.data.isOTPValidated) {
                navigate('/Ewallet')
            } else {
                alert(response.data.successErrorMsg)
            }
        })
    }

    const getOTP = () => {
        axios.get('user/getOtp/' + id)
            .then((response) => {
                alert(response.data.successErrorMsg)
                if (response.data.isOTPgenerated) {
                    setShowOTPScreen(true)
                    countDown()
                    setOTPSent(true)
                }
            });
    }

    const countDown = () => {
        var duration = 10 * 60;
        var timer = setInterval(function () {
            var c = duration--,
                min = (Math.abs(c / 60)) >> 0,
                sec = Math.abs(c - min * 60) + '';
            min = min < 10 ? "0" + min : min;
            setTimerCount(min + ":" + (sec.length > 1 ? '' : '0') + sec + ' Remaining to Enter OTP')
            if (duration == -1) {
                clearInterval(timer)
                setShowOTPScreen(false)
                setButtonValue("Re-Send OTP")
                setOTPSent(false)
            }
        }, 1000);
    }

    return (
        <form onSubmit={handleSubmit} encType="multipart/form-data">
            <div className="row">
                <div className="col-sm-1"></div>
                <div className="col-sm-5">
                    <label>Mobile No:
                        <input
                            type="text"
                            name="mobileNumber"
                            value={mobileNumber}
                            readOnly
                        />
                    </label>
                </div>
                <div className="col-sm-5">
                    <label>Email:
                        <input
                            type="text"
                            name="emailId"
                            value={emailId}
                            readOnly
                        />
                    </label>
                </div>
                <div className="col-sm-1"></div>

            </div>

            <br />

            {OTPSent ? null : <input type="button" id="sendOTP" value={buttonValue} onClick={getOTP} />}
            {showOTPScreen ? <div>
                <div>
                    <div>
                        <label>Enter OTP Here : </label>
                    </div>
                    <div>
                        <input name="otp" type="number" id="otpVal"
                            maxlength="4" placeholder="Enter OTP" required="required" />
                    </div>
                    <div>
                        {timerCount}
                    </div>
                </div>
                <div>
                    <div>
                        <p>Please Check SMS On Your Mobile and Email For OTP</p>
                    </div>
                </div>
                <input type="submit" id="showVerify" value="Verify & Login" />
            </div> : null}
        </form>
    )

}