import { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { sha256 } from 'js-sha256';
import { useNavigate } from 'react-router-dom';

const useEffectOnlyOnce = (callback, dependencies, condition) => {
    const calledOnce = useRef(false);

    useEffect(() => {
        if (calledOnce.current) {
            return;
        }

        if (condition(dependencies)) {
            callback(dependencies);

            calledOnce.current = true;
        }
    }, [callback, condition, dependencies]);
};

export default function Registration() {

    const [inputs, setInputs] = useState({});
    let [bankData, setBankData] = useState([]);

    const navigate = useNavigate();
    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({ ...values, [name]: value }))
    }

    useEffectOnlyOnce(
        (dependencies) => {
            //  console.log('I run only once if toggle is false.');
            fetchData()
        },
        [false],
        (dependencies) => dependencies[0] === false
    );

    const fetchData = async () => {
        try {
            const response = await axios.get('user/getMasterData');
            console.log(JSON.stringify(response.data.bankMaster));
            setBankData(response.data.bankMaster)
        } catch (error) {
            alert(error);
        }
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        let userDetails = new FormData(event.target);
        userDetails.set('password', sha256(userDetails.get('password')));
        userDetails.set('confirmPassword', sha256(userDetails.get('confirmPassword')));
        axios.post('user/registration', userDetails).then((response) => {
            if (response.data.errorMsgMap) {
                Object.entries(response.data.errorMsgMap).map(([key, value]) => {
                    alert(value)
                })
            } else if (response.data.errorMsg) {
                alert(response.data.errorMsg);
            } else if (response.data.isSaved) {
                alert('User registration is successful. Please login!');
                navigate('/Login')
            } else {
                alert('Failed to register!');
            }
        })
    }

    const onFileChangeHandler = (e) => {
        e.preventDefault()
        const bankFileObject = new FormData(e.target);
        axios.post('user/uploadUserBankFile', bankFileObject).then((response) => {
            var responseObject = response.data;
            var errorMsg = responseObject.errorMsg;
            var successMsg = responseObject.successMsg;
            if (errorMsg) {
                alert(errorMsg)
            } else if (successMsg) {
                alert(successMsg)
            } else {
                alert("Failed")
            }
        })
    };

    return (
        <div className="container">
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

                    </div>
                    <div className="col-sm-1"></div>

                </div>

                <div className="row">
                    <div className="col-sm-1"></div>
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
                    <div className="col-sm-5">
                        <label>Confirm Password:
                            <input
                                type="text"
                                name="confirmPassword"
                                value={inputs.confirmPassword || ""}
                                onChange={handleChange}
                            />
                        </label>
                    </div>
                    <div className="col-sm-1"></div>

                </div>

                <br />
                <div className="row">
                    <div className="col-sm-1"></div>
                    <div className="col-sm-5">
                        <label>Company or Individual Name:
                            <input
                                type="text"
                                name="individualOrCompanyName"
                                value={inputs.individualOrCompanyName || ""}
                                onChange={handleChange}
                            />
                        </label>
                    </div>
                    <div className="col-sm-5">
                        <label>Email Id:
                            <input
                                type="text"
                                name="emailId"
                                value={inputs.emailId || ""}
                                onChange={handleChange}
                            />
                        </label>
                    </div>
                    <div className="col-sm-1"></div>

                </div>

                <br />
                <div className="row">
                    <div className="col-sm-1"></div>
                    <div className="col-sm-5">
                        <label>Mobile:
                            <input
                                type="text"
                                name="mobileNumber"
                                value={inputs.mobileNumber || ""}
                                onChange={handleChange}
                            />
                        </label>
                    </div>
                    <div className="col-sm-5">
                        <label>PAN Number:
                            <input
                                type="text"
                                name="panNumber"
                                value={inputs.panNumber || ""}
                                onChange={handleChange}
                            />
                        </label>
                    </div>
                    <div className="col-sm-1"></div>

                </div>
                <br />
                <div className="row">
                    <div className="col-sm-1"></div>
                    <div className="col-sm-5">
                        <label>Adhar No:
                            <input
                                type="text"
                                name="adharNumber"
                                value={inputs.adharNumber || ""}
                                onChange={handleChange}
                            />
                        </label>
                    </div>
                    <div className="col-sm-5">
                        <label>Acc Number:
                            <input
                                type="text"
                                name="accountNumber"
                                value={inputs.accountNumber || ""}
                                onChange={handleChange}
                            />
                        </label>
                    </div>
                    <div className="col-sm-1"></div>

                </div>
                <br />
                <div className="row">
                    <div className="col-sm-1"></div>
                    <div className="col-sm-5">
                        <label>Acc Holder:
                            <input
                                type="text"
                                name="accountHolderName"
                                value={inputs.accountHolderName || ""}
                                onChange={handleChange}
                            />
                        </label>
                    </div>
                    <div className="col-sm-5">
                        <label>IFSC:
                            <input
                                type="text"
                                name="ifsc"
                                value={inputs.ifsc || ""}
                                onChange={handleChange}
                            />
                        </label>
                    </div>
                    <div className="col-sm-1"></div>

                </div>
                <br />
                <div className="row">
                    <div className="col-sm-1"></div>
                    <div className="col-sm-5">
                        <label>Bank Name:
                            <select name="bankId" required>
                                <option value="">Select bank name</option>
                                {
                                    Object.entries(bankData).map(([key, value]) => {
                                        return <option value={key}>{value}</option>
                                    })
                                }
                            </select>
                        </label>
                    </div>
                    <div className="col-sm-5">
                        <label>Branch Name:
                            <input
                                type="text"
                                name="branchName"
                                value={inputs.branchName || ""}
                                onChange={handleChange}
                            />
                        </label>
                    </div>
                    <div className="col-sm-1"></div>

                </div>
                <input type="submit" />
            </form>

            <form onSubmit={onFileChangeHandler} encType="multipart/form-data">
                <input type="hidden" name="id" value="2" />

                <div class="row">
                    <div className="col-sm-1"></div>
                    <div className="col-sm-10 text-center">

                        <label>Upload Bank File</label>
                        <input type="file" autoComplete="off" name="bankFile" />

                    </div>
                    <div className="col-sm-1"></div>
                </div>
                
                <br />
                <div class="row">
                    <div class="col-sm-12 text-center">
                    <button type="submit" >Sub</button>
                    </div>
                </div>
            </form>
        </div>
    )
}
