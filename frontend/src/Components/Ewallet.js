import axios from 'axios';
import React, { Component } from 'react'
import { getDataSession } from '../Services/Session';
import { Link } from 'react-router-dom'; 
export default class Ewallet extends Component {
  constructor(props){
    super(props);
    
    this.state = {
      
        ewalletId : '',
        userName : '',
        walletBalance : '',
        asyncLoad : '',
        checkSessionOut: []
    }
  }

  
  componentDidMount(){

    var config = { "Access-Control-Allow-Origin": "*" }
    //to get session data
    getDataSession(config, (res) => {
      var sessionValues = res.data;
      var checkSession = Object.keys(sessionValues).length;
      this.setState({ checkSessionOut: checkSession });
      //alert('session=='+JSON.stringify(sessionValues.id));
      this.setState({ asyncLoad: "true" });
  }, (err) => {
      console.log(err);

  });

    axios.post('/payment/getUser/').then((response) =>{
        if(response.data){
            // alert(JSON.stringify(response.data))
            this.setState({ewalletId : response.data.user.ewalletId})
            this.setState({userName : response.data.user.individualOrCompanyName})
            this.setState({walletBalance : response.data.user.walletBalance})
        }else{
        }
    })
    
  }
    
  render() {
    return (
      <div>
          <div className='text-center'>
        <h3>Welcome {this.state.userName}</h3>
        </div>
<br/>
        <div className='text-center'>
        <h3>e-Wallet</h3>
        </div>
        <br></br>
        <table style={{border : "1px solid black"}}>
            
            <thead style={{border : "1px solid black"}}>
                <th style={{border : "1px solid black"}}>Ewallet Number</th>
                <th style={{border : "1px solid black"}}>User Name</th>
                <th style={{border : "1px solid black"}}>Wallet Balance</th>
            </thead>
            <tr style={{border : "1px solid black"}}>
                <td style={{border : "1px solid black"}}>{this.state.ewalletId}</td>
                <td style={{border : "1px solid black"}}>{this.state.userName}</td>
                <td style={{border : "1px solid black"}}>{this.state.walletBalance}</td>
            </tr>
        </table>
        <br/>
        <Link to="/Payment" ><button id = "epayment" type="button" className="btn btn-primary btn-lg" >
          Deposit Amount to e-Wallet</button></Link>
      </div>
    )
  }
 

  };
















