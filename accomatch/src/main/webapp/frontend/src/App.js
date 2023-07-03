import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import NavBar from './Components/NavBar';
import { Home } from './Components/Pages/Home';
import { Posts } from './Components/Pages/Posts';
import { PostsDetailsPage } from './Components/Pages/PostsDetailsPage';
import { Applicants } from './Components/Pages/Applicants';
import { Contact } from './Components/Pages/Contact';
import { Login } from './Login';
import { Signup } from './Signup';

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
          <Route path="/applicants" element={<Applicants />} />
          <Route path="/contact" element={<Contact />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
