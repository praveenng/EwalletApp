import React, { Fragment } from 'react';
 import '../App.css';
import {
  Link,
} from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import $ from 'jquery';

export default class Home extends React.Component {
  componentDidMount() {
    axios.get('/erpAdminLogin/logout', null,).then((response) => {
        console.log(response.data);
    })
}

  render() {
    return (
      <Fragment>
       <span className="loadPageStyle">
          <h1>eWallet</h1>
          <h3><Link className="loadPageStyle linkStyle" to="/Wallethomepage/">Homepage</Link><br></br></h3>
          
        </span>
        
      
      </Fragment>

    );
  }
}
