import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import "./NavBar.css";

function NavBar() {
  const [click, setClick] = useState(false);
  let userid=sessionStorage.getItem('user_id');
  const handleClick = () => setClick(!click);
  return (
    <>
      <nav className="navbar">
        <div className="nav-container">
          <NavLink exact to="/" className="nav-logo">
            AccoMatch 
            <i className="fas fa-home"></i>
          </NavLink>

          <ul className={click ? "nav-menu active" : "nav-menu"}>
            <li className="nav-item">
              <NavLink
                exact
                to="/posts"
                activeClassName="active"
                className="nav-links"
                onClick={handleClick}
              >
                Posts
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                exact
                to={"/applicantposts/"+userid}
                activeClassName="active"
                className="nav-links"
                onClick={handleClick}
              >
                Applicants
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                exact
                to="/applicants"
                activeClassName="active"
                className="nav-links"
                onClick={handleClick}
              >
                Your Posts
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                exact
                to={"/personalposts/"+userid}
                activeClassName="active"
                className="nav-links"
                onClick={handleClick}
              >
                Personal Posts
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                  exact
                  to="/allapplicant"
                  activeClassName="active"
                  className="nav-links"
                  onClick={handleClick}
              >
                All Applicants
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                exact
                to="/create"
                activeClassName="active"
                className="nav-links"
                onClick={handleClick}
              >
                Create
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                exact
                to="/createApplicant"
                activeClassName="active"
                className="nav-links"
                onClick={handleClick}
              >
                Create for houseseeker
              </NavLink>
            </li>
          </ul>
          <div className="nav-icon" onClick={handleClick}>
            <i className={click ? "fas fa-times" : "fas fa-bars"}></i>
          </div>
        </div>
      </nav>
    </>
  );
}

export default NavBar;
