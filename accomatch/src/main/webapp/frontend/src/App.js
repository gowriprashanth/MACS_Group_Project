import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NavBar from './Components/NavBar';
import { Home } from './Components/Pages/Home';
import { Posts } from './Components/Pages/Posts';
import { PostsByStatus } from './Components/Pages/PostsByStatus';
import { PostsDetailsPage } from './Components/Pages/PostsDetailsPage';
import { Applicants } from './Components/Pages/Applicants';
import { LeaseHolderPersonlPosts } from './Components/Pages/LeaseHolderPersonlPosts';
import { AllApplicantPosts } from './Components/Pages/AllApplicantPosts';
import { Login } from './Login';
import { Signup } from './Signup';
import {HouseSeekerCreateApplication} from "./Components/Pages/HouseSeekerCreateApplication";
import{LeaseHolderCreateApplication} from "./Components/Pages/LeaseHolderCreateApplication";
import { ForgetPasswordEmail } from './ForgetPasswordEmail';
import { ForgetPassword } from './ForgetPassword';
import{ LeaseApplicantView } from './Components/Pages/LeaseApplicantView';
import{ ApplicantPosts } from './Components/Pages/ApplicantPosts';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(true);

  const handleLoginSuccess = () => {
    setIsLoggedIn(true);
  };

  return (
    <Router>
      {isLoggedIn && <NavBar />}
      <div className="pages">
        <Routes>
          <Route path="/" element={<Login onLoginSuccess={handleLoginSuccess} />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/home" element={<Home />} />
          <Route path="/posts" element={<Posts />} />
          <Route path="/posts/:applicationId" element={<PostsDetailsPage />} />
          <Route path="/postsbystatus" element ={<PostsByStatus/>}/>
          <Route path="/applicants" element={<Applicants />} />
          <Route path="/personalposts/:user_Id" element={<LeaseHolderPersonlPosts />} />
          <Route path="/allapplicant" element={<AllApplicantPosts />} />
          <Route path="/create" element={<LeaseHolderCreateApplication/>}/>
          <Route path="/createApplicant" element={<HouseSeekerCreateApplication/>}/>
          <Route path="/forgetpassword" element={<ForgetPasswordEmail />} />
          <Route path="/updatepassword" element={<ForgetPassword />} />
          <Route path="/leaseapplicantview/:user_Id" element={<LeaseApplicantView />} />
          <Route path="/applicantposts/:user_Id" element={<ApplicantPosts />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
