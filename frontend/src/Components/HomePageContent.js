import React, { Fragment, useEffect } from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import moneytrans from '../img/moneytrans.png';
import { useState } from 'react';
import '../css/commoncss.css';
import axios from 'axios';
import $ from 'jquery';
import FontAwesome from 'react-fontawesome';


export default function HomePageContent() {

    return (
        <Fragment>
               {/* <div class="box"></div>
                <div class="box"></div>
                <div class="box"></div> */}
                <section>
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-6 justify-content-center">
                                <h1 class="homepage-heading-content">Common eWallet platform  for all eUniwizard and eNivida bidders</h1>
                                <h2 class="homepage-sub-heading-content"> It must be possible for a bidder to use the common e-Wallet platform. eWallet Summary,eWallet Account Statement,Credit Money to eWallet.</h2>
                                <div >

                                </div>
                            </div>
                            <div class="col-lg-6 order-1 order-lg-2 hero-img" data-aos="fade-left" data-aos-delay="200">
                                <img src={moneytrans} class="img-fluid animated" alt="" />
                            </div>
                        </div>
                    </div>

                </section>

           

        </Fragment >
    )
}