import axios from 'axios';
import React, { Component } from 'react';

export default class Payment extends Component {

  constructor(props) {
    super(props);
    this.GetRazorPayGateway = this.GetRazorPayGateway.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.state = {
      amount: 0,
      orderId: '',
      ewalletId: '',
    }
  }


  render() {
    return (
      <div>
        <div className="form-group">
          <label>Amount : </label>&nbsp;
          <input type="text" className="form-control" name="amount" id="amount" aria-describedby="helpId"
            placeholder="Enter Amount" onChange={this.handleChange} />

          <label>Payment Mode : </label>
          <select id="mode" onChange={this.getOrder}>
            <option>Select payment mode </option>
            <option value='1'>Epayment</option>
            <option value='2'>NEFT</option>
          </select>

        </div>
        <button id="epayment" type="button" className="btn btn-primary btn-lg" style={{ display: 'none' }}
          onClick={() => this.GetRazorPayGateway(this.state.amount)}>
          Deposit </button>
        <button type="button" id="neft" className="btn btn-primary btn-lg" style={{ display: 'none' }}
          onClick={() => this.generateChallan(this.state.amount)}>
          Generate Challan </button>
      </div>
    )
  }

  handleChange(evt) {

    this.setState({
      amount: evt.target.value
    })
  }

  getOrder() {
    var v = document.getElementById('mode').value;
    if (v === '1') {
      document.getElementById('epayment').style.display = 'block'
      document.getElementById('neft').style.display = 'none'
    } else if (v === '2') {
      document.getElementById('neft').style.display = 'block'
      document.getElementById('epayment').style.display = 'none'
    }
  }

  generateChallan(amount) {
    let depositDetails = new FormData();
    depositDetails.set('initiatedAmount', amount);
    depositDetails.set('ewalletNumber', this.state.ewalletId);
    axios.post('/payment/generateChallan/', depositDetails).then((response) => {
      if (response.data) {
        alert(JSON.stringify(response.data.ewalletId));
        var resAmount = JSON.stringify(response.data.amount);
        var orderId = response.data.orderId;
        var user = response.data.users;
        var ewallet = this.state.ewalletId;

        var options = {
          "key": "rzp_test_ncLOWb1GgwbT1G",
          "amount": resAmount,
          "currency": "INR",
          "name": user.individualOrCompanyName,
          "description": ewallet,
          "order_id": orderId.toString(),
          "callback_url": "http://localhost:8082/ewallet/payment/razorpay-gateway-response",
          "prefill": {
            "name": user.individualOrCompanyName,
            "email": user.emailId,
            "contact": user.mobileNumber
          },
          "theme": {
            "color": "#34e8eb"
          },
          "config": {
            "display": {
              "blocks": {
                "banks": {
                  "name": 'Pay via Bank Transfer',
                  "instruments": [
                    {
                      "method": 'bank_transfer'
                    },
                  ],
                },
              },
              "sequence": ['block.banks'],
              "preferences": {
                "show_default_blocks": false,
              },
            },
          },
        }
        var rzp = new window.Razorpay(options);
        rzp.open();
      } else {

      }
    })
  }

  componentDidMount() {
    //alert(this.props.id);
    var script = document.createElement("script");
    script.src = "https://checkout.razorpay.com/v1/checkout.js";
    script.async = true;
    document.body.appendChild(script);

  }



  GetRazorPayGateway(amount) {
    let depositDetails = new FormData();
    depositDetails.set('initiatedAmount', amount);
    axios.post('/payment/depositRequest/', depositDetails).then((response) => {
      if (response.data) {
        //alert(JSON.stringify(response.data.amount));
        var resAmount = JSON.stringify(response.data.amount);
        var orderId = response.data.orderId;
        var user = response.data.users;
        var ewallet = response.data.ewalletId;

        var options = {
          "key": "rzp_test_ncLOWb1GgwbT1G",
          "amount": resAmount,
          "currency": "INR",
          "name": user.individualOrCompanyName,
          "description": ewallet,
          "order_id": orderId.toString(),
          "callback_url": "http://localhost:8082/ewallet/payment/razorpay-gateway-response",
          "prefill": {
            "name": user.individualOrCompanyName,
            "email": user.emailId,
            "contact": user.mobileNumber
          },
          "theme": {
            "color": "#34e8eb"
          },
          "config": {
            "display": {
              "hide": [
                {
                  "method": 'bank_transfer'
                }
              ],
              "preferences": {
                "show_default_blocks": true,
              },
            },
          },
        }
        var rzp = new window.Razorpay(options);
        rzp.open();
      } else {

      }
    })


  }
};
