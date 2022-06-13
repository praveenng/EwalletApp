import React, { Fragment } from 'react';
import Header from './Header'
 import HomePageContent from './HomePageContent';
// import Footer from './Footer';
// import HomePageFooterImages from './HomepageFooterImages'

class WalletHomePage extends React.Component {

 
  render() {
    return (
      <Fragment>
        <div className="container-fluid">
            <div className="row">
                <Header />
               <HomePageContent />
                {/* <HomePageFooterImages />
                <Footer />  */}
            </div>
        </div>    
      </Fragment>
      
    );
  }
}

export default WalletHomePage;
