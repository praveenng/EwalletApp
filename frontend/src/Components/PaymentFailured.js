import React, { Component } from 'react'
import { Link } from 'react-router-dom'

export default class PaymentFailured extends Component {

    

  render() {
        return (
            <div className="container-fluid">
            <div className="row">
                <div className="col-sm-12 text-center">
                    <h4 className="loadMsg">Payment Failed</h4>
                  <Link to="/EWallet"><button type="button" className=" btn btn-sm btn-primary themebutton">Ok</button>
                  </Link>
                </div>
            </div>
            </div>
           
        )
    
  }
}
