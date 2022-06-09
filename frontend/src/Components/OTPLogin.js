import axios from 'axios';
import { sha256 } from 'js-sha256';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

export default function OTPLogin() {
    const { id } = useParams();
    const [inputs, setInputs] = useState({});
    const [mobileNumber, setMobileNumber] = useState("");
    const [emailId, setEmailId] = useState("");

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({ ...values, [name]: value }))
    }

    useEffect(() => {
        //  fetchData()
        axios.get('user/getUser/' + id)
            .then((response) => {
                setMobileNumber(response.data.mobileNumber)
                setEmailId(response.data.emailId)
            });
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();

        let userDetails = new FormData(event.target);
        userDetails.set('password', sha256(userDetails.get('password')));
        axios.post('user/login', userDetails).then((response) => {
            if (response.data.errorMsg) {
                alert(response.data.errorMsg);
            } else if (response.data.success) {
                alert('Login successful!');
            } else {
                alert('Something went wrong. Please retry!');
            }
        })
    }

    const getOTP = () => {
        axios.get('user/getOtp/' + id)
            .then((response) => {
                alert(response.data.successErrorMsg)
            });
    }

    return (
        <form onSubmit={handleSubmit}>
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

            <input type="button" value="Send OTP" onClick={getOTP} />
        </form>
    )

}