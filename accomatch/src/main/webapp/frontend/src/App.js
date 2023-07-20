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
import { HouseSeekerCreateApplication } from './Components/Pages/HouseSeekerCreateApplication';
import { LeaseHolderCreateApplication } from './Components/Pages/LeaseHolderCreateApplication';
import { ForgetPasswordEmail } from './ForgetPasswordEmail';
import { ForgetPassword } from './ForgetPassword';
import { ChatModel } from './Components/Pages/ChatModel';
import { LeaseApplicantView } from './Components/Pages/LeaseApplicantView';
import { ApplicantPosts } from './Components/Pages/ApplicantPosts';
import { Rating } from './Components/Pages/Rating';
import { ToastContainer } from 'react-toastify';
import { ErrorBoundary } from 'react-error-boundary';
import ErrorPage from './Components/Pages/ErrorPage';

import 'react-toastify/dist/ReactToastify.css';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(true);

  const handleLoginSuccess = () => {
    setIsLoggedIn(true);
  };

  return (
    <Router>
      {isLoggedIn && <NavBar />}
      <div className="pages">
        <ToastContainer />
        <ErrorBoundary FallbackComponent={ErrorPage}>
          <Routes>
            <Route path="/" element={<Login onLoginSuccess={handleLoginSuccess} />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/home" element={<Home />} />
            <Route path="/posts" element={<Posts />} />
            <Route path="/posts/:applicationId" element={<PostsDetailsPage />} />
            <Route path="/postsbystatus" element={<PostsByStatus />} />
            <Route path="/applicants" element={<Applicants />} />
            <Route path="/personalposts/:user_Id" element={<LeaseHolderPersonlPosts />} />
            <Route path="/allapplicant" element={<AllApplicantPosts />} />
            <Route path="/create" element={<LeaseHolderCreateApplication />} />
            <Route path="/createApplicant" element={<HouseSeekerCreateApplication />} />
            <Route path="/rating" element={<Rating />} />
            <Route path="/forgetpassword" element={<ForgetPasswordEmail />} />
            <Route path="/updatepassword" element={<ForgetPassword />} />
            <Route path="/leaseapplicantview/:user_Id" element={<LeaseApplicantView />} />
            <Route path='/chat' element={<ChatModel/>}/>
            <Route path="/applicantposts/:user_Id" element={<ApplicantPosts />} />
          </Routes>
        </ErrorBoundary>
      </div>
    </Router>
  );
}

export default App;
