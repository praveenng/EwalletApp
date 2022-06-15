import React, { Fragment } from 'react';
import Header from './Header';
import axios from 'axios';
import '../css/commoncss.css';
import '../css/Loginstyle.css';
import Pagination from "react-js-pagination";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom'; 
import Bootstrap from 'react-bootstrap';
import BootstrapTable from 'react-bootstrap-table-next'
// import Table from "react-table";




class Walletsummary extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            ewalletId :'',
            companyName : '',
            availableBal : '',
            narration : '',
            transactionList : [],
            activePage: 1,
            count : 0,
            isError: {
               
                
            }
        };
       
         
    }
    
    componentDidMount() {
        let summary = new FormData();
        summary.set('page', 1);
        summary.set('limit', 10);
        axios.post('ewalletSummary/getEwalletSummary', summary).then((response) => {
            console.log(JSON.stringify(response.data))
            this.setState({ewalletId : response.data.user.ewalletId})
            this.setState({companyName : response.data.user.individualOrCompanyName})
            this.setState({availableBal : response.data.user.walletBalance})
            this.setState({transactionList : response.data.transactions})
            this.setState({count : response.data.count})
        })
    
      }


    handlePageChange = pageNumber => {
        let summary = new FormData();
        summary.set('page', pageNumber);
        summary.set('limit', 10);
        axios.post('ewalletSummary/getEwalletSummary', summary).then((response) => {
             console.log(JSON.stringify(response.data))
            this.setState({ewalletId : response.data.user.ewalletId})
            this.setState({companyName : response.data.user.individualOrCompanyName})
            this.setState({availableBal : response.data.user.walletBalance})
            this.setState({transactionList : response.data.transactions})
            this.setState({count : response.data.count})
          });
        this.setState({ activePage: pageNumber });
      };
    

    render() {
        return (
            <Fragment>
                <div className="container-fluid">
                    <div className="row">
                        <Header />
                    </div>
                    <div className="row">
                        <div className="col-sm-1"></div>
                        <div className=" col-sm-10 text-center">
                            <h2 className="h2-font" >eWallet Summary </h2>
                            <hr></hr>
                        </div>
                         
                    </div>

                    <div className="row">
                        <div className="col-sm-12">
                            <ol className="breadcrumb ">
                                <li className="array_font breadcrumb1" >eWallet ID: <span className="Profile-value"> {this.state.ewalletId}</span></li> &nbsp;&nbsp;

                                <li className="array_font breadcrumb1">Company Name: <span className="Profile-value"> {this.state.companyName}</span></li> &nbsp;&nbsp;


                                <li className="array_font">Available Balance (&#8377;): <span className="Profile-value">{this.state.availableBal}</span></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <li className="array_font"><Link className="ml-5" style={{marginLeft:'600px'}} to="/Payment" >
                                    Deposit Amount to e-Wallet</Link></li>
                            </ol>
                            
                        </div>
                    </div>

                    <div className="container-fluid jumbotron summaryjumbotron " >
                        <div className="row" style={{ display: "flex" }}>
                            <div className="col-sm-5"> </div>


                            <div className="col-sm-7">

                                <div className="searchwrapper">
                                    <div className="searchbox">
                                        <div className="row">
                                            <div className="col-sm-4"><input type="text" className="form-control" placeholder="Search by Keywords..." /></div>
                                            <div className="col-sm-2">    
                                            <input type='text' className="form-control" value="" id='datetimepicker4' placeholder="From Date " />
                                            </div>
                                            <div className="col-sm-2">     <input type='text' className="form-control" value="" id='datetimepicker4' placeholder="To Date " /></div>

                                            <div className="col-sm-3">
                                                <select className="form-control category">
                                                    <option>category</option>
                                                    <option>Department</option>
                                                    <option>Item name</option>
                                                    <option>Sites</option>
                                                    <option>Transation reffrence ID </option>
                                                </select>
                                            </div>
                                            <div className="col-sm-1"><input type="button" className="btn " style={{ color: "#fff" }} value="Search" /></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>


                        <table className="table table-bordered">  
            <thead>  
                <th>Date</th>  
                <th>Narration</th>  
                <th>Payment Reference</th> 
                <th>Debit</th> 
                <th>Credit</th> 
                <th>Balance</th>  
            </thead>  
    
            {this.state.transactionList.map((transaction, index) => (  
              <tr data-index={index}>  
                <td>{transaction.date}</td>  
                <td>{transaction.narration}</td>  
                <td>{transaction.paymentReference}</td> 
                <td>{transaction.debit}</td>  
                <td>{transaction.credit}</td>  
                <td>{transaction.balance}</td>  
              </tr>  
            ))}  
    
        </table>  
        <div className="d-flex justify-content-center mt-5" style={{display :'inline-block',border:'1px solid #e2e2e2',display:'flex',justifyContent:'center',fontSize:'25px'}}>
        <Pagination 
          activePage={this.state.activePage}
          itemsCountPerPage={10}
          totalItemsCount={this.state.count}
          pageRangeDisplayed={5}
          onChange={this.handlePageChange}
        /></div>

                    </div><br></br>
                    
                

                    </div>



            </Fragment >

        );
    }
}

export default Walletsummary;
