import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
return (
	<div>
	<h6><Link to="/Login" >  <button type="button" className=" btn btn-sm btn-primary themebutton">Login</button></Link></h6>
    <h6><Link to="/Registration" >  <button type="button" className=" btn btn-sm btn-primary themebutton">Register</button></Link></h6>
	</div>
);
};

export default Home;
