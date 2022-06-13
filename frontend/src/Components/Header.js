import React, { Fragment, useEffect } from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import elogo from '../img/elogo.png';
import moneytrans from '../img/moneytrans.png';
import { useState } from 'react';
import '../css/commoncss.css';
import axios from 'axios';
import $ from 'jquery';




export default function Header() {

    return (
        <Fragment>

            <div className="navcolor">
                <div className="row">
                    <div className="col-sm-1"></div>
                    <div className="col-sm-6 ">

                        <div className="logo">

                            <h1><a href="" className="logo-name"> <img src={elogo} />eWallet</a></h1>
                             

                        </div>


                    </div>
                    <div className="col-sm-3 navbar">

                        <Link className="nav-link" to="/Wallethomepage/">Home</Link>
                        <Link className="nav-link" to="/Login/">Login</Link>
                        <Link className="nav-link" to="/">Register </Link>
                        <Link className="nav-link" to="/Login/"> <i className="fa fa-th" style={{ fontSize:"20px" }}></i>  </Link>
                        
                                   
                  
                    </div>
                    <div className="col-sm-2"></div>
                </div>
                </div>

               
           

        </Fragment >
    )
}